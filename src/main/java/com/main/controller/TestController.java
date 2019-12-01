package com.main.controller;

import com.main.entity.Answer;
import com.main.entity.Question;
import com.main.entity.Questionnaire;
import com.main.entity.User;
import com.main.repository.QuestionRepo;
import com.main.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("/test")
    public String getTestPage(Model model) {
        List<Question> questions = questionnaireService.getAllQuestions();
        model.addAttribute("questions", questions);
        Questionnaire questionnaire = new Questionnaire();
        for (int i = questions.size(); i > 0; i--) {
            questionnaire.getAnswers().add(new Answer());
        }
        model.addAttribute("questionnaire", questionnaire);
        return "test";
    }

    @PostMapping("/test")
    public String saveAnswers(@RequestParam("isNew") Boolean isNew, @RequestParam(value = "questionnaireId", required = false) Long questionnaireId, @ModelAttribute("questionnaire") Questionnaire questionnaire) {

        if (isNew) {
            questionnaireService.saveNewQuestionnaire(questionnaire);
        } else {
            questionnaireService.saveExistedQuestionnaire(questionnaire, questionnaireId);
        }

        return "redirect:/test/history";
    }

    @GetMapping("/test/history")
    public String getTestHistory(Model model) {
//        List<Questionnaire> questionnaires = questionnaireService.getAllUsersQuestionnaires();
        List<Questionnaire> questionnaires = questionnaireService.getAllQuestionnaires();

        model.addAttribute("questionnaires", questionnaires);
        return "test-history";
    }

    //TODO Change to POST
    @GetMapping("/test/history/edit")
    public String editQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId, @RequestParam("action") String action, Model model) {
        switch (action) {
            case "delete":
                questionnaireService.deleteQuestionnaireById(questionnaireId);
                return "redirect:/test/history";
            case "edit": {
                Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
                List<Question> questions = questionnaireService.getAllQuestions();
                model.addAttribute("questionnaire", questionnaire);
                model.addAttribute("questions", questions);
                return "test-edit";
            }
            default:
                return "redirect:/test/history";
        }
    }

    @GetMapping("/test/history/view")
    public String viewQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId, Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Question> questions = questionnaireService.getAllQuestions();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questions);
        return "test-view";
    }

}
