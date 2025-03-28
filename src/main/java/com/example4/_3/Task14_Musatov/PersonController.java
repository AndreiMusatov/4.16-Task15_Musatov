package com.example4._3.Task14_Musatov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

//    private List<Person> persons = new ArrayList<>(Arrays.asList(
//            new Person(1, "Ivan", "Ivanovich", "Ivanov", LocalDate.of(1999, 2,3)),
//            new Person(2, "Петр", "Петрович", "Петров", LocalDate.of(2002, 2,2)),
//            new Person(3, "Евгений", "Васильевич", "Васин", LocalDate.of(2005, 4,8)),
//            new Person(4, "Максим", "Яковлевич", "Окопский", LocalDate.of(1978, 6,5))
//    ));

    // ПОЛУЧЕНИЕ(ВОЗВРАТ)
    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    // ПОЛУЧЕНИЕ/ВОЗВРАТ ПО ПАРАМЕТРУ(ID)
    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    //ОТПРАВКА(ДОБАВЛЕНИЕ)
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

// ОБНОВЛЕНИЕПО ПАРАМЕТРУ(ID)

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        person.setId(id);
        return new ResponseEntity(repository.save(person), status);
    }


    //УДАЛЕНИЕ
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }

}