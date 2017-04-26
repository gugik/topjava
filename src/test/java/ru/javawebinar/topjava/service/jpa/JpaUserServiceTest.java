package ru.javawebinar.topjava.service.jpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by admin on 24.04.2017.
 */

@ActiveProfiles(Profiles.JPA)

public class JpaUserServiceTest extends UserServiceTest {

    @Override
    @Test(expected = UnsupportedOperationException.class)

    public void testGetWithMeal() throws Exception {
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL1, ADMIN_MEAL2), service.getWithMeal(ADMIN_ID).getMeals());
    }
}
