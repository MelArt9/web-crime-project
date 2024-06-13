package com.crime.servlet.profile;

import com.crime.entity.Profile;
import com.crime.exception.ValidationException;
import com.crime.repository.ProfileRepository;
import com.crime.service.ProfileService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private ProfileService profileService = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("profile");

        // Проверяем, залогинен ли пользователь
        if (username != null) {
            response.sendRedirect(request.getContextPath() + "/journal/list");
        } else {
            request.getRequestDispatcher("/profile/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String target = null;

        try {
            Profile profile = profileService.doLogin(login, password, email);
            req.getSession().setAttribute("profile", profile);
            resp.sendRedirect(req.getContextPath() + "/journal/list");
            return;
        } catch (ValidationException exception) {
            req.setAttribute("errorMessage", exception.getMessage());
            target = "/profile/login.jsp";
        }

        RequestDispatcher requestDispatcher =
                getServletContext().getRequestDispatcher(target);
        requestDispatcher.forward(req, resp);
    }
}