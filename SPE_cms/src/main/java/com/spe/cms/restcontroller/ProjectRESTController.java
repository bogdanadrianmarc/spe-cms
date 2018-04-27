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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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

//   ### CLIENT'S PROJECTS ###
    @CrossOrigin
    @RequestMapping(value = "/projects_by_client", method = POST)
    public List<Project> projects_by_client(@RequestParam(value = "client_id") String client_id, @RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (clientController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getAllByClientId(client_id);
        else
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getAllByClientId(client_id);
        else
            return new ArrayList<>();
    }

//   ### PROJECT BY ID ###
    @CrossOrigin
    @RequestMapping(value = "/project_id", method = POST)
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

//   ### SAVE PROJECT ###
    @CrossOrigin
    @RequestMapping(value = "/project_save", method = POST)
    public String project_save(@RequestParam(value = "tags") String tags,@RequestParam(value = "title") String title,@RequestParam(value = "content") String content,@RequestParam(value = "imgUrl") String imgUrl,@RequestParam(value = "clientId") String clientId,@RequestParam(value = "license") String license, @RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (clientController.isUserAndPassCorrect(user,password) == 0){
            Integer maxId = projectController.getLastProject().getId();
            Project p = new Project(maxId+1,tags,title,content,0,imgUrl,"./project"+Integer.toString(maxId+1),clientId,license);
            projectController.setProject(p);
            return "OK";
        }
        else
            return "NOT LOGGED IN";
    }

//   ### DELETE PROJECT BY ID ###
    @CrossOrigin
    @RequestMapping(value = "/project_delete", method = POST)
    public String project_delete(@RequestParam(value = "projectId") Integer projectId, @RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);

        if (teacherController.isUserAndPassCorrect(user,password) == 0)
        {
            projectController.deleteProjectById(projectId);
            return "OK";
        }
        else
            if (clientController.isUserAndPassCorrect(user,password) == 0)
            {
                projectController.deleteProjectById(projectId);
                return "OK";
            }
        else
            return "NOT LOGGED IN";
    }

}
