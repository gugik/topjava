package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;
    public Meal save(Meal meal) {
        LOG.info("create " + meal);
        checkNew(meal);
        return service.save(meal);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }
    public Meal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }
    public Collection<Meal> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }
}