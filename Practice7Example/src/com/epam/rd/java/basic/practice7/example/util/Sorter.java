package com.epam.rd.java.basic.practice7.example.util;

import java.util.Collections;
import java.util.Comparator;

import com.epam.rd.java.basic.practice7.example.constants.Constants;
import com.epam.rd.java.basic.practice7.example.controller.DOMController;
import com.epam.rd.java.basic.practice7.example.entity.Answer;
import com.epam.rd.java.basic.practice7.example.entity.Question;
import com.epam.rd.java.basic.practice7.example.entity.Test;

public class Sorter {

	public static final Comparator<Question> SORT_QUESTIONS_BY_QUESTION_TEXT = (o1, o2) -> o1.getQuestionText().compareTo(o2.getQuestionText());

	public static final Comparator<Question> SORT_QUESTIONS_BY_ANSWERS_NUMBER = (o1, o2) -> o1.getAnswers().size() - o2.getAnswers().size();

	public static final Comparator<Answer> SORT_ANSWERS_BY_CONTENT = (o1, o2) -> o1.getContent().compareTo(o2.getContent());

	public static final Comparator<Answer> SORT_ANSWERS_BY_CORRECT = (o1, o2) -> {
		if (o1.isCorrect() && !o2.isCorrect()) {
			return -1;
		}
		if (o2.isCorrect() && !o1.isCorrect()) {
			return 1;
		}
		return 0;
	};

	public static final void sortQuestionsByQuestionText(Test test) {
		Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_QUESTION_TEXT);
	}

	public static final void sortQuestionsByAnswersNumber(Test test) {
		Collections.sort(test.getQuestions(), SORT_QUESTIONS_BY_ANSWERS_NUMBER);
	}

	public static final void sortAnswersByContent(Test test) {
		for (Question question : test.getQuestions()) {
			Collections.sort(question.getAnswers(), SORT_ANSWERS_BY_CONTENT);
		}
	}

	public static final void sortAnswersByCorrect(Test test) {
		for (Question question : test.getQuestions()) {
			Collections.sort(question.getAnswers(), SORT_ANSWERS_BY_CORRECT);
		}
	}

	public static void main(String[] args) throws Exception {
		DOMController domController = new DOMController(
				Constants.VALID_XML_FILE);
		domController.parse(false);
		Test test = domController.getTest();
		Sorter.sortQuestionsByQuestionText(test);
		System.out.println(test);
		Sorter.sortAnswersByContent(test);
		System.out.println(test);
	}
}