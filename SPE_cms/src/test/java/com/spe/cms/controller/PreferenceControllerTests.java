package com.spe.cms.controller;

import com.spe.cms.domain.Preference;
import com.spe.cms.repository.PreferenceDBRepo;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferenceControllerTests {

    private Preference c;
    private PreferenceDBRepo repo;
    private PreferenceController ctrl;

    @Before
    public void setup() {
        c = new Preference(-1, "-2", 3, 4);
        repo = new PreferenceDBRepo(DBInit.getProps());
        ctrl = new PreferenceController();
    }

    @Test
    public void tests() {
        int siz = ctrl.getAllPreferences().size();
        ctrl.setPreference(c);
        assert (ctrl.getAllPreferences().size() == siz+1);
        assert (ctrl.getPreferencesByStudentId("-2").get(0).getProjectId().equals(3));
        ctrl.deletePreferenceByStudentAndProjectId("-2",3);
        assert (repo.size() == siz);
    }

}