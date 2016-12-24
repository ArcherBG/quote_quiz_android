package com.flatrocktechnology.android.famousquotequiz.model;

import java.io.Serializable;

public class QuizQuestionDao implements Serializable{

    private String question;
    private String corrAnswer;
    private String firstWrongAnswer;
    private String secondWrongAnswer;
    private String thirdWrongAnswer;

    public String getThirdWrongAnswer() {
        return thirdWrongAnswer;
    }

    public void setThirdWrongAnswer(String thirdWrongAnswer) {
        this.thirdWrongAnswer = thirdWrongAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrAnswer() {
        return corrAnswer;
    }

    public void setCorrAnswer(String corrAnswer) {
        this.corrAnswer = corrAnswer;
    }

    public String getFirstWrongAnswer() {
        return firstWrongAnswer;
    }

    public void setFirstWrongAnswer(String firstWrongAnswer) {
        this.firstWrongAnswer = firstWrongAnswer;
    }

    public String getSecondWrongAnswer() {
        return secondWrongAnswer;
    }

    public void setSecondWrongAnswer(String secondWrongAnswer) {
        this.secondWrongAnswer = secondWrongAnswer;
    }


}
