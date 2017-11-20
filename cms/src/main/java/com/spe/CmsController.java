package com.spe;

import com.spe.domain.Project;
import com.spe.repository.ProjectDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
public class CmsController {

    @Autowired
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
    @RequestMapping("/projects")
    public List<Project> greeting() {
        List<Project> list = (List<Project>) repo.findAll();
        return list;
//        repo.findOne(id);
    }
}
