package com.spe.cms.controller;

import com.spe.cms.domain.Client;
import com.spe.cms.repository.ClientDBRepo;
import com.spe.cms.repository.utils.DBInit;

import java.util.List;

public class ClientController {

    private ClientDBRepo clientDBRepo;

    public ClientController() {
        clientDBRepo = new ClientDBRepo(DBInit.getProps());
    }

    public List<Client> getAllClients()
    {
        return (List<Client>) clientDBRepo.findAll();
    }

    public Client getClientById(String id)
    {
        return clientDBRepo.findOne(id);
    }

    public Integer isUserAndPassCorrect(String username, String password)
    {
        if (clientDBRepo.findOne(username) == null)
            return 1; //if incorrect user
        else
            if (!clientDBRepo.findOne(username).getPassword().equals(password))
                return 2; //if incorrect password
            else
                return 0; //if correct
    }

    public void setClient(Client c)
    {
        clientDBRepo.save(c);
    }
}
