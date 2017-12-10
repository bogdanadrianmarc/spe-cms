package com.spe.cms;

import com.spe.cms.domain.Project;
import com.spe.cms.repository.ProjectDBRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CmsController {

    ProjectDBRepo repo;

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
        repo = new ProjectDBRepo(serverProps);
    }

    @CrossOrigin
    @RequestMapping(value = "/projects", method = GET)
    public List<Project> projects() {
        return (List<Project>) repo.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/project", method = GET)
    public Project project(@RequestParam(value="id") Integer id) {
        return repo.findOne(id);
    }
}
