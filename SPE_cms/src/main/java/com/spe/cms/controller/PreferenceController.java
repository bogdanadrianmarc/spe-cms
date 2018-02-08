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
        List<Preference> preferences = (List<Preference>) preferenceDBRepo.findAll();
        List<Preference> idPreferences = new ArrayList<>();
        for (Preference p : preferences)
        {
            if (p.getStudentId().equals(id))
                idPreferences.add(p);
        }
        return idPreferences;
    }

    public void setPreference(Preference p)
    {
        p.setId(preferenceDBRepo.size()+1);
        preferenceDBRepo.save(p);
    }
}
