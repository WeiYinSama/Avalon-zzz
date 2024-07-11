package net.avalon.zzz.dao;

import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Boss;
import net.avalon.zzz.mapper.generator.AgentPoMapper;
import net.avalon.zzz.mapper.generator.BossPoMapper;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.mapper.generator.po.AgentPoExample;
import net.avalon.zzz.mapper.generator.po.BossPo;
import net.avalon.zzz.mapper.generator.po.BossPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:55
 */
@Repository
@Slf4j
public class BossDao {
    @Autowired
    private BossPoMapper mapper;


    /**
     * 创建Boss
     * @param boss
     * @throws AvalonException
     */
    public void createBoss(Boss boss) throws AvalonException {
        BossPo po = boss.toPo();
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());

        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                log.warn("Boss插入失败：{}", boss);
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
            log.debug("Boss插入成功：{}", po);
        } catch (DataAccessException e) {
            log.error("数据库访问失败");
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
    }


    /**
     * 删除Boss
     *
     * @param id
     * @throws AvalonException
     */
    public void deleteBossById(Long id) throws AvalonException {
        BossPo po = new BossPo();
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
     * 查看所有Boss
     *
     * @return
     */
    public List<Boss> findAll() throws AvalonException {
        List<Boss> ret = null;
        try {
            BossPoExample example = new BossPoExample();
            example.createCriteria().andDeletedEqualTo((byte) 0);
            List<BossPo> pos = mapper.selectByExample(example);
            ret = pos.stream().map(Boss::new).toList();
        } catch (AvalonException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;
    }

    public Boss findById(Long id) {
        BossPo po = mapper.selectByPrimaryKey(id);
        if (po == null) {
            throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
        }
        return new Boss(po);
    }
}
