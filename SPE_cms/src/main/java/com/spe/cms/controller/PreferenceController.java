package com.spe.cms.controller;

import com.spe.cms.domain.Preference;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.PreferenceDBRepo;

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

    private Preference getPreferenceByStudentAndProjectId(String studentId, Integer projectId)
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

    public void updatePreferenceByStudentAndProjectId(String studentId, Integer projectId, Integer oldPriority, Integer newPriority)
    {
        List<Preference> preferences = getPreferencesByStudentId(studentId);
        if (oldPriority > newPriority)
        {
            for (Preference p : preferences) {
                if (p.getPriority() >= newPriority && p.getPriority() < oldPriority) {
                    p.setPriority(p.getPriority() + 1);
                    preferenceDBRepo.update(p.getId(), p);
                } else if (p.getPriority().equals(oldPriority))
                    preferenceDBRepo.update(p.getId(), new Preference(p.getId(), studentId, projectId, newPriority));
            }
        }
        else if (oldPriority < newPriority)
        {
            for (Preference p : preferences) {
                if (p.getPriority() <= newPriority && p.getPriority() > oldPriority) {
                    p.setPriority(p.getPriority() - 1);
                    preferenceDBRepo.update(p.getId(), p);
                } else if (p.getPriority().equals(oldPriority))
                    preferenceDBRepo.update(p.getId(), new Preference(p.getId(), studentId, projectId, newPriority));
            }
        }
    }
}
