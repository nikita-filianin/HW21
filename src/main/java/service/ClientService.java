package service;

import database.Database;
import entity.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final String SELECT_ALL_CLIENT_QUERY = "SELECT * FROM clients";
    private static final String SAVE_CLIENT_QUERY = "INSERT INTO clients (name, email, phone, about) VALUES (?,?,?,?)";
    private static final String UPDATE_CLIENT_QUERY = "UPDATE clients SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT_QUERY = "DELETE from clients WHERE id = ?";
    private static final String FIND_BY_PHONE_CLIENT_QUERY = "SELECT * FROM clients WHERE phone = ?";
    private static final String GET_DATA_CLIENT_QUERY = "select c.name, c.email, s.alias FROM clients c\n" +
            "INNER JOIN client_status ct ON c.id = ct.client_id\n" +
            "INNER JOIN statuses s ON ct.status_id = s.id;";



    public List<Clients> getAll() {
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_CLIENT_QUERY);
            while (resultSet.next()) {
                Clients client = new Clients();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getLong("phone"));
                client.setAbout(resultSet.getString("about"));
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static void save(Clients client) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(SAVE_CLIENT_QUERY)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setLong(3, client.getPhone());
            preparedStatement.setString(4, client.getAbout());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Clients client) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(UPDATE_CLIENT_QUERY)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setLong(2, client.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Clients client) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(DELETE_CLIENT_QUERY)) {
            preparedStatement.setLong(1, client.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Clients> findByPhone(long phone) {
        List<Clients> found_clients = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(FIND_BY_PHONE_CLIENT_QUERY);
            preparedStatement.setLong(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Clients client = new Clients();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getLong("phone"));
                client.setAbout(resultSet.getString("about"));
                found_clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found_clients;
    }

    public static List<Clients> getClientStatus() {
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_DATA_CLIENT_QUERY);
            while (resultSet.next()) {
                Clients client = new Clients();
                client.setId(resultSet.getLong("id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


}