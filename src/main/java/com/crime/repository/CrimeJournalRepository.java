package com.crime.repository;

import com.crime.entity.CrimeJournal;
import com.crime.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrimeJournalRepository {

    private static final String SELECT_BY_PROFILE_ID_QUERY_TEMPLATE =
            "SELECT id, description, date_crime, is_closed, profile_id FROM crime_journal WHERE profile_id = ? ORDER BY id";

    private static final String SELECT_BY_ID_QUERY_TEMPLATE =
            "SELECT id, description, date_crime, is_closed, profile_id FROM crime_journal WHERE id = ?";

    private static final String UPDATE_BY_ID_QUERY_TEMPLATE =
            "UPDATE crime_journal SET description = ?, date_crime = ?, is_closed = ? WHERE id = ?";

    private static final String INSERT_QUERY_TEMPLATE =
            "INSERT INTO crime_journal (description, date_crime, is_closed, profile_id) VALUES (?, ?, ?, ?)";

    private static final String DELETE_QUERY_TEMPLATE =
            "DELETE FROM crime_journal WHERE id = ?";

    public List<CrimeJournal> findByProfileId(Long profileId) {
        Connection connection = ConnectionFactory.getConnection();
        List<CrimeJournal> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_BY_PROFILE_ID_QUERY_TEMPLATE);
            preparedStatement.setLong(1, profileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(mapCrimeJournal(resultSet));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return result;
    }

    public CrimeJournal findById(Long Id) {
        Connection connection = ConnectionFactory.getConnection();
        List<CrimeJournal> result = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_BY_ID_QUERY_TEMPLATE);
            preparedStatement.setLong(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapCrimeJournal(resultSet);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return null;
    }

    public boolean update(CrimeJournal crimeJournal) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UPDATE_BY_ID_QUERY_TEMPLATE);
            preparedStatement.setString(1, crimeJournal.getDescription());
            preparedStatement.setDate(2, (Date) crimeJournal.getDate_crime());
            preparedStatement.setBoolean(3, crimeJournal.getIs_closed());
            preparedStatement.setLong(4, crimeJournal.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public CrimeJournal create(CrimeJournal crimeJournal) {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_TEMPLATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, crimeJournal.getDescription());
            preparedStatement.setDate(2, new java.sql.Date(crimeJournal.getDate_crime().getTime()));
            preparedStatement.setBoolean(3, crimeJournal.getIs_closed());
            preparedStatement.setLong(4, crimeJournal.getProfile_id());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating crime journal failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    crimeJournal.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating crime journal failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return crimeJournal;
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY_TEMPLATE)) {

            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private CrimeJournal mapCrimeJournal(ResultSet resultSet) throws SQLException {
        CrimeJournal crimeJournal = new CrimeJournal();
        crimeJournal.setId(resultSet.getLong("id"));
        crimeJournal.setDescription(resultSet.getString("description"));
        crimeJournal.setDate_crime(resultSet.getDate("date_crime"));
        crimeJournal.setIs_closed(resultSet.getBoolean("is_closed"));
        crimeJournal.setProfile_id(resultSet.getLong("profile_id"));
        return crimeJournal;
    }
}