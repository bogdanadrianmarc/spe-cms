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

    public Preference getPreferenceByStudentAndProjectId(String studentId, Integer projectId)
    {
        return preferenceDBRepo.findOneByStudentAndProjectId(studentId, projectId);
    }

    public List<Preference> getPreferencesByStudentId(String id)
    {
        return (List<Preference>) preferenceDBRepo.findAllByStudentId(id);
    }

    public void setPreference(Preference p)
    {
        preferenceDBRepo.save(p);
    }

    public void deletePreferenceByStudentAndProjectId(String studentId, Integer projectId)
    {
        Preference x = getPreferenceByStudentAndProjectId(studentId, projectId);
        preferenceDBRepo.deleteByStudentAndProjectId(studentId, projectId);
        List<Preference> preferences = getPreferencesByStudentId(studentId);
        for (Preference p : preferences) {
            if (p.getPriority() > x.getPriority()) {
                p.setPriority(p.getPriority()-1);
                preferenceDBRepo.delete(p.getId());
                setPreference(p);
            }
        }
    }
}
