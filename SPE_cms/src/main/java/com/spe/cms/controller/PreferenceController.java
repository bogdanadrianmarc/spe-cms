package com.spe.cms.controller;

import com.spe.cms.domain.Preference;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.PreferenceDBRepo;

import java.util.ArrayList;
import java.util.List;

public class PreferenceController {

    private PreferenceDBRepo preferenceDBRepo;

    public PreferenceController() {
        DBInit dbInit = new DBInit();
        preferenceDBRepo = new PreferenceDBRepo(dbInit.getProps());
    }

    public List<Preference> getAllPreferences()
    {
        return (List<Preference>) preferenceDBRepo.findAll();
    }

    public List<Preference> getPreferencesByStudentId(String id)
    {
        return (List<Preference>) preferenceDBRepo.findAllByStudentId(id);
    }

    public void setPreference(Preference p)
    {
        p.setId(preferenceDBRepo.size()+1);
        preferenceDBRepo.save(p);
    }

    public void deletePreferenceByStudentAndProjectId(String studentId, Integer projectId)
    {
        preferenceDBRepo.deleteByStudentAndProjectId(studentId, projectId);
    }
}
