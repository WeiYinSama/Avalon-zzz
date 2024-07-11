package net.avalon.zzz.service;

import net.avalon.zzz.dao.StageDao;
import net.avalon.zzz.dao.bo.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:27
 */
@Service
public class StageService {

    @Autowired
    private StageDao dao;


    public void addStage(Stage bo) {
        dao.createStage(bo);
    }

    public void deleteStageById(Long id) {

        dao.deleteStageById(id);
    }

    public List<Stage> findAll() {

        return dao.findAll();
    }
}
