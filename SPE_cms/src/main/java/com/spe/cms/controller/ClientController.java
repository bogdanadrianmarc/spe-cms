package com.spe.cms.controller;

import com.spe.cms.domain.Client;
import com.spe.cms.repository.ClientDBRepo;
import com.spe.cms.repository.utils.DBInit;

import java.util.List;

public class ClientController {

    private ClientDBRepo clientDBRepo;

    public ClientController() {
        DBInit dbInit = new DBInit();
        clientDBRepo = new ClientDBRepo(dbInit.getProps());
    }

    public List<Client> getAllClients()
    {
        return (List<Client>) clientDBRepo.findAll();
    }

    public Client getClientById(String id)
    {
        return clientDBRepo.findOne(id);
    }

    public Integer isUserAndPassCorrect(String user, String password)
    {
        if (clientDBRepo.findOne(user) == null)
            return 1; //if incorrect
        else
            return 0; //if correct
    }

    public void setClient(Client c)
    {
        clientDBRepo.save(c);
    }
}