package com.spe.cms.controller;

import com.spe.cms.domain.Project;
import com.spe.cms.repository.ProjectDBRepo;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectControllerTests {

    private Project c;
    private ProjectDBRepo repo;
    private ProjectController ctrl;

    @Before
    public void setup() {
        c = new Project(-1, "2", "3", "4",5,"6","7","8","9");
        repo = new ProjectDBRepo(DBInit.getProps());
        ctrl = new ProjectController();
    }

    @Test
    public void tests() {
        int siz = ctrl.getAllProjects().size();
        ctrl.setProject(c);
        assert (ctrl.getAllProjects().size() == siz+1);
        assert (ctrl.getAllByClientId("8").get(0).getTags().equals("2"));
        ctrl.deleteProjectById(-1);
        assert (repo.size() == siz);
    }

}