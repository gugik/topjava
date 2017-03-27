package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

/**
 * Created by admin on 26.03.2017.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(UserServlet.class);
    private static final MealDao mealDao = new MealDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        String action = request.getParameter("action");
        if (action == null) {
        } else if (action.equals("delete")) {
            mealDao.delete(Integer.valueOf(request.getParameter("id")));
            response.sendRedirect("meals");
        } else if (action.equals("edit")) {
            request.setAttribute("edit", true);
            request.setAttribute("mealForEdit", mealDao.getById(Integer.parseInt(request.getParameter("id"))));
        }

        if (action == null || action.equals("edit")) {
            List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("meals", mealsWithExceeded);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(
                id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("date")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories")));
        mealDao.save(meal);
        resp.sendRedirect("meals");
    }
}
