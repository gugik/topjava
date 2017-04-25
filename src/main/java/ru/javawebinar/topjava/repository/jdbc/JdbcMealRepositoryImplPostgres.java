package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;

/**
 * Created by admin on 25.04.2017.
 */
@Repository
@Profile(Profiles.POSTGRES_DB)
public class JdbcMealRepositoryImplPostgres extends JdbcMealRepositoryImpl {
    public JdbcMealRepositoryImplPostgres(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }
}
