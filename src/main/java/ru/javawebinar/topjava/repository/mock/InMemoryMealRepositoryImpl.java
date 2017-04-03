package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {
        Meal meal=repository.get(id);
        if (AuthorizedUser.id()==meal.getUserId()) {repository.remove(id); return true;}
        else return false;
    }

    @Override
    public Meal get(int id) {
        Meal meal=repository.get(id);
        if (AuthorizedUser.id()==meal.getUserId()) {repository.remove(id); return meal;}
        else return null;
    }

    @Override
    public Collection<Meal> getAll() {
        Collection<Meal> collection=repository.values()
                .stream()
                .filter(meal -> meal.getUserId()==AuthorizedUser.id())
                .sorted(((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime())))
                .collect(Collectors.toList());
        return collection.isEmpty()? Collections.EMPTY_LIST:collection;
    }
}

