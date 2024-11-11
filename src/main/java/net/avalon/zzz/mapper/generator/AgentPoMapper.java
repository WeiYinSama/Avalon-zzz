package net.avalon.zzz.mapper.generator;

import static net.avalon.zzz.mapper.generator.AgentPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;
import net.avalon.zzz.mapper.generator.po.AgentPo;
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
public interface AgentPoMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, avatar, deleted, createTime, createBy, updateTime, updateBy);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AgentPo> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AgentPoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.BIGINT)
    })
    List<AgentPo> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AgentPoResult")
    Optional<AgentPo> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, agentPo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, agentPo, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AgentPo row) {
        return MyBatis3Utils.insert(this::insert, row, agentPo, c ->
            c.map(name).toProperty("name")
            .map(avatar).toProperty("avatar")
            .map(deleted).toProperty("deleted")
            .map(createTime).toProperty("createTime")
            .map(createBy).toProperty("createBy")
            .map(updateTime).toProperty("updateTime")
            .map(updateBy).toProperty("updateBy")
        );
    }

    default int insertSelective(AgentPo row) {
        return MyBatis3Utils.insert(this::insert, row, agentPo, c ->
            c.map(name).toPropertyWhenPresent("name", row::getName)
            .map(avatar).toPropertyWhenPresent("avatar", row::getAvatar)
            .map(deleted).toPropertyWhenPresent("deleted", row::getDeleted)
            .map(createTime).toPropertyWhenPresent("createTime", row::getCreateTime)
            .map(createBy).toPropertyWhenPresent("createBy", row::getCreateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", row::getUpdateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", row::getUpdateBy)
        );
    }

    default Optional<AgentPo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, agentPo, completer);
    }

    default List<AgentPo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, agentPo, completer);
    }

    default List<AgentPo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, agentPo, completer);
    }

    default Optional<AgentPo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, agentPo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AgentPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(row::getName)
                .set(avatar).equalTo(row::getAvatar)
                .set(deleted).equalTo(row::getDeleted)
                .set(createTime).equalTo(row::getCreateTime)
                .set(createBy).equalTo(row::getCreateBy)
                .set(updateTime).equalTo(row::getUpdateTime)
                .set(updateBy).equalTo(row::getUpdateBy);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AgentPo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(row::getName)
                .set(avatar).equalToWhenPresent(row::getAvatar)
                .set(deleted).equalToWhenPresent(row::getDeleted)
                .set(createTime).equalToWhenPresent(row::getCreateTime)
                .set(createBy).equalToWhenPresent(row::getCreateBy)
                .set(updateTime).equalToWhenPresent(row::getUpdateTime)
                .set(updateBy).equalToWhenPresent(row::getUpdateBy);
    }

    default int updateByPrimaryKey(AgentPo row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(avatar).equalTo(row::getAvatar)
            .set(deleted).equalTo(row::getDeleted)
            .set(createTime).equalTo(row::getCreateTime)
            .set(createBy).equalTo(row::getCreateBy)
            .set(updateTime).equalTo(row::getUpdateTime)
            .set(updateBy).equalTo(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }

    default int updateByPrimaryKeySelective(AgentPo row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(avatar).equalToWhenPresent(row::getAvatar)
            .set(deleted).equalToWhenPresent(row::getDeleted)
            .set(createTime).equalToWhenPresent(row::getCreateTime)
            .set(createBy).equalToWhenPresent(row::getCreateBy)
            .set(updateTime).equalToWhenPresent(row::getUpdateTime)
            .set(updateBy).equalToWhenPresent(row::getUpdateBy)
            .where(id, isEqualTo(row::getId))
        );
    }
}