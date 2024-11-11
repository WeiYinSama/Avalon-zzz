package net.avalon.zzz.dao;



import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.core.enums.DeletedEnum;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.mapper.generator.AgentPoMapper;
import net.avalon.zzz.mapper.generator.TeamAgentPoMapper;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.mapper.generator.po.TeamAgentPo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static net.avalon.zzz.mapper.generator.AgentPoDynamicSqlSupport.*;
import static net.avalon.zzz.mapper.generator.TeamAgentPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;


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
        po.setDeleted(DeletedEnum.NOMAL.getCode());
        po.setCreateTime(LocalDateTime.now());

        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                log.warn("代理人插入失败：{}", agent);
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            log.info("代理人插入成功：{}", po);
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
        po.setDeleted(DeletedEnum.DELETED.getCode());
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
    public List<Agent> queryAll() throws AvalonException {
        List<Agent> ret = null;
        try {
            SelectStatementProvider provider = select(agentPo.allColumns()).from(agentPo)
                    .where(agentPo.deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                    .orderBy(agentPo.createTime.descending())
                    .build().render(RenderingStrategies.MYBATIS3);

            List<AgentPo> pos = mapper.selectMany(provider);

            ret = pos.stream().map(Agent::new).toList();
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;
    }


    public List<Agent> findByTeamId(Long tid) {

        SelectStatementProvider provider = select(teamAgentPo.agentId).from(teamAgentPo)
                .where(teamAgentPo.teamId, isEqualTo(tid))
                .and(teamAgentPo.deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                .build().render(RenderingStrategies.MYBATIS3);

        return teamAgentPoMapper.selectMany(provider).stream()
                .map(TeamAgentPo::getAgentId)
                .map(this::findById)
                .toList();

    }

    public Agent findById(Long id) {
        SelectStatementProvider provider = select(agentPo.allColumns()).from(agentPo)
                .where(agentPo.deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                .and(agentPo.id, isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);

        Optional<AgentPo> optional = mapper.selectOne(provider);

        if (optional.isEmpty()) {
            throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
        }
        return new Agent(optional.get());
    }
}
