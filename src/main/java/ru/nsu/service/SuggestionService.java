package ru.nsu.service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.JDBCType;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.nsu.controller.suggestion.SuggestionResultSetExtractor;
import ru.nsu.entity.Suggestion;

@Service
@RequiredArgsConstructor
public class SuggestionService {
    private final JdbcTemplate jdbcTemplate;

    public void saveSuggestion(Suggestion suggestion) {

        Array idSqlArray = jdbcTemplate.execute(
            (Connection c) -> c.createArrayOf(JDBCType.BIGINT.getName(), suggestion.getEventIds())
        );

        jdbcTemplate.update("INSERT INTO suggestion (session_id, suggestion_offset, event_ids) values (?, ?, ?)",
            suggestion.getSessionId(), suggestion.getSuggestionOffset(), idSqlArray);


    }

    public Suggestion getSuggestion(Long sessionId) {
        return jdbcTemplate.query("SELECT * FROM suggestion WHERE session_id = ?",
            new Object[]{sessionId},
            new SuggestionResultSetExtractor()
        );
    }
}
