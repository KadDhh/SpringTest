package com.example.springtest.repos;

import com.example.springtest.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByText(String text);
}