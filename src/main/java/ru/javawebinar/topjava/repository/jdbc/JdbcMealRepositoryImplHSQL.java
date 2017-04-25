package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Created by admin on 25.04.2017.
 */
@Repository
@Profile(Profiles.HSQL_DB)
public class JdbcMealRepositoryImplHSQL extends JdbcMealRepositoryImpl {

    public JdbcMealRepositoryImplHSQL(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public <T> T convert(LocalDateTime dateTime) {
        return (T) Timestamp.valueOf(dateTime);
    }
}
