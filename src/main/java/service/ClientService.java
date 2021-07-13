package service;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientService {


    private ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientService() {
    }

    public List<Client> findAll() {
        List<Client> allClients = clientDao.findAll();
        if (allClients.isEmpty())
            System.out.println("List is empty");
        return allClients;
    }

    public List<Client> findByName(String name, String secondName) {
        List<Client> byName = clientDao.findByFirstNameAndSecondName(name, secondName);
        if (Objects.isNull(byName)) {
            System.out.println(String.format("Clinet with name %s was not found", name));
        }
        return byName;
    }

    public Client findById(int id) {
        return clientDao.findById(id);
    }

    public void create(Client client) {
        clientDao.create(client);
    }

    public void delete(Client client) {
        clientDao.delete(client);
    }
    //public void update(Client client) {clientDao.update(client);}
}
