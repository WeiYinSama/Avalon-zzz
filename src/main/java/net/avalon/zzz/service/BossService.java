package net.avalon.zzz.service;

import net.avalon.zzz.dao.BossDao;
import net.avalon.zzz.dao.bo.Boss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:26
 */
@Service
public class BossService {

    @Autowired
    private BossDao dao;

    public void addBoss(Boss bo) {

        dao.createBoss(bo);
    }

    public void deleteBossById(Long id) {

        dao.deleteBossById(id);
    }

    public List<Boss> findAll() {

        return dao.findAll();
    }
}
