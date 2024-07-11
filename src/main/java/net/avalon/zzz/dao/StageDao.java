package net.avalon.zzz.dao;

import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Stage;
import net.avalon.zzz.mapper.generator.StagePoMapper;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.mapper.generator.po.AgentPoExample;
import net.avalon.zzz.mapper.generator.po.StagePo;
import net.avalon.zzz.mapper.generator.po.StagePoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 关卡类型
 *
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:56
 */
@Repository
@Slf4j
public class StageDao {

    @Autowired
    private StagePoMapper mapper;

    /**
     * 添加关卡类型
     * @param stage
     * @throws AvalonException
     */
    public void createStage(Stage stage) throws AvalonException {
        StagePo po = stage.toPo();
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());
        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                log.warn("关卡类型插入失败：{}", stage);
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            log.debug("关卡类型插入成功：{}", po);
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
    public void deleteStageById(Long id) throws AvalonException {
        StagePo po = new StagePo();
        po.setId(id);
        po.setDeleted((byte) 1);
        po.setUpdateTime(LocalDateTime.now());
        try {
            int i = mapper.updateByPrimaryKeySelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
            }
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 查看所有关卡类型
     *
     * @return
     */
    public List<Stage> findAll() throws AvalonException {
        List<Stage> ret = null;
        try {
            StagePoExample example = new StagePoExample();
            example.createCriteria().andDeletedEqualTo((byte) 0);
            List<StagePo> pos = mapper.selectByExample(example);
            ret = pos.stream().map(Stage::new).toList();
        } catch (AvalonException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;
    }

    public Stage findById(Long id){
        StagePo po = mapper.selectByPrimaryKey(id);
        if (po == null) {
            throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
        }
        return new Stage(po);
    }
}
