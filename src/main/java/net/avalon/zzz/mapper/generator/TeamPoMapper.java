package net.avalon.zzz.mapper.generator;

import static net.avalon.zzz.mapper.generator.TeamPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;
import net.avalon.zzz.mapper.generator.po.TeamPo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TeamPoMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, videoId, deleted, createTime, createBy, updateTime, updateBy);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<TeamPo> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TeamPoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="video_id", property="videoId", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.BIGINT)
    })
    List<TeamPo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TeamPoResult")
    Optional<TeamPo> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, teamPo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, teamPo, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(TeamPo row) {
        return MyBatis3Utils.insert(this::insert, row, teamPo, c ->
            c.map(name).toProperty("name")
            .map(videoId).toProperty("videoId")
            .map(deleted).toProperty("deleted")
            .map(createTime).toProperty("createTime")
            .map(createBy).toProperty("createBy")
            .map(updateTime).toProperty("updateTime")
            .map(updateBy).toProperty("updateBy")
        );
    }

    default int insertSelective(TeamPo row) {
        return MyBatis3Utils.insert(this::insert, row, teamPo, c ->
            c.map(name).toPropertyWhenPresent("name", row::getName)
            .map(videoId).toPropertyWhenPresent("videoId", row::getVideoId)
            .map(deleted).toPropertyWhenPresent("deleted", row::getDeleted)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
        );
    }

    default Optional<TeamPo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, teamPo, completer);
    }

    default List<TeamPo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, teamPo, completer);
    }

    default List<TeamPo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, teamPo, completer);
    }

    default Optional<TeamPo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, teamPo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(TeamPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(row::getName)
                .set(videoId).equalTo(row::getVideoId)
                .set(deleted).equalTo(row::getDeleted)
                .set(createTime).equalTo(row::getCreateTime)
                .set(createBy).equalTo(row::getCreateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(updateBy).equalTo(row::getUpdateBy);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(TeamPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(row::getName)
                .set(videoId).equalToWhenPresent(row::getVideoId)
                .set(deleted).equalToWhenPresent(row::getDeleted)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy);
    }

    default int updateByPrimaryKey(TeamPo row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(videoId).equalTo(row::getVideoId)
            .set(deleted).equalTo(row::getDeleted)
            .set(createTime).equalTo(row::getCreateTime)
            .set(createBy).equalTo(row::getCreateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }

    default int updateByPrimaryKeySelective(TeamPo row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(videoId).equalToWhenPresent(row::getVideoId)
            .set(deleted).equalToWhenPresent(row::getDeleted)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }
}