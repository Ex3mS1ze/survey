package com.main.service;

import com.main.entity.Diagnosis;
import com.main.entity.Question;
import com.main.entity.Questionnaire;
import com.main.entity.User;
import com.main.repository.AnswerRepo;
import com.main.repository.DiagnosisRepo;
import com.main.repository.QuestionRepo;
import com.main.repository.QuestionnaireRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionnaireService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());

    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuestionnaireRepo questionnaireRepo;
    @Autowired
    private DiagnosisRepo diagnosisRepo;
    @Autowired
    private AnswerRepo answerRepo;


    public List<Question> getAllQuestions() {
        return questionRepo.findAllByOrderById();
    }

    public void saveNewQuestionnaire(Questionnaire questionnaire) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        questionnaire.setUser(user);
        questionnaire.setDate(LocalDateTime.now());
        questionnaire.setProcessed(false);
        Long nextId = questionnaireRepo.getNextId();
        questionnaire.setId(nextId);
        questionnaire.getAnswers().forEach(answer -> answer.setQuestionnaire(questionnaire));
        Questionnaire questionnaireSaved = questionnaireRepo.save(questionnaire);
        LOGGER.info("New questionnaire created{}", questionnaireSaved.toString());
    }

    public void saveExistedQuestionnaire(Questionnaire questionnaire, Long id) {
        Optional<Questionnaire> questionnaireFromDb = questionnaireRepo.findById(id);
        //TODO add exception wtih handler
        questionnaire.getAnswers().forEach(answer -> answer.setQuestionnaire(questionnaireFromDb.get()));
        questionnaireFromDb.get().setAnswers(questionnaire.getAnswers());
        questionnaireRepo.save(questionnaireFromDb.get());
    }
    public void saveExistedProcessedQuestionnaire(Questionnaire questionnaire, Long id){
        saveExistedQuestionnaire(questionnaire, id);
        Optional<Questionnaire> questionnaireFromDb = questionnaireRepo.findById(id);
        questionnaireFromDb.get().setProcessed(true);
        questionnaireFromDb.get().setDiagnosis(questionnaire.getDiagnosis());
        questionnaireRepo.save(questionnaireFromDb.get());

    }

    public List<Questionnaire> getAllUsersQuestionnaires(User... user) {
        User userFromSecurityContext = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Questionnaire> questionnaires = questionnaireRepo.findAllByUserOrderByDate(userFromSecurityContext);
        return questionnaires;
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
            return new Questionnaire();
        }

        Questionnaire existedQuestionnaire = questionnaire.get();
        Collections.sort(existedQuestionnaire.getAnswers(), (a1, a2) -> a1.getQuestion().getId().compareTo(a2.getQuestion().getId()));
        return questionnaire.orElseGet(Questionnaire::new);
    }

    public boolean operateQuestionnaireAnswers(Questionnaire questionnaire, boolean isNew, Long questionnaireId) {
        if (!Questionnaire.isFullFilled(questionnaire)) {
            return false;
        }

        List<Long> allIds = diagnosisRepo.getAllIds();
        Random random = new Random();
        Diagnosis diagnosis = diagnosisRepo.findById(allIds.get(random.nextInt(allIds.size()-1))).get();
        questionnaire.setDiagnosis(diagnosis);
        questionnaire.setProcessed(true);
        if (isNew) {
            saveNewQuestionnaire(questionnaire);
        }
        saveExistedProcessedQuestionnaire(questionnaire, questionnaireId);
        return true;
    }

}
