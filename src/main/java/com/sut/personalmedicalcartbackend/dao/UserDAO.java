package com.sut.personalmedicalcartbackend.dao;

import com.sut.personalmedicalcartbackend.model.User;
import com.sut.personalmedicalcartbackend.model.UserRegistrationRequest;
import com.sut.personalmedicalcartbackend.util.PhoneNumberHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Component
public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private static Connection connection;

    static {
        String url;
        String username;
        String password;
        String fileName = "src/main/resources/database.properties";

        try (InputStream in = new FileInputStream(fileName)) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);

            logger.debug("Connection successful");
        } catch (SQLException | IOException ex) {
            logger.error("Can't connect to database: {1}", ex);
        }
    }

    public User getUser(String phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE \"PHONE_NUMBER\" = ?")) {
            ps.setString(1, PhoneNumberHelper.extractCountryCode(phoneNumber));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setPatronymic(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setPhoneNumber(rs.getString(7));
                return user;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void addUser(UserRegistrationRequest user) {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO users (\"NAME\", \"SURNAME\", \"PATRONYMIC\", \"PASSWORD\", \"EMAIL\", \"PHONE_NUMBER\") VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPatronymic());
            ps.setString(4, user.getPassword()); //TODO: encrypt password!!!
            ps.setString(5, user.getEmail());
            ps.setString(6, PhoneNumberHelper.extractCountryCode(user.getPhoneNumber()));
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
