package com.revature.crs.Registration;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegistrationRepository implements Crudable<Registration> {


    @Override
    public boolean update(Registration updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Registration> findAll() {
        return List.of();
    }

    @Override
    public Registration create(Registration newObject) throws InvalidInputException {
        return null;
    }

    @Override
    public Registration findById(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from registrations where registration_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) throw new DataNotFoundException("No registration found.");
            return generateRegistrationFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Registration generateRegistrationFromResultSet(ResultSet resultSet) throws SQLException {
        Registration registration = new Registration();
        registration.setRegistrationID(resultSet.getInt("registration_id"));
        registration.setRegistrationID(resultSet.getInt("course"));
        registration.setRegistrationID(resultSet.getInt("student"));
        registration.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
        return registration;
    }
}
