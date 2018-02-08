package com.spe.cms.restcontroller;

import com.spe.cms.controller.*;
import com.spe.cms.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CmsController {

    ProjectController projectController;
    PreferenceController preferenceController;
    StudentController studentController;
    TeacherController teacherController;
    ClientController clientController;

    @PostConstruct
    public void initialize() {
        projectController = new ProjectController();
        preferenceController = new PreferenceController();
        studentController = new StudentController();
        teacherController = new TeacherController();
        clientController = new ClientController();
    }


//    PROJECTS
    /**
     * @return : all the projects
     */
    @CrossOrigin
    @RequestMapping(value = "/projects", method = POST)
    public List<Project> projects(@RequestParam(value = "user") String user, @RequestParam(value = "password") String password) {
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getAllProjects();
        else
            if (teacherController.isUserAndPassCorrect(user,password) == 0)
                return projectController.getAllProjects();
            else
//                OPTIONAL
//                if (clientController.isUserAndPassCorrect(user,password) == 0)
//                    return projectController.getAllProjects();
//                else
                    return new ArrayList<>();
    }


//    PROJECT
    /**
     * @param id : id of the project
     * @return : the project with that id
     */
    @CrossOrigin
    @RequestMapping(value = "/project", method = POST)
    public Project project(@RequestParam(value = "id") Integer id, @RequestParam(value = "user") String user, @RequestParam(value = "password") String password) {
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getProjectById(id);
        else
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return projectController.getProjectById(id);
        else
//                OPTIONAL
//                if (clientController.isUserAndPassCorrect(user,password) == 0)
//                    return projectController.getAllProjects();
//                else
            return new Project(-1,"","","",-1,"","","");
    }


//    SELECTIONS
    /**
     * @return : all the selections by all the students
     */
    @CrossOrigin
    @RequestMapping(value = "/selections", method = POST)
    public List<Preference> selections(@RequestParam(value = "user") String user, @RequestParam(value = "password") String password) {
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return preferenceController.getAllPreferences();
        else
            return new ArrayList<>();
    }

    /**
     * @param id : student id
     * @return : all the selections for that id
     */
    @CrossOrigin
    @RequestMapping(value = "/selections_id", method = POST)
    public List<Preference> selections_id(@RequestParam(value = "id") String id, @RequestParam(value = "user") String user, @RequestParam(value = "password") String password) {
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return preferenceController.getPreferencesByStudentId(id);
        else
            if (teacherController.isUserAndPassCorrect(user,password) == 0)
                return preferenceController.getPreferencesByStudentId(id);
            else
                return new ArrayList<>();
    }


//    SELECTION
    /**
     * @param studentId : student id
     * @param projectId : project id
     * @param priority : priority
     * @return : ok response; saves a certain preference
     */
    @CrossOrigin
    @RequestMapping(value = "/selection_save", method = POST)
    public String selection_save(@RequestParam(value = "studentId") String studentId, @RequestParam(value = "projectId") Integer projectId, @RequestParam(value = "priority") Integer priority, @RequestParam(value = "user") String user, @RequestParam(value = "password") String password)
    {
        if (studentController.isUserAndPassCorrect(user,password) == 0)
        {
            Preference p = new Preference(-1, studentId, projectId, priority);
            preferenceController.setPreference(p);
            System.out.println(studentId);
            return "OK";
        }
        else
            return "NOT LOGGED IN";
    }


//    LOGIN
    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam(value = "type") String type, @RequestParam(value = "user") String user, @RequestParam(value = "password") String password)
    {
        if (type.equals("student"))
            if (studentController.isUserAndPassCorrect(user,password) == 0)
                return "OK";
            else
                return "INCORRECT DETAILS";
        else
            if (type.equals("teacher"))
                if (teacherController.isUserAndPassCorrect(user,password) == 0)
                    return "OK";
                else
                    return "INCORRECT DETAILS";
            else
                if (type.equals("client"))
                    if (clientController.isUserAndPassCorrect(user,password) == 0)
                        return "OK";
                    else
                        return "INCORRECT DETAILS";
                else
                    return "INCORRECT TYPE";
    }

//    REGISTER
    @CrossOrigin
    @RequestMapping(value = "/register", method = POST)
    public String register(@RequestParam(value = "type") String type, @RequestParam(value = "attributes") List<String> attributes)
    {
        if (type.equals("student"))
        {
            Student s = new Student(attributes.get(0), attributes.get(1), attributes.get(2));
            studentController.setStudent(s);
            return "OK";
        }
        else
            if (type.equals("teacher"))
            {
                Teacher t = new Teacher(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3));
                teacherController.setTeacher(t);
                return "OK";
            }
            else
                if (type.equals("client"))
                {
                    Client c = new Client(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5), attributes.get(6), attributes.get(7));
                    clientController.setClient(c);
                    return "OK";
                }
                else
                    return "INCORRECT TYPE";
    }

}
