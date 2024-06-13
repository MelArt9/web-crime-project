package com.crime.servlet.crimeJournal;

import com.crime.entity.CrimeJournal;
import com.crime.entity.Profile;
import com.crime.repository.CrimeJournalRepository;
import com.crime.service.CrimeJournalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/journal/edit")
public class CrimeJournalEditServlet extends HttpServlet {

    private final CrimeJournalService crimeJournalService = new CrimeJournalService(new CrimeJournalRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Проверка, что это моя запись
        Long profileId = ((Profile) req.getSession().getAttribute("profile")).getId();
        CrimeJournal crime = crimeJournalService.findByProfileAndId(profileId, Long.valueOf(req.getParameter("Id")));

        req.setAttribute("id", crime.getId());
        req.setAttribute("description", crime.getDescription());
        req.setAttribute("date_crime", crime.getDate_crime());
        req.setAttribute("is_closed", crime.getIs_closed());

        getServletContext()
                .getRequestDispatcher("/journal/edit.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long profileId = ((Profile) req.getSession().getAttribute("profile")).getId();
        CrimeJournal crime = crimeJournalService.findByProfileAndId(profileId, id);

        crime.setDescription(req.getParameter("description"));

        String dateString = req.getParameter("date_crime");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = format.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        crime.setDate_crime(new java.sql.Date(parsedDate.getTime()));

        String isClosedParam = req.getParameter("is_closed");
        boolean isClosed = Boolean.parseBoolean(isClosedParam);
        crime.setIs_closed(isClosed);

        crimeJournalService.update(crime);
        resp.sendRedirect(req.getContextPath() + "/journal/list");
    }
}