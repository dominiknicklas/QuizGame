package com.dnicklas.quizzgame.bizz;

import com.dnicklas.quizzgame.model.Question;
import com.dnicklas.quizzgame.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class InsertQuestions {
    private QuestionRepository qr;

    public InsertQuestions(QuestionRepository qr) {
        this.qr = qr;
    }

    public  void insertQuestions() {
        if(qr != null) {
            try {
                Files.lines(Path.of("/Users/dominik/IdeaProjects/quizz-game/questions.csv"))
                        .skip(1)
                        .map(s -> s.split(";"))
                        .map(Question::createQuestion)
                        .forEach(qr::save);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
