package com.spe.cms.controller;

import com.spe.cms.domain.Project;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.ProjectDBRepo;

import java.util.List;

public class ProjectController {

    private ProjectDBRepo projectDBRepo;

    public ProjectController() {
        DBInit dbInit = new DBInit();
        projectDBRepo = new ProjectDBRepo(dbInit.getProps());
    }

    public List<Project> getAllProjects()
    {
        return (List<Project>) projectDBRepo.findAll();
    }

    public Project getProjectById(Integer id)
    {
        return projectDBRepo.findOne(id);
    }

    public Project getLastProject(){return projectDBRepo.findLastOne();}

    public List<Project> getAllByClientId(String id)
    {
        return (List<Project>) projectDBRepo.findAllByClientId(id);
    }

    public void setProject(Project p){
        projectDBRepo.save(p);
    }

    public void deleteProjectById(Integer Id){ projectDBRepo.delete(Id);}
}
