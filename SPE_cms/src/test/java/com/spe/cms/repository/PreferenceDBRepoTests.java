package com.spe.cms.repository;

import com.spe.cms.domain.Preference;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferenceDBRepoTests {

    private Preference c;
    private PreferenceDBRepo repo;

    @Before
    public void setup() {
        c = new Preference(-1, "-1", 3, 4);
        repo = new PreferenceDBRepo(DBInit.getProps());
    }

    @Test
    public void tests() {
        int siz = repo.size();
        repo.save(c);
        assert (repo.size() == siz+1);
        assert (repo.findOneByStudentAndProjectId("-1",3).getPriority().equals(4));
        assert (((List<Preference>) repo.findAll()).get(siz).getStudentId().equals("-1"));
        assert (((List<Preference>) repo.findAllByStudentId("-1")).get(0).getStudentId().equals("-1"));
        repo.deleteByStudentAndProjectId("-1",3);
        assert (repo.size() == siz);
    }

}