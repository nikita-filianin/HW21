package service;

import database.Database;
import entity.Clients;
import entity.Statuses;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusService {
    private static final String SELECT_ALL_STATUS_QUERY = "SELECT * FROM statuses";
    private static final String SAVE_STATUS_QUERY = "INSERT INTO statuses (alias, description) VALUES (?,?)";
    private static final String UPDATE_STATUS_QUERY = "UPDATE statuses SET alias = ?, description = ? WHERE id = ?";
    private static final String DELETE_STATUS_QUERY = "DELETE from statuses WHERE id = ?";
    private static final String SELECT_STATUS_CLIENT_QUERY = "SELECT c.id, c.name, c.email, s.id, s.alias FROM clients c INNER JOIN client_status ct ON c.id = ct.client_id INNER JOIN statuses s ON ct.status_id = s.id WHERE c.age > 8";



    public static List<Statuses> getAll() {
        List<Statuses> statuses = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_STATUS_QUERY);
            while (resultSet.next()) {
                Statuses status = new Statuses();
                status.setId(resultSet.getLong("id"));
                status.setAlias(resultSet.getString("alias"));
                status.setDescription(resultSet.getString("description"));
                statuses.add(status);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    public static void save(Statuses status) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(SAVE_STATUS_QUERY)) {
            preparedStatement.setString(1, status.getAlias());
            preparedStatement.setString(2, status.getDescription());

            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Statuses status) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(UPDATE_STATUS_QUERY)) {
            preparedStatement.setString(1, status.getAlias());
            preparedStatement.setString(2, status.getDescription());
            preparedStatement.setLong(3, status.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Statuses status) {
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement(DELETE_STATUS_QUERY)) {
            preparedStatement.setLong(1, status.getId());
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Map<Statuses, Clients> getClientAndStatus() {
        Map<Statuses, Clients> clientStatusMap = new HashMap<>();
        List<Statuses> statuses = new ArrayList<>();
        List<Clients> clients = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_STATUS_CLIENT_QUERY);
            while (resultSet.next()) {
                Statuses status = new Statuses();
                Clients client = new Clients();
                status.setAlias(resultSet.getString("alias"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                statuses.add(status);
                clients.add(client);
                clientStatusMap.put(status,client);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientStatusMap;
    }
}