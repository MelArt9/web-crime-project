package com.crime.service;

import com.crime.entity.CrimeJournal;
import com.crime.exception.ValidationException;
import com.crime.repository.CrimeJournalRepository;

import java.util.List;

public class CrimeJournalService {

    private final CrimeJournalRepository crimeJournalRepository;

    public CrimeJournalService(CrimeJournalRepository crimeJournalRepository) {
        this.crimeJournalRepository = crimeJournalRepository;
    }

    public List<CrimeJournal> findByProfileId(Long profileId) {
        return crimeJournalRepository.findByProfileId(profileId);
    }

    public boolean update(CrimeJournal crimeJournal) {
        return crimeJournalRepository.update(crimeJournal);
    }

    public CrimeJournal create(CrimeJournal crimeJournal) {
        return crimeJournalRepository.create(crimeJournal);
    }

    public boolean delete(Long id) {
        return crimeJournalRepository.delete(id);
    }


    public CrimeJournal findByProfileAndId(Long profileId, Long IdJournal) {
        CrimeJournal crimeJournal = crimeJournalRepository.findById(IdJournal);

        if (!crimeJournal.getProfile_id().equals(profileId)) {
            throw new ValidationException("This is not your crime under investigation!");
        }
        return crimeJournal;
    }
}