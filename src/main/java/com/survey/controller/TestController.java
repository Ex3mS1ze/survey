package com.survey.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.survey.entity.Question;
import com.survey.entity.Questionnaire;
import com.survey.entity.Views;
import com.survey.repository.QuestionnaireRepo;
import com.survey.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class TestController {
    private final QuestionnaireService questionnaireService;
    private final QuestionnaireRepo questionnaireRepo;
    private final ObjectMapper mapper;

    @Autowired
    public TestController(QuestionnaireService questionnaireService, QuestionnaireRepo questionnaireRepo) {
        this.questionnaireService = questionnaireService;
        this.questionnaireRepo = questionnaireRepo;

        mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        mapper.registerModule(new JavaTimeModule());
    }

    @GetMapping("/test")
    public String getTestPage(Model model, @RequestParam(value = "typeName", required = false) String typeName) {
        Questionnaire questionnaire = questionnaireService.getNewQuestionnaireByTypeName(typeName);
        Set<String> categories = questionnaire.getAllCategories();

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("categories", categories);

        return "test";
    }

    @PostMapping("/test")
    public String saveChanges(@RequestParam("isNew") Boolean isNew,
                              @RequestParam(value = "patientId", required = false) Long patientId,
                              @RequestParam(value = "questionnaireId", required = false) Long questionnaireId,
                              @ModelAttribute("questionnaire") Questionnaire questionnaire) {
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
    public String operateQuestionnaire(@RequestParam("isNew") Boolean isNew,
                                       @RequestParam(value = "questionnaireId", required = false) Long questionnaireId,
                                       @RequestParam(value = "patientId", required = false) Long patientId,
                                       @ModelAttribute("questionnaire") @Valid Questionnaire questionnaire,
                                       BindingResult bindingResult, Model model) {
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

        model.addAttribute("questionnaires", questionnaires);

        return "test-history";
    }

    @GetMapping("/test/edit")
    public String editQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId,
                                    @RequestParam(value = "patientId", required = false) Long patientId,
                                    @RequestParam("action") String action, Model model) {
        switch (action) {
            case "delete":
                questionnaireService.deleteQuestionnaireById(questionnaireId);
                return patientId != null ?
                        "redirect:/patient/profile?patientId=" + patientId : "redirect:/test/history";
            case "edit": {
                Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
                Set<String> categories = questionnaire.getAllCategories();

                model.addAttribute("categories", categories);
                model.addAttribute("questionnaire", questionnaire);

                return "test-edit";
            }
            default:
                return "redirect:/test/history";
        }
    }

    @GetMapping("/test/view")
    public String viewQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId, Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questionnaire.getType().getQuestions());
        model.addAttribute("categories", questionnaire.getAllCategories());

        return "test-view";
    }
    //TODO remove or PreAuthorize
    @GetMapping("/load_test_history")
    public ResponseEntity<?> loadTestHistory(@RequestParam Map<String, String> allRequestParams,
                                             Model model) throws JsonProcessingException {
        PageRequest page = PageRequest.of(Integer.parseInt(allRequestParams.get("draw")),
                                          Integer.parseInt(allRequestParams.get("length")));
        Page<Questionnaire> questionnaires = questionnaireRepo.findAll(page);

        String jsonString = mapper.setConfig(mapper.getSerializationConfig())
                                  .writerWithView(Views.WithoutAnswersQuestionsTypeUser.class)
                                  .withDefaultPrettyPrinter()
                                  .writeValueAsString(questionnaires.get().collect(Collectors.toList()));
        return ResponseEntity.ok(jsonString);
    }

}
