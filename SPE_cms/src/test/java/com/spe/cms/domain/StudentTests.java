package com.spe.cms.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTests {

    private Student s;

    @Before
    public void setup() {
        s = new Student("a","b","c");
    }

    @Test
    public void getters() {
        assert (s.getId().equals("a"));
        assert (s.getPassword().equals("b"));
        assert (s.getFullName().equals("c"));
    }

    @Test
    public void setters() {
        s.setId("b");
        s.setPassword("c");
        s.setFullName("d");
        assert (s.getId().equals("b"));
        assert (s.getPassword().equals("c"));
        assert (s.getFullName().equals("d"));
    }

}