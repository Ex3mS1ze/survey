package com.survey.service;

import com.survey.entity.*;
import com.survey.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.survey.service.UserService.getUserFromPrincipal;

@Service
public class QuestionnaireService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
    private final String GASTRO_TYPE = "gastroenterological";

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private DiagnosisRepo diagnosisRepo;
    @Autowired
    private QuestionnaireTypeRepo questionnaireTypeRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAllByOrderById();
    }

    public void saveNewQuestionnaire(Questionnaire questionnaire) {
        User user = getUserFromPrincipal();
        questionnaire.setUser(user);
        questionnaire.setDate(LocalDateTime.now());

        if (questionnaire.getProcessed() == null) {
            questionnaire.setProcessed(false);
        }

        List<Answer> answers = questionnaire.getAnswers();
        questionnaire.setAnswers(null);
        //Save empty questionnaire to get id and set questionnaire to each answer.
        Questionnaire savedQuestionnaire = questionnaireRepo.save(questionnaire);
        answers.forEach(answer -> answer.setQuestionnaire(savedQuestionnaire));
        savedQuestionnaire.setAnswers(answers);
        questionnaireRepo.save(savedQuestionnaire);
        LOGGER.info("New questionnaire created{}", savedQuestionnaire.toString());
    }

    public void saveExistedQuestionnaire(Questionnaire questionnaire, Long id) {
        Optional<Questionnaire> questionnaireFromDb = questionnaireRepo.findById(id);
        //TODO add exception with handler
        questionnaire.getAnswers().forEach(answer -> answer.setQuestionnaire(questionnaireFromDb.get()));
        questionnaireFromDb.get().setAnswers(questionnaire.getAnswers());
        questionnaireRepo.save(questionnaireFromDb.get());
    }

    public void saveExistedProcessedQuestionnaire(Questionnaire questionnaire, Long id) {
        saveExistedQuestionnaire(questionnaire, id);
        Optional<Questionnaire> questionnaireFromDb = questionnaireRepo.findById(id);
        questionnaireFromDb.get().setProcessed(true);
        questionnaireFromDb.get().setDiagnosis(questionnaire.getDiagnosis());
        questionnaireRepo.save(questionnaireFromDb.get());
    }

    public List<Questionnaire> getAllUsersQuestionnaires() {
        User user = getUserFromPrincipal();
        Optional<List<Questionnaire>> questionnaires = Optional.of(new ArrayList<Questionnaire>());

        if (user.isAdmin() || user.isDoctor()) {
            questionnaires = Optional.ofNullable(getAllQuestionnaires());
        } else if (user.isPatient()) {
            questionnaires = questionnaireRepo.findAllByUserOrderByDate(user);
        }

        return questionnaires.orElse(new ArrayList<Questionnaire>());
    }

    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepo.findAll();
    }

    public void deleteQuestionnaireById(Long id) {
        questionnaireRepo.deleteById(id);
    }

    public Questionnaire findQuestionnaireById(Long id) {
        Optional<Questionnaire> questionnaire = questionnaireRepo.findById(id);
        if (!questionnaire.isPresent()) {
            throw new NoSuchElementException("No questionnaire with id=" + id);
        }

        Questionnaire existedQuestionnaire = questionnaire.get();
        existedQuestionnaire.getType()
                            .setQuestions(getOrderedQuestionsByTypeId(existedQuestionnaire.getType().getId()));
        existedQuestionnaire.setAnswers(
                getOrderedAnswersByQuestionsAndQuestionnaireId(existedQuestionnaire.getType().getQuestions(), id));

        //        Collections.sort(existedQuestionnaire.getAnswers(), (a1, a2) -> a1.getQuestion().getId().compareTo(a2.getQuestion().getId()));
        return existedQuestionnaire;
    }

    public boolean operateQuestionnaireAnswers(Questionnaire questionnaire, boolean isNew, Long questionnaireId) {
        if (questionnaire.isAnyAnswerEmpty()) {
            return false;
        }
        //TODO real calculate
        List<Long> allIds = diagnosisRepo.getAllIds();
        Random random = new Random();
        Diagnosis diagnosis = diagnosisRepo.findById(allIds.get(random.nextInt(allIds.size() - 1))).get();
        questionnaire.setDiagnosis(diagnosis);
        questionnaire.setProcessed(true);
        if (isNew) {
            saveNewQuestionnaire(questionnaire);
        } else {
            saveExistedProcessedQuestionnaire(questionnaire, questionnaireId);
        }

        return true;
    }

    public List<String> getAllQuestionCategories() {
        List<String> questionCategories = questionRepo.getAllQuestionCategories();
        questionCategories.replaceAll(c -> c.substring(0, c.lastIndexOf(',')));
        return questionCategories;
    }

    public QuestionnaireType getQuestionnaireTypeByTypeName(String typeName) {
        QuestionnaireType questionnaireType;
        if (typeName == null || typeName.isEmpty() ||
            !questionnaireTypeRepo.getAllTypesNames().contains(typeName)) {
            LOGGER.warn("Questionnaire type is empty, load gastroenterological test by default.");
            questionnaireType = questionnaireTypeRepo.getByName(GASTRO_TYPE).get();
        } else {
            questionnaireType = questionnaireTypeRepo.getByName(typeName)
                                                     .orElseThrow(() -> new IllegalArgumentException(
                                                             "Wrong typeName argument =" + typeName));
        }

        List<Question> orderedQuestions = getOrderedQuestionsByTypeId(questionnaireType.getId());
        questionnaireType.setQuestions(orderedQuestions);

        return questionnaireType;
    }

    public List<Question> getOrderedQuestionsByTypeId(Long type_id) {
        List<Long> orderedQuestionsIds = questionnaireTypeRepo.getOrderedQuestionsIds(type_id);
        List<Question> orderedQuestions = new ArrayList<>();
        for (Long orderedQuestionsId : orderedQuestionsIds) {
            orderedQuestions.add(questionRepo.getById(orderedQuestionsId).get());
        }

        return orderedQuestions;
    }

    public List<Answer> getOrderedAnswersByQuestionsAndQuestionnaireId(List<Question> orderedQuestions,
                                                                       Long questionnaire_id) {
        List<Answer> answers = new ArrayList<>();
        for (Question question : orderedQuestions) {
            Optional<Answer> answer = answerRepo.getByQuestionIdAndQuestionnaireId(question.getId(), questionnaire_id);
            answers.add(answer.orElseThrow(() -> new NoSuchElementException("Answer with id = " + question.getId())));
        }

        return answers;
    }

    public Questionnaire getNewQuestionnaireByTypeName(String typeName) {
        QuestionnaireType questionnaireType = getQuestionnaireTypeByTypeName(typeName);
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setType(questionnaireType);

        for (int i = questionnaireType.getQuestions().size(); i > 0; i--) {
            questionnaire.getAnswers().add(new Answer());
        }

        return questionnaire;
    }
}
