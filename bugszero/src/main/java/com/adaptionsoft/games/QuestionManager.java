package com.adaptionsoft.games;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.adaptionsoft.games.Category.*;

public class QuestionManager {
    private Map<Category, LinkedList<String>> questionMap = new HashMap<>();

    public QuestionManager() {
        Category[] categories = values();
        Arrays.stream(categories).forEach(this::addQuestions);
    }

    private void addQuestions(Category category) {
        questionMap.put(category, new LinkedList<>());
        for (int i = 0; i < 50; i++) {
            questionMap.get(category).addLast(category + " Question " + i);
        }
    }

    public void askQuestion(Category category) {
        System.out.println(questionMap.get(category).removeFirst());
    }
}
