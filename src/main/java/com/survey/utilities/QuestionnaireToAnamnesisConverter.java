package com.survey.utilities;

import com.google.gson.*;
import com.survey.entity.Answer;
import com.survey.entity.Questionnaire;
import com.survey.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionnaireToAnamnesisConverter {
    private static Gson gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
    }

    public static JsonObject convert(Questionnaire questionnaire) {
        JsonObject jsonQuestionnaire = new JsonObject();
        jsonQuestionnaire.add("patient", getJsonPatient(questionnaire.getUser()));

        for (Answer answer : questionnaire.getAnswers()) {
            String description = answer.getQuestion().getDescription();
            if (answer.isAnswerInList()) {
                List<String> textList = answer.getTextList()
                                              .stream()
                                              .filter(a -> !"null".equals(a))
                                              .collect(Collectors.toList());
                jsonQuestionnaire.addProperty(description, gsonBuilder.toJson(textList));
            } else {
                jsonQuestionnaire.addProperty(description, answer.getText());
            }
        }

        return jsonQuestionnaire;
    }

    private static JsonObject getJsonPatient(User user) {
        JsonObject patient = new JsonObject();
        patient.addProperty("name", user.getFullName());
        return patient;
    }

}
