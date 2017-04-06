package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((meal) -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        else if (get(meal.getId(),userId)==null) return  null;
        meal.setUserId(userId);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (get(id,userId)==null) return false;
        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal=repository.get(id);
        return userId==meal.getUserId()? meal: null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getAll(userId,LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        Collection<Meal> collection=repository.values()
                .stream()
                .filter(meal -> meal.getUserId()==userId)
                .filter(meal -> (DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)))
                .sorted(((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime())))
                .collect(Collectors.toList());
        return collection.isEmpty()? Collections.EMPTY_LIST:collection;
    }
}

