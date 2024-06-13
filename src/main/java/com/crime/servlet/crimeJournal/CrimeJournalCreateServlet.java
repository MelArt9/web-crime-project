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

@WebServlet("/journal/create")
public class CrimeJournalCreateServlet extends HttpServlet {

    private final CrimeJournalService crimeJournalService =
            new CrimeJournalService(new CrimeJournalRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/journal/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        Date date_crime;
        try {
            date_crime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date_crime"));
        } catch (ParseException e) {
            throw new ServletException("Error parsing date", e);
        }
        Boolean is_closed = Boolean.parseBoolean(req.getParameter("is_closed"));

        Profile profile = (Profile) req.getSession().getAttribute("profile");
        Long profile_id = profile.getId();

        CrimeJournal crimeJournal = new CrimeJournal();
        crimeJournal.setDescription(description);
        crimeJournal.setDate_crime(date_crime);
        crimeJournal.setIs_closed(is_closed);
        crimeJournal.setProfile_id(profile_id);

        crimeJournalService.create(crimeJournal);
        resp.sendRedirect(req.getContextPath() + "/journal/list");
    }
}