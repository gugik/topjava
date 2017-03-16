package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

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
        List<UserMealWithExceed> rez=getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        List<UserMealWithExceed> rez2=getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        rez.forEach(System.out::println);
        System.out.println("with use of Stream");
        rez2.forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList,
                                                                   LocalTime startTime, LocalTime endTime,
                                                                   int caloriesPerDay) {

        List<UserMealWithExceed> resultList = new ArrayList<>();
        Map<LocalDate, Integer> map = new TreeMap<>();

        for (UserMeal meal : mealList) {
            LocalDate date = meal.getDateTime().toLocalDate();
            if (map.containsKey(date)) {
                map.put(date, map.get(date) + meal.getCalories());
            } else {
                map.put(date, meal.getCalories());
            }
        }

        for (UserMeal meal : mealList) {

            LocalDateTime date = meal.getDateTime();
            LocalTime time = meal.getDateTime().toLocalTime();

            if (time.isAfter(startTime) && time.isBefore(endTime)) {
                resultList.add(new UserMealWithExceed(date, meal.getDescription(), meal.getCalories(),
                        caloriesPerDay < map.get(meal.getDateTime().toLocalDate())));
            }
        }
        return resultList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream (List<UserMeal> mealList,
                                                                   LocalTime startTime, LocalTime endTime,
                                                                   int caloriesPerDay) {

        List<UserMealWithExceed> resultList = new ArrayList<>();
        Map<LocalDate, Integer> map = new TreeMap<>();

        mealList.forEach(meal -> map.put(meal.getDateTime().toLocalDate(), map.containsKey(meal.getDateTime().toLocalDate()) ?
                map.get(meal.getDateTime().toLocalDate()) + meal.getCalories() :
                meal.getCalories()));


        mealList.stream().
                filter(male -> (male.getDateTime().toLocalTime().isAfter(startTime) &&
                        male.getDateTime().toLocalTime().isBefore(endTime))).
                forEach(meal -> resultList.add(new UserMealWithExceed (meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesPerDay < map.get(meal.getDateTime().toLocalDate()))));

        return resultList;
    }
}
