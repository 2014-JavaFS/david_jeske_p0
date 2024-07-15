package com.revature.crs.Registration;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.revature.crs.util.CourseRegistrationFrontController.logger;

public class RegistrationRepository implements Crudable<Registration> {
    // Data Access Object (DAO)
    @Override
    public boolean update(Registration updatedRegistration) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update registrations " +
                    "set { registration_id = ?, course = ?, student = ?, registration_date = ?} " +
                    "where registration_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, updatedRegistration.getRegistrationId());
            preparedStatement.setInt(2, updatedRegistration.getCourseId());
            preparedStatement.setInt(3, updatedRegistration.getStudentId());
            preparedStatement.setDate(4, Date.valueOf(updatedRegistration.getRegistrationDate()));
            preparedStatement.setInt(5, updatedRegistration.getRegistrationId());

            logger.info(preparedStatement.toString());
            if (preparedStatement.executeUpdate(sql) == 0) throw new RuntimeException("Course not found.");
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete * from registrations where registration_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            logger.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new DataNotFoundException("Registration record not found.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Registration> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Registration> registrations = new ArrayList<>();
            String sql = "select * from registrations";

            logger.info(sql);
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) registrations.add(generateRegistrationFromResultSet(resultSet));
            return registrations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Registration create(Registration newRegistration) throws InvalidInputException {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into registrations values (default,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, newRegistration.getCourseId());
            preparedStatement.setInt(2, newRegistration.getStudentId());
            preparedStatement.setDate(3, Date.valueOf(newRegistration.getRegistrationDate()));

            logger.info(preparedStatement.toString());
            if (preparedStatement.executeUpdate() == 0) {
                logger.warn("registration: {} not added");
                throw new RuntimeException("Registration was unable to be added");
            }
            logger.info("registration: {} added");
            return newRegistration;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Registration findById(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from registrations where registration_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) throw new DataNotFoundException("Registration record not found.");
            return generateRegistrationFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Registration generateRegistrationFromResultSet(ResultSet resultSet) throws SQLException {
        Registration registration = new Registration();
        registration.setRegistrationId(resultSet.getInt("registration_id"));
        registration.setRegistrationId(resultSet.getInt("course"));
        registration.setRegistrationId(resultSet.getInt("student"));
        registration.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
        return registration;
    }
}
