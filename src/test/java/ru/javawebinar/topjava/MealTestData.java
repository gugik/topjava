package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int ID_USER_BREAKFAST_30 = START_SEQ + 2;
    public static final int ID_USER_LUNCH_30 = START_SEQ + 3;
    public static final int ID_USER_SUPPER_30 = START_SEQ + 4;
    public static final int ID_USER_BREAKFAST_31 = START_SEQ + 5;
    public static final int ID_USER_LUNCH_31 = START_SEQ + 6;
    public static final int ID_USER_SUPPER_31 = START_SEQ + 7;
    public static final int ID_ADMIN_BREAKFAST_30 = START_SEQ + 8;
    public static final int ID_ADMIN_LUNCH_30 = START_SEQ + 9;
    public static final int ID_ADMIN_SUPPER_30 = START_SEQ + 10;

    public static final Meal MEAL_USER_BREAKFAST_30 = new Meal(ID_USER_BREAKFAST_30, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_USER_LUNCH_30 = new Meal(ID_USER_LUNCH_30, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 800);
    public static final Meal MEAL_USER_SUPPER_30 = new Meal(ID_USER_SUPPER_30, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_USER_BREAKFAST_31 = new Meal(ID_USER_BREAKFAST_31, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 550);
    public static final Meal MEAL_USER_LUNCH_31 = new Meal(ID_USER_LUNCH_31, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_USER_SUPPER_31 = new Meal(ID_USER_SUPPER_31, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 1000);

    public static final Meal MEAL_ADMIN_BREAKFAST_30 = new Meal(ID_ADMIN_BREAKFAST_30, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 700);
    public static final Meal MEAL_ADMIN_LUNCH_30 = new Meal(ID_ADMIN_LUNCH_30, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1200);
    public static final Meal MEAL_ADMIN_SUPPER_30 = new Meal(ID_ADMIN_SUPPER_30, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 2000);


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories()
                    ))
    );

}
