package com.spe.cms.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTests {

    private Client c;

    @Before
    public void setup() {
        c = new Client("1","2","3","4","5","6","7","8");
    }

    @Test
    public void getters() {
        assert (c.getId().equals("1"));
        assert (c.getPassword().equals("2"));
        assert (c.getOrgName().equals("3"));
        assert (c.getOrgAddress().equals("4"));
        assert (c.getOrgPhone().equals("5"));
        assert (c.getPersName().equals("6"));
        assert (c.getPersPhone().equals("7"));
        assert (c.getPersEmail().equals("8"));
    }

    @Test
    public void setters() {
        c.setId("a");
        c.setPassword("b");
        c.setOrgName("c");
        c.setOrgAddress("d");
        c.setOrgPhone("e");
        c.setPersName("f");
        c.setPersPhone("g");
        c.setPersEmail("h");
        assert (c.getId().equals("a"));
        assert (c.getPassword().equals("b"));
        assert (c.getOrgName().equals("c"));
        assert (c.getOrgAddress().equals("d"));
        assert (c.getOrgPhone().equals("e"));
        assert (c.getPersName().equals("f"));
        assert (c.getPersPhone().equals("g"));
        assert (c.getPersEmail().equals("h"));
    }

}