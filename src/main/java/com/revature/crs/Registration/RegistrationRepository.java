package com.revature.crs.Registration;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.revature.crs.util.CourseRegistrationFrontController.logger;

public class RegistrationRepository implements Crudable<Registration> {
    // Data Access Object (DAO)
    @Override
    public boolean update(Registration updatedRegistration) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update registrations " +
                    "set registration_id = ?, course = ?, student = ?, registration_date = ? " +
                    "where registration_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, updatedRegistration.getRegistrationId());
            preparedStatement.setInt(2, updatedRegistration.getCourseId());
            preparedStatement.setInt(3, updatedRegistration.getStudentId());
            preparedStatement.setDate(4, Date.valueOf(updatedRegistration.getRegistrationDate()));
            preparedStatement.setInt(5, updatedRegistration.getRegistrationId());
            logger.info("updating info to: {}", updatedRegistration);
            logger.info("SQL: {}", preparedStatement);
            if (preparedStatement.executeUpdate() == 0) throw new RuntimeException("Registration record not found.");
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            Registration registration = findById(id);
            if (registration.getRegistrationDate().plusWeeks(2).isAfter(LocalDate.now())) {
                //checks that registration is within window
                CallableStatement callableStatement = conn.prepareCall("call cancel_registration(?);");
                callableStatement.setInt(1, id);
                logger.info("SQL: {}", callableStatement);
                callableStatement.execute();
                callableStatement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Registration> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Registration> registrations = new ArrayList<>();
            String sql = "select * from registrations";

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
            CallableStatement callableStatement = conn.prepareCall("call new_registration( ?, ? );");
            callableStatement.setInt(1, newRegistration.getCourseId());
            callableStatement.setInt(2, newRegistration.getStudentId());
            callableStatement.execute();
            callableStatement.close();
            return null;
            //TODO: probably shouldnt be just returning a null but uhhhhhh it works?
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

            logger.info("SQL: {}", preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) throw new DataNotFoundException("Registration record not found.");
            return generateRegistrationFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Registration> getEnrolled(int curUser) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Registration> registrations = new ArrayList<>();
            String sql = "select r.* from registrations r " +
                    "inner join users u on u.user_id = r.student " +
                    "where r.student = ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, curUser);

            logger.info("SQL: {}", preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) registrations.add(generateRegistrationFromResultSet(resultSet));
            return registrations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Registration generateRegistrationFromResultSet(ResultSet resultSet) throws SQLException {
        Registration registration = new Registration();
        registration.setRegistrationId(resultSet.getInt("registration_id"));
        registration.setCourseId(resultSet.getInt("course"));
        registration.setStudentId(resultSet.getInt("student"));
        registration.setRegistrationDate(resultSet.getDate("registration_date").toLocalDate());
        return registration;
    }
}
