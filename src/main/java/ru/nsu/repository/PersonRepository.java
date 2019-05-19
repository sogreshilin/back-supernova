package ru.nsu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByVkId(String vkId);
}
