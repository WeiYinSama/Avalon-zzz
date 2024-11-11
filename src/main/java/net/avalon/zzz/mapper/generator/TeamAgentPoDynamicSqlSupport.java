package net.avalon.zzz.mapper.generator;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class TeamAgentPoDynamicSqlSupport {
    public static final TeamAgentPo teamAgentPo = new TeamAgentPo();

    public static final SqlColumn<Long> id = teamAgentPo.id;

    /**
     * Database Column Remarks:
     *   队伍 id
     */
    public static final SqlColumn<Long> teamId = teamAgentPo.teamId;

    /**
     * Database Column Remarks:
     *   代理人 id
     */
    public static final SqlColumn<Long> agentId = teamAgentPo.agentId;

    /**
     * Database Column Remarks:
     *   0-正常（默认）、1-已删除
     */
    public static final SqlColumn<Byte> deleted = teamAgentPo.deleted;

    public static final SqlColumn<LocalDateTime> createTime = teamAgentPo.createTime;

    public static final SqlColumn<Long> createBy = teamAgentPo.createBy;

    public static final SqlColumn<LocalDateTime> updateTime = teamAgentPo.updateTime;

    public static final SqlColumn<Long> updateBy = teamAgentPo.updateBy;

    public static final class TeamAgentPo extends AliasableSqlTable<TeamAgentPo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> teamId = column("team_id", JDBCType.BIGINT);

        public final SqlColumn<Long> agentId = column("agent_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> deleted = column("deleted", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createBy = column("create_by", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateBy = column("update_by", JDBCType.BIGINT);

        public TeamAgentPo() {
            super("team_agent", TeamAgentPo::new);
        }
    }
}