package com.spe.cms.restcontroller;

import com.spe.cms.controller.PreferenceController;
import com.spe.cms.controller.StudentController;
import com.spe.cms.controller.TeacherController;
import com.spe.cms.domain.Preference;
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
public class PreferenceRESTController {

    PreferenceController preferenceController;
    StudentController studentController;
    TeacherController teacherController;

    @PostConstruct
    public void initialize() {
        preferenceController = new PreferenceController();
        studentController = new StudentController();
        teacherController = new TeacherController();
    }


//   ### ALL SELECTIONS ###
    @CrossOrigin
    @RequestMapping(value = "/selections", method = POST)
    public List<Preference> selections(@RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return preferenceController.getAllPreferences();
        else
            return new ArrayList<>();
    }


//   ### ALL SELECTIONS FOR STUDENT ID ###
    @CrossOrigin
    @RequestMapping(value = "/selections_id", method = POST)
    public List<Preference> selections_id(@RequestParam(value = "id") String id, @RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
        if (studentController.isUserAndPassCorrect(user,password) == 0)
            return preferenceController.getPreferencesByStudentId(id);
        else
        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return preferenceController.getPreferencesByStudentId(id);
        else
            return new ArrayList<>();
    }


//   ### SAVE SELECTION ###
    @CrossOrigin
    @RequestMapping(value = "/selection_save", method = POST)
    public String selection_save(@RequestParam(value = "studentId") String studentId, @RequestParam(value = "projectId") Integer projectId, @RequestParam(value = "priority") Integer priority, @RequestParam(value = "login_token") String login_token)
    {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);
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

}
