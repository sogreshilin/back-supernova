package ru.nsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
