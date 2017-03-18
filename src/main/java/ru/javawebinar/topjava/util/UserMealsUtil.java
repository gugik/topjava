package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> rez = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        List<UserMealWithExceed> rez2 = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        rez.forEach(System.out::println);
        System.out.println("with use of Stream");
        rez2.forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
                                                                   LocalTime startTime, LocalTime endTime,
                                                                   int caloriesPerDay) {

        Map<LocalDate, Integer> map = new TreeMap<>();

        mealList.forEach(meal -> map.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        List<UserMealWithExceed> resultList = new ArrayList<>();

        mealList.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                resultList.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesPerDay < map.get(meal.getDateTime().toLocalDate())));
            }
        });

        return resultList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList,
                                                                         LocalTime startTime, LocalTime endTime,
                                                                         int caloriesPerDay) {

        Map<LocalDate, Integer> map =
                mealList.stream().collect(Collectors.groupingBy(meal -> meal.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream().
                filter(male -> (TimeUtil.isBetween(male.getDateTime().toLocalTime(), startTime, endTime))).
                map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesPerDay < map.get(meal.getDateTime().toLocalDate())))
                .collect(Collectors.toList());

    }
}
