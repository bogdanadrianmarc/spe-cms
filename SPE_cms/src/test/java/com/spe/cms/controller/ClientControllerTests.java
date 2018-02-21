package com.spe.cms.controller;

import com.spe.cms.domain.Client;
import com.spe.cms.repository.ClientDBRepo;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTests {

    private Client c;
    private ClientDBRepo repo;
    private ClientController ctrl;

    @Before
    public void setup() {
        c = new Client("-1", "2", "3", "4", "5", "6", "7", "8");
        repo = new ClientDBRepo(DBInit.getProps());
        ctrl = new ClientController();
    }

    @Test
    public void tests() {
        int siz = ctrl.getAllClients().size();
        ctrl.setClient(c);
        assert (ctrl.getAllClients().size() == siz+1);
        assert (ctrl.isUserAndPassCorrect("-1","2") == 0);
        assert (ctrl.getClientById("-1").getPassword().equals("2"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}