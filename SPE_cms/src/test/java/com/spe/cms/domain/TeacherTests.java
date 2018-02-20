package com.spe.cms.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTests {

    private Teacher t;

    @Before
    public void setup() {
        t = new Teacher("a","b","c","d");
    }

    @Test
    public void getters() {
        assert (t.getId().equals("a"));
        assert (t.getPassword().equals("b"));
        assert (t.getFullName().equals("c"));
        assert (t.getEmail().equals("d"));
    }

    @Test
    public void setters() {
        t.setId("b");
        t.setPassword("c");
        t.setFullName("d");
        t.setEmail("e");
        assert (t.getId().equals("b"));
        assert (t.getPassword().equals("c"));
        assert (t.getFullName().equals("d"));
        assert (t.getEmail().equals("e"));
    }

}