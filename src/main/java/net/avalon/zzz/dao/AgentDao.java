package net.avalon.zzz.dao;

import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.mapper.generator.AgentPoMapper;
import net.avalon.zzz.mapper.generator.TeamAgentPoMapper;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.mapper.generator.po.AgentPoExample;
import net.avalon.zzz.mapper.generator.po.TeamAgentPo;
import net.avalon.zzz.mapper.generator.po.TeamAgentPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:54
 */
@Repository
@Slf4j
public class AgentDao {

    @Autowired
    private AgentPoMapper mapper;

    @Autowired
    private TeamAgentPoMapper teamAgentPoMapper;

    /**
     * 创建代理人
     *
     * @param agent
     */
    public void createAgent(Agent agent) throws AvalonException {
        AgentPo po = agent.toPo();
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());

        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                log.warn("代理人插入失败：{}", agent);
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            log.debug("代理人插入成功：{}", po);
        } catch (DataAccessException e) {
            log.error("数据库访问失败");
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 删除代理人
     *
     * @param id
     * @throws AvalonException
     */
    public void deleteAgentById(Long id) throws AvalonException {
        AgentPo po = new AgentPo();
        po.setId(id);
        po.setDeleted((byte) 1);
        po.setUpdateTime(LocalDateTime.now());
        try {
            int i = mapper.updateByPrimaryKeySelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
            }
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 查看所有代理人
     *
     * @return
     */
    public List<Agent> findAll() throws AvalonException {
        List<Agent> ret = null;
        try {
            AgentPoExample example = new AgentPoExample();
            example.createCriteria().andDeletedEqualTo((byte) 0);
            List<AgentPo> pos = mapper.selectByExample(example);
            ret = pos.stream().map(Agent::new).toList();
        } catch (AvalonException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;
    }


    public List<Agent> findByTeamId(Long tid) {

        TeamAgentPoExample teamAgentPoExample = new TeamAgentPoExample();
        teamAgentPoExample.or().andTeamIdEqualTo(tid);
        List<TeamAgentPo> teamAgentPos = teamAgentPoMapper.selectByExample(teamAgentPoExample);
        List<Long> aids = teamAgentPos.stream().map(TeamAgentPo::getAgentId).toList();

        return aids.stream().map(this::findById).toList();
    }

    public Agent findById(Long id){
        AgentPo po = mapper.selectByPrimaryKey(id);

        return new Agent(po);
    }
}
