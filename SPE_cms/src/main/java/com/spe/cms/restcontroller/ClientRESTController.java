package com.spe.cms.restcontroller;

import com.spe.cms.controller.*;
import com.spe.cms.restcontroller.utils.Cryption;
import com.spe.cms.domain.Client;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ClientRESTController {
    ClientController clientController;

    @PostConstruct
    public void initialize() {
        clientController = new ClientController();
    }

//    ### CLIENT BY ID ###
    @CrossOrigin
    @RequestMapping(value = "/client_id", method = POST)
    public Client client_id(@RequestParam(value = "id")  String id,@RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);

        if (clientController.isUserAndPassCorrect(user,password) == 0)
            return clientController.getClientById(id);
        else
            return new Client("id","password","orgName","ordAddress","orgPhone","persName","persPhone","persEmail");
    }

}
