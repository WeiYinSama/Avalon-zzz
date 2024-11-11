package net.avalon.zzz.mapper.generator;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class AgentPoDynamicSqlSupport {
    public static final AgentPo agentPo = new AgentPo();

    public static final SqlColumn<Long> id = agentPo.id;

    /**
     * Database Column Remarks:
     *   名字
     */
    public static final SqlColumn<String> name = agentPo.name;

    /**
     * Database Column Remarks:
     *   头像
     */
    public static final SqlColumn<String> avatar = agentPo.avatar;

    /**
     * Database Column Remarks:
     *   0-正常（默认）、1-已删除
     */
    public static final SqlColumn<Byte> deleted = agentPo.deleted;

    public static final SqlColumn<LocalDateTime> createTime = agentPo.createTime;

    public static final SqlColumn<Long> createBy = agentPo.createBy;

    public static final SqlColumn<LocalDateTime> updateTime = agentPo.updateTime;

    public static final SqlColumn<Long> updateBy = agentPo.updateBy;

    public static final class AgentPo extends AliasableSqlTable<AgentPo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> avatar = column("avatar", JDBCType.VARCHAR);

        public final SqlColumn<Byte> deleted = column("deleted", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createBy = column("create_by", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateBy = column("update_by", JDBCType.BIGINT);

        public AgentPo() {
            super("agent", AgentPo::new);
        }
    }
}