package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by admin on 26.03.2017.
 */
public interface MealDao {

    void save(Meal meal);

    void delete(int id);

    Meal getById(int id);

    Collection<Meal> getAll();

}
