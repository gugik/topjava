package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by admin on 24.04.2017.
 */

@ActiveProfiles(Profiles.JDBC)

public class JdbcMealServiceTest extends MealServiceTest {

    @Override
    public void testGetWithUser() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        service.getWithUser(MEAL1_ID, USER_ID).getUser();
    }
}
