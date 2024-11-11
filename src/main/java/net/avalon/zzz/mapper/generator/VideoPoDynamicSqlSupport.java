package net.avalon.zzz.mapper.generator;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class VideoPoDynamicSqlSupport {
    public static final VideoPo videoPo = new VideoPo();

    public static final SqlColumn<Long> id = videoPo.id;

    /**
     * Database Column Remarks:
     *   标题
     */
    public static final SqlColumn<String> title = videoPo.title;

    /**
     * Database Column Remarks:
     *   关卡名称
     */
    public static final SqlColumn<String> levelName = videoPo.levelName;

    /**
     * Database Column Remarks:
     *   视频地址
     */
    public static final SqlColumn<String> address = videoPo.address;

    /**
     * Database Column Remarks:
     *   状态：0 - 审核中（默认）、1 - 审核通过
     */
    public static final SqlColumn<Byte> status = videoPo.status;

    /**
     * Database Column Remarks:
     *   Action By
     */
    public static final SqlColumn<String> actionBy = videoPo.actionBy;

    /**
     * Database Column Remarks:
     *   关卡类型 id
     */
    public static final SqlColumn<Long> levelId = videoPo.levelId;

    /**
     * Database Column Remarks:
     *   0-正常（默认）、1-已删除
     */
    public static final SqlColumn<Byte> deleted = videoPo.deleted;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    public static final SqlColumn<LocalDateTime> createTime = videoPo.createTime;

    /**
     * Database Column Remarks:
     *   创建者
     */
    public static final SqlColumn<Long> createBy = videoPo.createBy;

    /**
     * Database Column Remarks:
     *   更新时间
     */
    public static final SqlColumn<LocalDateTime> updateTime = videoPo.updateTime;

    /**
     * Database Column Remarks:
     *   更新者
     */
    public static final SqlColumn<Long> updateBy = videoPo.updateBy;

    public static final class VideoPo extends AliasableSqlTable<VideoPo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> levelName = column("level_name", JDBCType.VARCHAR);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<Byte> status = column("status", JDBCType.TINYINT);

        public final SqlColumn<String> actionBy = column("action_by", JDBCType.VARCHAR);

        public final SqlColumn<Long> levelId = column("level_id", JDBCType.BIGINT);

        public final SqlColumn<Byte> deleted = column("deleted", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createBy = column("create_by", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateBy = column("update_by", JDBCType.BIGINT);

        public VideoPo() {
            super("video", VideoPo::new);
        }
    }
}