package net.avalon.zzz.mapper.generator;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class TeamPoDynamicSqlSupport {
    public static final TeamPo teamPo = new TeamPo();

    public static final SqlColumn<Long> id = teamPo.id;

    /**
     * Database Column Remarks:
     *   队伍名称
     */
    public static final SqlColumn<String> name = teamPo.name;

    /**
     * Database Column Remarks:
     *   视频 id
     */
    public static final SqlColumn<Long> videoId = teamPo.videoId;

    /**
     * Database Column Remarks:
     *   0-正常（默认）、1-已删除
     */
    public static final SqlColumn<Byte> deleted = teamPo.deleted;

    public static final SqlColumn<LocalDateTime> createTime = teamPo.createTime;

    public static final SqlColumn<Long> createBy = teamPo.createBy;

    public static final SqlColumn<LocalDateTime> updateTime = teamPo.updateTime;

    public static final SqlColumn<Long> updateBy = teamPo.updateBy;

    public static final class TeamPo extends AliasableSqlTable<TeamPo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Long> videoId = column("video_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> deleted = column("deleted", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createBy = column("create_by", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateBy = column("update_by", JDBCType.BIGINT);

        public TeamPo() {
            super("team", TeamPo::new);
        }
    }
}