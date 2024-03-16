package com.dnicklas.quizzgame.repository;

import com.dnicklas.quizzgame.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT u FROM Question u WHERE u.topic = ?1")
    List<Question> findAllByWhereTopic(String topic);
}
