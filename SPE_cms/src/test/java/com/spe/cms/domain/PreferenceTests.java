package com.spe.cms.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferenceTests {

    private Preference p;

    @Before
    public void setup() {
        p = new Preference(1,"2",3,4);
    }

    @Test
    public void getters() {
        assert (p.getId().equals(1));
        assert (p.getStudentId().equals("2"));
        assert (p.getProjectId().equals(3));
        assert (p.getPriority().equals(4));
    }

    @Test
    public void setters() {
        p.setId(2);
        p.setStudentId("a");
        p.setProjectId(4);
        p.setPriority(5);
        assert (p.getId().equals(2));
        assert (p.getStudentId().equals("a"));
        assert (p.getProjectId().equals(4));
        assert (p.getPriority().equals(5));
    }

}