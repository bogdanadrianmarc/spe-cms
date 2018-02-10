package com.spe.cms.restcontroller;

import com.spe.cms.controller.ClientController;
import com.spe.cms.controller.ProjectController;
import com.spe.cms.controller.StudentController;
import com.spe.cms.controller.TeacherController;
import com.spe.cms.domain.Project;
import com.spe.cms.restcontroller.utils.Cryption;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ProjectRESTController {

    ProjectController projectController;
    StudentController studentController;
    TeacherController teacherController;
    ClientController clientController;

    @PostConstruct
    public void initialize() {
        projectController = new ProjectController();
        studentController = new StudentController();
        teacherController = new TeacherController();
        clientController = new ClientController();
    }


//   ### ALL PROJECTS ###
    @CrossOrigin
    @RequestMapping(value = "/projects", method = POST)
    public List<Project> projects(@RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getAllProjects();
        else
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getAllProjects();
        else
            return new ArrayList<>();
    }


//   ### PROJECT BY ID ###
    @CrossOrigin
    @RequestMapping(value = "/project", method = POST)
    public Project project(@RequestParam(value = "id") Integer id, @RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getProjectById(id);
        else
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getProjectById(id);
        else
        if (clientController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getProjectById(id);
        else
            return new Project(-1,"","","",-1,"","","","");
    }

}