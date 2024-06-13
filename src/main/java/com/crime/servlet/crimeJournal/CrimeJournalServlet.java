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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/journal/list")
public class CrimeJournalServlet extends HttpServlet {

    private final CrimeJournalService crimeJournalService = new CrimeJournalService(new CrimeJournalRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long profileId = ((Profile) req.getSession().getAttribute("profile")).getId();
        List<CrimeJournal> crimes = crimeJournalService.findByProfileId(profileId);
        req.setAttribute("crimes", crimes);
        getServletContext()
                .getRequestDispatcher("/journal/list.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Здесь можно добавить обработку POST-запросов, например, для добавления новых преступлений в список
    }
}