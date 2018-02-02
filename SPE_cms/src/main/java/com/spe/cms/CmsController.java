package com.spe.cms;

import com.spe.cms.domain.Preference;
import com.spe.cms.domain.Project;
import com.spe.cms.repository.PreferenceDBRepo;
import com.spe.cms.repository.ProjectDBRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CmsController {

    ProjectDBRepo projectDBRepo;
    PreferenceDBRepo preferenceDBRepo;

    @PostConstruct
    public void initialize() {
        ///repo
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("bd.config"));
            //System.setProperties(serverProps);

            System.out.println("Properties set. ");
            //System.getProperties().list(System.out);
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        projectDBRepo = new ProjectDBRepo(serverProps);
        preferenceDBRepo = new PreferenceDBRepo(serverProps);
    }


//    PROJECTS
    /**
     * @return : all the projects
     */
    @CrossOrigin
    @RequestMapping(value = "/projects", method = GET)
    public List<Project> projects() {
        return (List<Project>) projectDBRepo.findAll();
    }


//    PROJECT
    /**
     * @param id : id of the project
     * @return : the project with that id
     */
    @CrossOrigin
    @RequestMapping(value = "/project", method = GET)
    public Project project(@RequestParam(value = "id") Integer id) {
        return projectDBRepo.findOne(id);
    }


//    SELECTIONS
    /**
     * @return : all the selections by all the students
     */
    @CrossOrigin
    @RequestMapping(value = "/selections", method = GET)
    public List<Preference> selections() {
        return (List<Preference>) preferenceDBRepo.findAll();
    }

    /**
     * @param id : student id
     * @return : all the selections for that id
     */
    @CrossOrigin
    @RequestMapping(value = "/selections_id", method = GET)
    public List<Preference> selections_id(@RequestParam(value = "id") Integer id) {
        List<Preference> preferences = (List<Preference>) preferenceDBRepo.findAll();
        List<Preference> idPreferences = new ArrayList<>();
        for (Preference p : preferences)
        {
            if (p.getStudentId() == id)
                idPreferences.add(p);
        }
        return idPreferences;
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
    public String selection_save(@RequestParam(value = "studentId") Integer studentId, @RequestParam(value = "projectId") Integer projectId, @RequestParam(value = "priority") Integer priority)
    {
        Preference p = new Preference(preferenceDBRepo.size()+1, studentId, projectId, priority);
        preferenceDBRepo.save(p);
        return "OK";
    }

}
