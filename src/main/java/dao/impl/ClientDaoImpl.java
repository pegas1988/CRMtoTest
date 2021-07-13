package dao.impl;

import dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import entity.Client;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao {

    private DataSource dataSource;

    private static final String SELECT_FROM_CLIENT = "select * from client";
    private static final String SELECT_FROM_CLIENT_WHERE_FIRST_NAME_AND_LAST_NAME = "select * from client where first_name = ? and last_name = ?";
    private static final String INSERT_INTO_CLIENT = "insert into client (first_name, last_name, sex) values (?,?,?)";
    private static final String DELETE_FROM_CLIENT = "delete from client where last_name = ?";
    private static final String FIND_CLIENT_BY_ID = "select *from client where client_id = ?";


    public ClientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ClientDaoImpl() {
    }
//private static final String UPDATE_CLIENTS_Di_BY_NAME = "update client set last_name = 'changed' where first_name = ?";

    @Override
    public Client findById(int id) {
        Client client = new Client();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setDiagnoses(resultSet.getString("diagnoses"));
                client.setSex(resultSet.getString("sex"));
                client.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public void create(Client client) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CLIENT, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            //preparedStatement.setDate(3, (Date) client.getDateOfBirth());
            preparedStatement.setString(3, client.getSex());
            //preparedStatement.setString(5, client.getDiagnoses());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setClientID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void updateDiagnose(Client client) {
        try (Connection connection = PostgresUtil.getConnetion();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_BY_NAME);
        ) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void delete(Client client) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_CLIENT)
        ) {
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FROM_CLIENT);
        ) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setDiagnoses(resultSet.getString("diagnoses"));
                client.setSex(resultSet.getString("sex"));
                client.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public List<Client> findByFirstNameAndSecondName(String name, String secondName) {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CLIENT_WHERE_FIRST_NAME_AND_LAST_NAME);
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, secondName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setFirstName(resultSet.getString("first_name"));
                client.setLastName(resultSet.getString("last_name"));
                client.setDiagnoses(resultSet.getString("diagnoses"));
                client.setSex(resultSet.getString("sex"));
                client.setDateOfBirth(resultSet.getTimestamp("date_of_birth"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}

