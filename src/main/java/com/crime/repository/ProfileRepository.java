package com.crime.repository;

import com.crime.entity.Profile;
import com.crime.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRepository {
    private static final String INSERT_QUERY_TEMPLATE =
            "INSERT INTO profile (login, password, email) VALUES (?, ?, ?)";
    private static final String SELECT_BY_LOGIN_QUERY_TEMPLATE =
            "SELECT id, login, password, email FROM profile WHERE login = ?";

    public int create(Profile profile) { // возвращает ID, поэтому void, а не Profile
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_QUERY_TEMPLATE);
            preparedStatement.setString(1, profile.getLogin());
            preparedStatement.setString(2, profile.getPassword());
            preparedStatement.setString(3, profile.getEmail());
            return  preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public Profile findByLogin(String login) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_BY_LOGIN_QUERY_TEMPLATE);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapProfile(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private Profile mapProfile(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();
        profile.setId(resultSet.getLong("id"));
        profile.setLogin(resultSet.getString("login"));
        profile.setPassword(resultSet.getString("password"));
        profile.setEmail(resultSet.getString("email"));
        return profile;
    }
}
