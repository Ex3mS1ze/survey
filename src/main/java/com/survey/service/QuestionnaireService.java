package com.survey.service;

import com.survey.entity.*;
import com.survey.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.AccessControlException;
import java.time.LocalDateTime;
import java.util.*;

import static com.survey.service.UserService.getUserFromPrincipal;

@Service
public class QuestionnaireService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
    public static final String GASTRO_TYPE = "gastroenterological";
    public static final String CARDIO_TYPE = "cardiovascular";

    private final QuestionRepo questionRepo;
    private final AnswerRepo answerRepo;
    private final QuestionnaireRepo questionnaireRepo;
    private final DiagnosisRepo diagnosisRepo;
    private final QuestionnaireTypeRepo questionnaireTypeRepo;
    private final QuestionWeightService questionWeightService;
    private final ScoreQuestionnaireRepo scoreQuestionnaireRepo;

    private final AssessmentService assessmentService;

    @Autowired
    public QuestionnaireService(QuestionRepo questionRepo, AnswerRepo answerRepo,
                                QuestionnaireRepo questionnaireRepo,
                                DiagnosisRepo diagnosisRepo, QuestionnaireTypeRepo questionnaireTypeRepo,
                                QuestionWeightRepo questionWeightRepo,
                                QuestionWeightService questionWeightService,
                                ScoreQuestionnaireRepo scoreQuestionnaireRepo,
                                AssessmentService assessmentService) {
        this.questionRepo = questionRepo;
        this.answerRepo = answerRepo;
        this.questionnaireRepo = questionnaireRepo;
        this.diagnosisRepo = diagnosisRepo;
        this.questionnaireTypeRepo = questionnaireTypeRepo;
        this.questionWeightService = questionWeightService;
        this.scoreQuestionnaireRepo = scoreQuestionnaireRepo;
        this.assessmentService = assessmentService;
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAllByOrderById();
    }

    public Questionnaire saveNewQuestionnaire(Questionnaire questionnaire) {
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
        for (Answer answer : answers) {
            answer.setQuestionnaire(savedQuestionnaire);
        }
        savedQuestionnaire.setAnswers(answers);
        savedQuestionnaire = questionnaireRepo.save(savedQuestionnaire);
        LOGGER.info("New questionnaire created{}", savedQuestionnaire.getId());
        return savedQuestionnaire;
    }

    public void saveExistedQuestionnaire(Questionnaire questionnaire, Long id) {
        Questionnaire questionnaireFromDb = questionnaireRepo.findById(id).orElseThrow(NoSuchElementException::new);
        //TODO add exception with handler
        questionnaire.getAnswers().forEach(answer -> answer.setQuestionnaire(questionnaireFromDb));
        questionnaireFromDb.setAnswers(questionnaire.getAnswers());
        questionnaireRepo.save(questionnaireFromDb);
    }

    public Questionnaire saveExistedProcessedQuestionnaire(Questionnaire questionnaire, Long id) {
        saveExistedQuestionnaire(questionnaire, id);
        Questionnaire questionnaireFromDb = questionnaireRepo.findById(id).orElseThrow(NoSuchElementException::new);
        questionnaireFromDb.setProcessed(true);
        questionnaireFromDb.setDiagnosis(questionnaire.getDiagnosis());
        return questionnaireRepo.save(questionnaireFromDb);
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

    public void deleteQuestionnaireById(Long id, boolean secure) {
        User principal = getUserFromPrincipal();
        if (secure && !principal.isAdmin() && principal.isPatient() &&
            !questionnaireRepo.existsByIdAndUserId(id, principal.getId())) {
            LOGGER.warn("Patient {} try to delete questionnaire(id={})", principal.getEmail(), id);
            throw new AccessControlException("Access denied");
        }
        questionnaireRepo.deleteById(id);
    }

    public Questionnaire findQuestionnaireById(Long id, boolean secure) {
        User principal = getUserFromPrincipal();
        Questionnaire questionnaire = questionnaireRepo.findById(id)
                                                       .orElseThrow(() -> new NoSuchElementException(
                                                               "No questionnaire with id=" + id));
        if (secure && !principal.isAdmin() && principal.isPatient() &&
            !questionnaire.getUser().getId().equals(principal.getId())) {
            LOGGER.warn("Patient {} trying to access questionnaire(id={}) which does not own", principal.getEmail(),
                        id);
            throw new AccessControlException("Access denied");
        }
        questionnaire.getType().setQuestions(getOrderedQuestionsByTypeId(questionnaire.getType().getId()));
        questionnaire.setAnswers(getOrderedAnswers(questionnaire.getType().getQuestions(), id));

        return questionnaire;
    }

    public boolean operateQuestionnaireAnswers(Questionnaire questionnaire, boolean isNew, Long questionnaireId) {
        if (questionnaire.getAnswers().size() <= 0 || questionnaire.isAnyAnswerEmpty()) {
            LOGGER.warn("Can't operate questionnaire, because empty answer/answers exist");
            return false;
        }

        //Save to fetch questions
        if (isNew) {
            questionnaire = saveNewQuestionnaire(questionnaire);
            questionnaire.setType(questionnaireTypeRepo.getOne(questionnaire.getType().getId()));
        } else {
            questionnaire = saveExistedProcessedQuestionnaire(questionnaire, questionnaireId);
        }

        if (questionnaire.getType().getName().equals(GASTRO_TYPE)) {
            List<ScoreQuestionnaireResult> operatingResults = questionWeightService.operateQuestionnaire(questionnaire);
            questionnaire.setScoreResults(operatingResults);
        } else if (questionnaire.getType().getName().equals(CARDIO_TYPE)) {
            /*JsonObject jsonQuestionnaire = QuestionnaireToAnamnesisConverter.convert(questionnaire);
            List<Long> allIds = diagnosisRepo.getAllIds();
            Random random = new Random();
            Diagnosis diagnosis = diagnosisRepo.findById(allIds.get(random.nextInt(allIds.size() - 1))).get();
            questionnaire.setDiagnosis(diagnosis);*/
            assessmentService.operateQuestionnaireRank(questionnaire);
            Diagnosis diagnosis = assessmentService.operateQuestionnaireDirect(questionnaire);
            questionnaire.setDiagnosis(diagnosis);
            questionnaire.setProcessed(true);
            questionnaireRepo.save(questionnaire);
        }

        questionnaire.setProcessed(true);
        questionnaireRepo.save(questionnaire);

        return true;
    }


    public List<String> getAllQuestionCategories() {
        List<String> questionCategories = questionRepo.getAllQuestionCategories();
        questionCategories.replaceAll(c -> c.substring(0, c.lastIndexOf(',')));
        return questionCategories;
    }

    public QuestionnaireType getQuestionnaireTypeByTypeName(String typeName) {
        QuestionnaireType questionnaireType;
        if (typeName == null || typeName.isEmpty() || !questionnaireTypeRepo.getAllTypesNames().contains(typeName)) {
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

    public List<Answer> getOrderedAnswers(List<Question> orderedQuestions, Long questionnaire_id) {
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

    /*public boolean isProcessed(long id) {
        return questionnaireRepo.isProcessed(id);
    }*//*public boolean isProcessed(long id) {
        return questionnaireRepo.isProcessed(id);
    }*/
}
