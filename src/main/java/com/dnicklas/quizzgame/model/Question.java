package com.dnicklas.quizzgame.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String question;

    @Column(nullable = false)
    private String answersOne;

    @Column(nullable = false)
    private String answerTwo;

    @Column(nullable = false)
    private String answerThree;

    @Column(nullable = false)
    private String answerFour;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    private String topic;

    public Question(String question, String answersOne, String answerTwo, String answerThree, String answerFour, String correctAnswer, String topic) {
        this.question = question;
        this.answersOne = answersOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }

    public Question(Long id, String question, String answersOne, String answerTwo, String answerThree, String answerFour, String correctAnswer, String topic) {
        this.id = id;
        this.question = question;
        this.answersOne = answersOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }

    public Question() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswersOne() {
        return answersOne;
    }

    public void setAnswersOne(String answersOne) {
        this.answersOne = answersOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static Question createQuestion(String[] arr) {
        String question = arr[0];
        String answerOne = arr[1].strip();
        String answerTwo = arr[2].strip();
        String answerThree = arr[3].strip();
        String answerFour = arr[4].strip();
        String correctAnswer = arr[5].strip();
        String topic = arr[6].replace(" ", "_").toUpperCase();
        return new Question(question, answerOne, answerTwo, answerThree, answerFour, correctAnswer, topic);
    }
}
