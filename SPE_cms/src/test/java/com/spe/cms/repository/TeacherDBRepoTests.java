package com.spe.cms.repository;

import com.spe.cms.domain.Teacher;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherDBRepoTests {

    private Teacher c;
    private TeacherDBRepo repo;

    @Before
    public void setup() {
        c = new Teacher("-1", "2", "3","4");
        repo = new TeacherDBRepo(DBInit.getProps());
    }

    @Test
    public void tests() {
        int siz = repo.size();
        repo.save(c);
        assert (repo.size() == siz+1);
        repo.update("-1", new Teacher("-1","3","4","5"));
        assert (repo.findOne("-1").getPassword().equals("3"));
        assert (((List<Teacher>) repo.findAll()).get(siz).getId().equals("-1"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}