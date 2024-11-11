package net.avalon.zzz.dao;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.core.enums.DeletedEnum;
import net.avalon.zzz.dao.bo.Level;
import net.avalon.zzz.mapper.generator.LevelPoMapper;
import net.avalon.zzz.mapper.generator.po.LevelPo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static net.avalon.zzz.mapper.generator.LevelPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: Heda
 * @Create: 2024/11/6
 */
@Repository
@Slf4j
public class LevelDao {

    @Autowired
    private LevelPoMapper mapper;


    /**
     * 添加关卡类型
     *
     * @param level
     * @throws AvalonException
     */
    public void createLevel(Level level) throws AvalonException {
        LevelPo po = level.toPo();
        po.setDeleted(DeletedEnum.NOMAL.getCode());
        po.setCreateTime(LocalDateTime.now());
        // todo create by
        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                log.warn("关卡类型插入失败：{}", level);
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            log.info("关卡类型插入成功：{}", po);
        } catch (DataAccessException e) {
            log.error("数据库访问失败");
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 删除关卡类型
     *
     * @param id
     * @throws AvalonException
     */
    public void deleteLevelById(Long id) throws AvalonException {
        LevelPo po = new LevelPo();
        po.setId(id);
        po.setDeleted(DeletedEnum.DELETED.getCode());
        po.setUpdateTime(LocalDateTime.now());
        try {
            int i = mapper.updateByPrimaryKeySelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
            }
            log.info("关卡类型删除成功：{}", po);
        } catch (DataAccessException e) {
            log.error("数据库访问失败");
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 查看所有关卡类型
     *
     * @return
     */
    public List<Level> queryAll() throws AvalonException {
        List<Level> ret = null;
        try {

            SelectStatementProvider provider = select(levelPo.allColumns()).from(levelPo)
                    .where(deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                    .orderBy(createTime)
                    .build().render(RenderingStrategies.MYBATIS3);

            List<LevelPo> pos = mapper.selectMany(provider);

            ret = pos.stream().map(Level::new).toList();
        } catch (AvalonException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;
    }

    public Level getById(Long id) {

        SelectStatementProvider provider = select(levelPo.allColumns()).from(levelPo)
                .where(deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                .and(levelPo.id, isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);

        Optional<LevelPo> optional = mapper.selectOne(provider);

        if (optional.isEmpty()) {
            throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
        }
        return new Level(optional.get());
    }
}
