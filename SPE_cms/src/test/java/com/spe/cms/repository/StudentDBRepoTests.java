package com.spe.cms.repository;

import com.spe.cms.domain.Student;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDBRepoTests {

    private Student c;
    private StudentDBRepo repo;

    @Before
    public void setup() {
        c = new Student("-1", "2", "3");
        repo = new StudentDBRepo(DBInit.getProps());
    }

    @Test
    public void tests() {
        int siz = repo.size();
        repo.save(c);
        assert (repo.size() == siz+1);
        repo.update("-1", new Student("-1","3","4"));
        assert (repo.findOne("-1").getPassword().equals("3"));
        assert (((List<Student>) repo.findAll()).get(siz).getId().equals("-1"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}