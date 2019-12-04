package com.main.controller;

import com.main.entity.Answer;
import com.main.entity.Question;
import com.main.entity.Questionnaire;
import com.main.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
        model.addAttribute("categories", questionnaireService.getAllQuestionCategories());
        return "test";
    }

    @PostMapping("/test")
    public String saveChanges(@RequestParam("isNew") Boolean isNew, @RequestParam(value = "patientId", required = false) Long patientId, @RequestParam(value = "questionnaireId", required = false) Long questionnaireId, @ModelAttribute("questionnaire") Questionnaire questionnaire) {

        if (isNew) {
            questionnaireService.saveNewQuestionnaire(questionnaire);
        } else {
            questionnaireService.saveExistedQuestionnaire(questionnaire, questionnaireId);
        }

        if (patientId != null) {
            return "redirect:/patient/profile?patientId=" + patientId;
        }

        return "redirect:/test/history";
    }

    @PostMapping("/operate_test")
    public String operateQuestionnaire(@RequestParam("isNew") Boolean isNew, @RequestParam(value = "questionnaireId", required = false) Long questionnaireId, @RequestParam(value = "patientId", required = false) Long patientId, @ModelAttribute("questionnaire") @Valid Questionnaire questionnaire, BindingResult bindingResult, Model model) {

        boolean isOperated = questionnaireService.operateQuestionnaireAnswers(questionnaire, isNew, questionnaireId);
        if (bindingResult.hasErrors() || !isOperated) {
            List<Question> questions = questionnaireService.getAllQuestions();
            model.addAttribute("questions", questions);
            model.addAttribute("questionnaire", questionnaire);

            if (isNew) {
                return "test";
            }

            questionnaire.setId(questionnaireId);
            if (patientId != null) {
                return "test-edit";
            }

            return "test-edit";
        }

        if (patientId != null) {
            return "redirect:/patient/profile?patientId=" + patientId;
        }

        return "redirect:/test/history";
    }

    @GetMapping("/test/history")
    public String getTestHistory(Model model) {
        List<Questionnaire> questionnaires = questionnaireService.getAllUsersQuestionnaires();
        //        List<Questionnaire> questionnaires = questionnaireService.getAllQuestionnaires();

        model.addAttribute("questionnaires", questionnaires);
        return "test-history";
    }

    //TODO Change to POST
    @GetMapping("/test/edit")
    public String editQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId,
                                    @RequestParam(value = "patientId", required = false) Long patientId,
                                    @RequestParam("action") String action, Model model) {
        switch (action) {
            case "delete":
                questionnaireService.deleteQuestionnaireById(questionnaireId);
                return patientId != null ? "redirect:/patient/profile?patientId=" + patientId : "redirect:/test/history";
            case "edit": {
                Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
                List<Question> questions = questionnaireService.getAllQuestions();
                model.addAttribute("categories", questionnaireService.getAllQuestionCategories());
                model.addAttribute("questionnaire", questionnaire);
                model.addAttribute("questions", questions);
                return "test-edit";
            }
            default:
                return "redirect:/test/history";
        }
    }

    @GetMapping("/test/view")
    public String viewQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId, Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Question> questions = questionnaireService.getAllQuestions();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questions);
        model.addAttribute("categories", questionnaireService.getAllQuestionCategories());
        return "test-view";
    }

    /*@PostMapping("/load_test_history")
    public ResponseEntity<?> loadTestHistory() {
        List<Questionnaire> questionnaires = questionnaireService.getAllQuestionnaires();

        return ResponseEntity.ok(questionnaires);
    }*/
}
