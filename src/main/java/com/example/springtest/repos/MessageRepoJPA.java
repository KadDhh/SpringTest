package com.example.springtest.repos;

import com.example.springtest.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepoJPA extends JpaRepository<Message, Long> {
    List<Message> findByText(String text);

    List<Message> findAllByOrderByIdDesc();

}
