package ru.nsu.controller.suggestion;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.NotNull;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import ru.nsu.entity.Suggestion;

public class SuggestionResultSetExtractor implements ResultSetExtractor<Suggestion> {

    @Override
    public Suggestion extractData(ResultSet rs) throws SQLException, DataAccessException {
        return new Suggestion()
            .setId(rs.getLong("id"))
            .setSessionId(rs.getLong("session_id"))
            .setSuggestionOffset(rs.getLong("suggestion_offset"))
            .setEventIds((Long[]) rs.getArray("event_ids").getArray());
    }
}
