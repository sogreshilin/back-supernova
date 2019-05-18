package ru.nsu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByAuthorId(long authorId);
}
