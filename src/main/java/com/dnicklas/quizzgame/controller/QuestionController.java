package com.dnicklas.quizzgame.controller;

import com.dnicklas.quizzgame.bizz.InsertQuestions;
import com.dnicklas.quizzgame.model.Answer;
import com.dnicklas.quizzgame.model.Question;
import com.dnicklas.quizzgame.model.TopicAndAmount;
import com.dnicklas.quizzgame.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class QuestionController {
    private QuestionRepository repo;

    private List<Question> questions = new ArrayList<>();

    private int counter = 0;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;


    public QuestionController(QuestionRepository repo) {
        this.repo = repo;
    }

    @RequestMapping("/")
    public String gettingStarted(Model model) {
        model.addAttribute("topicAndAmount" ,new TopicAndAmount());
        return "home";
    }

    @PostMapping("/gettingStarted")
    public String playGame(@ModelAttribute("topicAndAmount") TopicAndAmount topicAndAmount, Model model) {
        List<Question> allByWhereTopic = repo.findAllByWhereTopic(topicAndAmount.getTopic());
        Collections.shuffle(allByWhereTopic);
        questions = new ArrayList<>();
        int amountOfQuestions = Integer.parseInt(topicAndAmount.getAmountOfQuestions());
        for(int counter = 0; counter < amountOfQuestions; counter++) {
            Question question = allByWhereTopic.get(counter);
            questions.add(question);
        }
        if (counter < questions.size()) {
            getNextQuestion(model);
            return "play";
        }
        return "gameOver";
    }


    @PostMapping("/processAnswer")
    public String processAnswer(@ModelAttribute("answer") Answer answer, Model model) {
        String userAnswer = answer.getAnswer().strip();
        String correctAnswer = answer.getCorrectAnswer().strip();
        if(userAnswer.equals(correctAnswer)) {
            correctAnswers++;
            model.addAttribute("correctAnswerMessage", "Congratulations your answer was correct!");
            return "answer-correct-page";
        }
        wrongAnswers++;
        model.addAttribute("wrongAnswerMessage", "Sorry - your answer was incorrect");
        model.addAttribute("answer", answer);
        return "answer-wrong-page";
    }

    @RequestMapping("/nextQuestion")
    public String showNextQuestion(Model model) {
        if (counter < questions.size()) {
            getNextQuestion(model);
            return "play";
        }
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("wrongAnswers", wrongAnswers);
        resetValues();
        return "gameOver";
    }

    private void resetValues() {
        counter = 0;
        correctAnswers = 0;
        wrongAnswers = 0;
        questions = null;
    }

    private void getNextQuestion(Model model) {
        Question question = questions.get(counter);
        List<String> answers = new ArrayList<>(List.of(question.getAnswersOne(), question.getAnswerTwo(), question.getAnswerThree(), question.getAnswerFour()));
        Collections.shuffle(answers);
        question.setAnswersOne(answers.get(0));
        question.setAnswerTwo(answers.get(1));
        question.setAnswerThree(answers.get(2));
        question.setAnswerFour(answers.get(3));
        //shuffle answers
        counter++;
        model.addAttribute("question", question);
        model.addAttribute("questionCounter", counter);
        model.addAttribute("answer", new Answer());
    }
}
