package net.avalon.zzz.dao;


import com.github.pagehelper.PageHelper;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.dao.bo.*;
import net.avalon.zzz.mapper.generator.*;
import net.avalon.zzz.mapper.generator.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:57
 */
@Repository
public class VideoDao {

    @Autowired
    private VideoPoMapper mapper;

    @Autowired
    private VideoMapper videoMapper;


    public Long addVideo(Video video) {
        VideoPo po = video.toPo();
        // 状态为审核中
        po.setStatus((byte) 0);
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());
        try {
            int i = mapper.insertSelective(po);
            if (i == 0) {
                throw new AvalonException(AvalonStatus.INSERT_FAIL);
            }
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return po.getId();
    }


    public List<Long> findVidsByQueryParams(QueryParams params) {
        params.initOffset();
        if (params.getAgents() != null) {
            params.initAgentsSize();
        }
        return videoMapper.findVideoIdsByQueryParams(params);
    }

    public Video findById(Long vid) {

        Video ret = null;
        try {
            VideoPo po = mapper.selectByPrimaryKey(vid);
            if (po == null) {
                throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
            }
            ret = new Video(po);
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;

    }


}
