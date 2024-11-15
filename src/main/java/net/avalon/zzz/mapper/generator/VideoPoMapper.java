package net.avalon.zzz.mapper.generator;

import static net.avalon.zzz.mapper.generator.VideoPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;
import net.avalon.zzz.mapper.generator.po.VideoPo;
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
public interface VideoPoMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, title, levelName, address, status, actionBy, levelId, deleted, createTime, createBy, updateTime, updateBy);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<VideoPo> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VideoPoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="level_name", property="levelName", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="action_by", property="actionBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="level_id", property="levelId", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.BIGINT)
    })
    List<VideoPo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VideoPoResult")
    Optional<VideoPo> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, videoPo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, videoPo, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(VideoPo row) {
        return MyBatis3Utils.insert(this::insert, row, videoPo, c ->
            c.map(title).toProperty("title")
            .map(levelName).toProperty("levelName")
            .map(address).toProperty("address")
            .map(status).toProperty("status")
            .map(actionBy).toProperty("actionBy")
            .map(levelId).toProperty("levelId")
            .map(deleted).toProperty("deleted")
            .map(createTime).toProperty("createTime")
            .map(createBy).toProperty("createBy")
            .map(updateTime).toProperty("updateTime")
            .map(updateBy).toProperty("updateBy")
        );
    }

    default int insertSelective(VideoPo row) {
        return MyBatis3Utils.insert(this::insert, row, videoPo, c ->
            c.map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(levelName).toPropertyWhenPresent("levelName", row::getLevelName)
            .map(address).toPropertyWhenPresent("address", row::getAddress)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(actionBy).toPropertyWhenPresent("actionBy", row::getActionBy)
            .map(levelId).toPropertyWhenPresent("levelId", row::getLevelId)
            .map(deleted).toPropertyWhenPresent("deleted", row::getDeleted)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
        );
    }

    default Optional<VideoPo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, videoPo, completer);
    }

    default List<VideoPo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, videoPo, completer);
    }

    default List<VideoPo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, videoPo, completer);
    }

    default Optional<VideoPo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, videoPo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(VideoPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(title).equalTo(row::getTitle)
                .set(levelName).equalTo(row::getLevelName)
                .set(address).equalTo(row::getAddress)
                .set(status).equalTo(row::getStatus)
                .set(actionBy).equalTo(row::getActionBy)
                .set(levelId).equalTo(row::getLevelId)
                .set(deleted).equalTo(row::getDeleted)
                .set(createTime).equalTo(row::getCreateTime)
                .set(createBy).equalTo(row::getCreateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(updateBy).equalTo(row::getUpdateBy);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(VideoPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(title).equalToWhenPresent(row::getTitle)
                .set(levelName).equalToWhenPresent(row::getLevelName)
                .set(address).equalToWhenPresent(row::getAddress)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(actionBy).equalToWhenPresent(row::getActionBy)
                .set(levelId).equalToWhenPresent(row::getLevelId)
                .set(deleted).equalToWhenPresent(row::getDeleted)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy);
    }

    default int updateByPrimaryKey(VideoPo row) {
        return update(c ->
            c.set(title).equalTo(row::getTitle)
            .set(levelName).equalTo(row::getLevelName)
            .set(address).equalTo(row::getAddress)
            .set(status).equalTo(row::getStatus)
            .set(actionBy).equalTo(row::getActionBy)
            .set(levelId).equalTo(row::getLevelId)
            .set(deleted).equalTo(row::getDeleted)
            .set(createTime).equalTo(row::getCreateTime)
            .set(createBy).equalTo(row::getCreateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }

    default int updateByPrimaryKeySelective(VideoPo row) {
        return update(c ->
            c.set(title).equalToWhenPresent(row::getTitle)
            .set(levelName).equalToWhenPresent(row::getLevelName)
            .set(address).equalToWhenPresent(row::getAddress)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(actionBy).equalToWhenPresent(row::getActionBy)
            .set(levelId).equalToWhenPresent(row::getLevelId)
            .set(deleted).equalToWhenPresent(row::getDeleted)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }
}