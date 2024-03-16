package com.dnicklas.quizzgame.model;

public class TopicAndAmount {
    private String Topic;
    private String amountOfQuestions;

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getAmountOfQuestions() {
        return amountOfQuestions;
    }

    public void setAmountOfQuestions(String amountOfQuestions) {
        this.amountOfQuestions = amountOfQuestions;
    }
}
