package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;

//I believe this tells the JPA repository what type of object the table it interracts with should be put into
// And I believe the second parameter is for telling it what type it should expect for the primary key.
public interface MessageRepository extends JpaRepository<Message,Integer> {
    // public Message findByMessageId(long Id);
}
