package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;


import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by admin on 10.04.2017.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceImplTest {
    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(ID_USER_BREAKFAST_30, USER_ID);
        MealTestData.MATCHER.assertEquals(MEAL_USER_BREAKFAST_30, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundGet() throws Exception {
        service.get(ID_USER_BREAKFAST_30, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ID_ADMIN_LUNCH_30, ADMIN_ID);
        MealTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(MEAL_ADMIN_SUPPER_30, MEAL_ADMIN_BREAKFAST_30), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(ID_ADMIN_LUNCH_30, USER_ID);
    }


    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(
                Arrays.asList(MEAL_USER_SUPPER_31,
                        MEAL_USER_LUNCH_31,
                        MEAL_USER_BREAKFAST_31),
                service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 31, 10, 0),
                        LocalDateTime.of(2015, Month.MAY, 31, 20, 0), USER_ID));
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(
                Arrays.asList(MEAL_ADMIN_SUPPER_30,
                        MEAL_ADMIN_LUNCH_30,
                        MEAL_ADMIN_BREAKFAST_30),
                service.getAll(ADMIN_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updated = MEAL_USER_LUNCH_30;
        updated.setDescription("Lunch");
        updated.setCalories(330);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(ID_USER_LUNCH_30, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Meal updated = MEAL_USER_BREAKFAST_31;
        updated.setCalories(330);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "New Завтрак", 900);
        Meal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(
                created,
                MEAL_ADMIN_SUPPER_30,
                MEAL_ADMIN_LUNCH_30,
                MEAL_ADMIN_BREAKFAST_30), service.getAll(ADMIN_ID));
    }
}