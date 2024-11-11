package net.avalon.zzz.dao;

import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.core.enums.DeletedEnum;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Team;
import net.avalon.zzz.mapper.generator.TeamAgentPoMapper;
import net.avalon.zzz.mapper.generator.TeamPoMapper;
import net.avalon.zzz.mapper.generator.po.TeamAgentPo;
import net.avalon.zzz.mapper.generator.po.TeamPo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static net.avalon.zzz.mapper.generator.TeamAgentPoDynamicSqlSupport.teamAgentPo;
import static net.avalon.zzz.mapper.generator.TeamPoDynamicSqlSupport.teamPo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:58
 */
@Repository
public class TeamDao {

    @Autowired
    private TeamPoMapper mapper;

    @Autowired
    private TeamAgentPoMapper teamAgentPoMapper;

    public void addTeam(Team team) {
        TeamPo po = team.toPo();
        po.setDeleted(DeletedEnum.NOMAL.getCode());
        po.setCreateTime(LocalDateTime.now());
        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            List<Agent> agents = team.getAgents();
            agents.forEach(agent -> {
                this.addTeamAgent(po.getId(), agent.getId());
            });
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    public void addTeamAgent(Long tid, Long aid) {
        TeamAgentPo teamAgentPo = new TeamAgentPo();
        teamAgentPo.setTeamId(tid);
        teamAgentPo.setAgentId(aid);
        teamAgentPo.setDeleted(DeletedEnum.NOMAL.getCode());
        teamAgentPo.setCreateTime(LocalDateTime.now());
        teamAgentPoMapper.insertSelective(teamAgentPo);
    }

    public List<Team> findByVid(Long vid) {

        SelectStatementProvider provider = select(teamPo.allColumns()).from(teamPo)
                .where(teamPo.videoId, isEqualTo(vid))
                .and(teamPo.deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                .build().render(RenderingStrategies.MYBATIS3);

        return mapper.selectMany(provider).stream().map(Team::new).toList();
    }
}
