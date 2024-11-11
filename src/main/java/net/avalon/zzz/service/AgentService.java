package net.avalon.zzz.service;

import net.avalon.zzz.dao.AgentDao;
import net.avalon.zzz.dao.bo.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:27
 */
@Service
public class AgentService {

    @Autowired
    private AgentDao dao;


    public void addAgent(Agent bo) {
        dao.createAgent(bo);
    }

    public void deleteAgentById(Long id) {

        dao.deleteAgentById(id);
    }

    public List<Agent> findAll() {

        return dao.queryAll();
    }
}
