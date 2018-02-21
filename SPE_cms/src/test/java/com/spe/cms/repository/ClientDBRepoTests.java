package com.spe.cms.repository;

import com.spe.cms.domain.Client;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientDBRepoTests {

    private Client c;
    private ClientDBRepo repo;

    @Before
    public void setup() {
        c = new Client("-1", "2", "3", "4", "5", "6", "7", "8");
        repo = new ClientDBRepo(DBInit.getProps());
    }

    @Test
    public void tests() {
        int siz = repo.size();
        repo.save(c);
        assert (repo.size() == siz+1);
        repo.update("-1", new Client("-1","3","4","5","6","7","8","9"));
        assert (repo.findOne("-1").getPassword().equals("3"));
        assert (((List<Client>) repo.findAll()).get(siz).getId().equals("-1"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}