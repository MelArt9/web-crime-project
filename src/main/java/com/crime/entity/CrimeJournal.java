package com.crime.entity;

import java.util.Date;
import java.util.Objects;

public class CrimeJournal {
    private Long id;
    private String description;
    private Date date_crime;
    private Boolean is_closed;
    private Long profile_id;

    public CrimeJournal(Long id, String description, Date date_crime, Boolean is_closed, Long profile_id) {
        this.id = id;
        this.description = description;
        this.date_crime = date_crime;
        this.is_closed = is_closed;
        this.profile_id = profile_id;
    }

    public CrimeJournal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_crime() {
        return date_crime;
    }

    public void setDate_crime(Date date_crime) {
        this.date_crime = date_crime;
    }

    public Boolean getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(Boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrimeJournal that = (CrimeJournal) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(date_crime, that.date_crime) && Objects.equals(is_closed, that.is_closed) && Objects.equals(profile_id, that.profile_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date_crime, is_closed, profile_id);
    }
}