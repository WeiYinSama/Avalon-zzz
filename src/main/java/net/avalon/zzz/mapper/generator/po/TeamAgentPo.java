package net.avalon.zzz.mapper.generator.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Database Table Remarks:
 *   队伍-代理人表（team_agent）
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table team_agent
 */
@Data
public class TeamAgentPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   队伍 id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.team_id
     *
     * @mbg.generated
     */
    private Long teamId;

    /**
     * Database Column Remarks:
     *   代理人 id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.agent_id
     *
     * @mbg.generated
     */
    private Long agentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.deleted
     *
     * @mbg.generated
     */
    private Byte deleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column team_agent.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.id
     *
     * @return the value of team_agent.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.id
     *
     * @param id the value for team_agent.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.team_id
     *
     * @return the value of team_agent.team_id
     *
     * @mbg.generated
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.team_id
     *
     * @param teamId the value for team_agent.team_id
     *
     * @mbg.generated
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.agent_id
     *
     * @return the value of team_agent.agent_id
     *
     * @mbg.generated
     */
    public Long getAgentId() {
        return agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.agent_id
     *
     * @param agentId the value for team_agent.agent_id
     *
     * @mbg.generated
     */
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.deleted
     *
     * @return the value of team_agent.deleted
     *
     * @mbg.generated
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.deleted
     *
     * @param deleted the value for team_agent.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.create_time
     *
     * @return the value of team_agent.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.create_time
     *
     * @param createTime the value for team_agent.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column team_agent.update_time
     *
     * @return the value of team_agent.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column team_agent.update_time
     *
     * @param updateTime the value for team_agent.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}