package com.epam.rd.java.basic.practice7.example.constants;

public enum XML {
	TEST("Test"), QUESTION("Question"), QUESTION_TEXT("QuestionText"), ANSWER("Answer"),

	CORRECT("correct");

	private String value;

	XML(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
