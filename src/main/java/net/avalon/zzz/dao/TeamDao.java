package net.avalon.zzz.dao;

import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Team;
import net.avalon.zzz.mapper.generator.TeamAgentPoMapper;
import net.avalon.zzz.mapper.generator.TeamPoMapper;
import net.avalon.zzz.mapper.generator.po.TeamAgentPo;
import net.avalon.zzz.mapper.generator.po.TeamPo;
import net.avalon.zzz.mapper.generator.po.TeamPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());
        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            List<Agent> agents = team.getAgents();
            agents.forEach(agent -> {
                addTeamAgent(po.getId(), agent.getId());
            });
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    public void addTeamAgent(Long tid, Long aid) {
        TeamAgentPo teamAgentPo = new TeamAgentPo();
        teamAgentPo.setTeamId(tid);
        teamAgentPo.setAgentId(aid);
        teamAgentPo.setDeleted((byte) 0);
        teamAgentPo.setCreateTime(LocalDateTime.now());
        teamAgentPoMapper.insertSelective(teamAgentPo);
    }

    public List<Team> findByVid(Long vid) {

        TeamPoExample example = new TeamPoExample();
        example.or().andVideoIdEqualTo(vid);
        List<TeamPo> pos = mapper.selectByExample(example);

        return pos.stream().map(Team::new).toList();
    }
}
