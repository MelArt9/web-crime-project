package com.crime.servlet.crimeJournal;

import com.crime.repository.CrimeJournalRepository;
import com.crime.service.CrimeJournalService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/journal/delete")
public class CrimeJournalDeleteServlet extends HttpServlet {

    private final CrimeJournalService crimeJournalService =
            new CrimeJournalService(new CrimeJournalRepository());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is missing");
            return;
        }

        Long id = Long.valueOf(idParam);
        boolean deleted = crimeJournalService.delete(id);

        if (deleted) {
            resp.sendRedirect(req.getContextPath() + "/journal/list");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to delete the crime journal entry");
        }
    }
}
