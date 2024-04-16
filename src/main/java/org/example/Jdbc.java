package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Jdbc {
    private Connection connection;
    
    public Jdbc() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/database.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public ArrayList<PhoneContact> getAllContacts() throws SQLException {
        String sql = """
                SELECT name, number
                FROM contacts;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<PhoneContact> contacts = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String number = resultSet.getString(2);
            PhoneContact contact = new PhoneContact(name, number);
            contacts.add(contact);
        }
        return contacts;
    }

    public void addContact(PhoneContact newContact) throws SQLException {
        String sql = """
                INSERT INTO contacts(name, number) 
                VALUES (?, ?);
                """;
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newContact.getName());
        preparedStatement.setString(2, newContact.getNumber());
        preparedStatement.executeUpdate();
    }

    public void updateContact(PhoneContact newContact, PhoneContact oldContact) throws SQLException {
        String sql = """
                UPDATE contacts
                SET name = ?, number = ?
                WHERE name = ? AND number = ?;
                """;
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newContact.getName());
        preparedStatement.setString(2, newContact.getNumber());
        preparedStatement.setString(3, oldContact.getName());
        preparedStatement.setString(4, oldContact.getNumber());
        preparedStatement.executeUpdate();
    }

    public void deleteContact(PhoneContact deleteContact) throws SQLException {
        String sql = """
                DELETE FROM contacts
                WHERE name = ? AND number = ?;
                """;
        
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, deleteContact.getName());
        preparedStatement.setString(2, deleteContact.getNumber());
        preparedStatement.executeUpdate();
    }
}
