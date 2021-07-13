package dao;

import entity.Client;

import java.util.List;

public interface ClientDao {

    Client findById(int id);

    void create(Client client);

    void delete(Client client);

    List<Client> findAll();

    List<Client> findByFirstNameAndSecondName(String name, String secondName);

}
