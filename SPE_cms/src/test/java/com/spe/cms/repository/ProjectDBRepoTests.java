package com.spe.cms.repository;

import com.spe.cms.domain.Project;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectDBRepoTests {

    private Project c;
    private ProjectDBRepo repo;

    @Before
    public void setup() {
        c = new Project(-1, "2", "3", "4", 5, "6", "7", "-8","9");
        repo = new ProjectDBRepo(DBInit.getProps());
    }

    @Test
    public void tests() {
        int siz = repo.size();
        repo.save(c);
        assert (repo.size() == siz+1);
        repo.update(-1, new Project(-1,"3","4","5",6,"7","8","-9","10"));
        assert (repo.findOne(-1).getTags().equals("3"));
        assert (((List<Project>) repo.findAll()).get(siz).getId().equals(-1));
        assert (((List<Project>) repo.findAllByClientId("-9")).get(0).getId().equals(-1));
        repo.delete(-1);
        assert (repo.size() == siz);
    }

}