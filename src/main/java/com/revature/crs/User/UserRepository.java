package com.revature.crs.User;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.interfaces.Crudable;

import javax.naming.AuthenticationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Crudable<User> {
    @Override
    public boolean update(User updatedUser) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update users " +
                    "set { first_name = ?, last_name = ?, email = ?, password = ?, is_faculty = ? } " +
                    "where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, updatedUser.getFirstName());
            preparedStatement.setString(2, updatedUser.getLastName());
            preparedStatement.setString(3, updatedUser.getEmail());
            preparedStatement.setString(4, updatedUser.getPassword());
            preparedStatement.setBoolean(5, updatedUser.isFaculty());
            preparedStatement.setInt(6, updatedUser.getUserID());
            if (preparedStatement.executeUpdate(sql) == 0) throw new RuntimeException("User not found.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int userID) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete * from users where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new DataNotFoundException("No user found.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<User> users = new ArrayList<>();
            String sql = "select * from users";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) users.add(generateUserFromResultSet(resultSet));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User create(User newUser) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into users values (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setString(4, newUser.getPassword());
            preparedStatement.setBoolean(5, newUser.isFaculty());
            if (preparedStatement.executeUpdate(sql) == 0) throw new RuntimeException("User not added.");
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User findById(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from users where user_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new DataNotFoundException("No user found.");
            return generateUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findByLogin(String email, String password) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from users where email like ? and password like ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new DataNotFoundException("Incorrect Email or Password");
            return generateUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User generateUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserID(resultSet.getInt("user_id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        //user.setPassword(resultSet.getString("password"));
        user.setFaculty(resultSet.getBoolean("is_faculty"));
        return user;
    }
}
