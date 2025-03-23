package com.example4._3.Task14_Musatov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;

//    private List<Message> messages = new ArrayList<>(Arrays.asList(
//            new Message(1, "Сообщение", "Привет, как дела?", LocalDateTime.now())
//    ));


    //  ПОЛУЧЕНИЕ(ВОЗВРАТ)
    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }

    // ПОЛУЧЕНИЕ/ВОЗВРАТ ПО ПАРАМЕТРУ(ID)
    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return repository.findById(id);
    }

    //ОТПРАВКА(ДОБАВЛЕНИЕ)
    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        repository.save(message);
        return message;
    }

//  ИЗМЕНЕНИЕ ПО(ID)

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {

        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        message.setId(id);
        return new ResponseEntity(repository.save(message), status);
    }


    //УДАЛЕНИЕ
    @DeleteMapping("/message/{id}")
    public void deletePerson(@PathVariable int id) {

        repository.deleteById(id);
    }

}
