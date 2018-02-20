package com.spe.cms.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTests {

    private Project p;

    @Before
    public void setup() {
        p = new Project(1,"a","b","c",5,"e","f","g","h");
    }

    @Test
    public void getters() {
        assert (p.getId().equals(1));
        assert (p.getTags().equals("a"));
        assert (p.getTitle().equals("b"));
        assert (p.getContent().equals("c"));
        assert (p.getApplicantsNr().equals(5));
        assert (p.getImgUrl().equals("e"));
        assert (p.getProjectUrl().equals("f"));
        assert (p.getClientId().equals("g"));
        assert (p.getLicense().equals("h"));
    }

    @Test
    public void setters() {
        p.setId(2);
        p.setTags("b");
        p.setTitle("c");
        p.setContent("d");
        p.setApplicantsNr(6);
        p.setImgUrl("f");
        p.setProjectUrl("g");
        p.setClientId("h");
        p.setLicense("i");
        assert (p.getId().equals(2));
        assert (p.getTags().equals("b"));
        assert (p.getTitle().equals("c"));
        assert (p.getContent().equals("d"));
        assert (p.getApplicantsNr().equals(6));
        assert (p.getImgUrl().equals("f"));
        assert (p.getProjectUrl().equals("g"));
        assert (p.getClientId().equals("h"));
        assert (p.getLicense().equals("i"));
    }

}