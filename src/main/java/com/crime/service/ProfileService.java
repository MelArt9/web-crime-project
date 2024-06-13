package com.crime.service;

import com.crime.entity.Profile;
import com.crime.exception.ValidationException;
import com.crime.repository.ProfileRepository;

public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile register(String login, String password, String email) {
        if (login == null || password == null || email == null || login.length() < 3 || password.length() < 8 || email.isEmpty()) {
            throw new ValidationException("Incorrect credentials format");
        }

        if (profileRepository.findByLogin(login) != null) {
            throw new ValidationException("User already exists!");
        }

        Profile profile = new Profile(null, login, password, email);
        profileRepository.create(profile);
        return profile;
    }

    public Profile doLogin(String login, String password, String email) {
        if (login == null || password == null || email == null || login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            throw new ValidationException("Login, password, and email must not be empty");
        }

        Profile profile = profileRepository.findByLogin(login);
        if (profile == null) {
            throw new ValidationException("User not found");
        }

        if (!profile.getPassword().equals(password) || !profile.getEmail().equals(email)) {
            throw new ValidationException("Invalid login credentials");
        }
        return profile;
    }
}
