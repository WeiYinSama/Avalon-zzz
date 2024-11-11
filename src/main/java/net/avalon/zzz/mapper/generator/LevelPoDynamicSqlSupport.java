package net.avalon.zzz.mapper.generator;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class LevelPoDynamicSqlSupport {
    public static final LevelPo levelPo = new LevelPo();

    public static final SqlColumn<Long> id = levelPo.id;

    /**
     * Database Column Remarks:
     *   关卡类型
     */
    public static final SqlColumn<String> name = levelPo.name;

    /**
     * Database Column Remarks:
     *   0-正常（默认）、1-已删除
     */
    public static final SqlColumn<Byte> deleted = levelPo.deleted;

    public static final SqlColumn<LocalDateTime> createTime = levelPo.createTime;

    public static final SqlColumn<Long> createBy = levelPo.createBy;

    public static final SqlColumn<LocalDateTime> updateTime = levelPo.updateTime;

    public static final SqlColumn<Long> updateBy = levelPo.updateBy;

    public static final class LevelPo extends AliasableSqlTable<LevelPo> {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Byte> deleted = column("deleted", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createBy = column("create_by", JDBCType.BIGINT);

        public final SqlColumn<LocalDateTime> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateBy = column("update_by", JDBCType.BIGINT);

        public LevelPo() {
            super("level", LevelPo::new);
        }
    }
}