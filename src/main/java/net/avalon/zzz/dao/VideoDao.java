package net.avalon.zzz.dao;


import com.github.pagehelper.PageHelper;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.core.enums.DeletedEnum;
import net.avalon.zzz.core.enums.VideoStatusEnum;
import net.avalon.zzz.dao.bo.*;
import net.avalon.zzz.mapper.generator.*;
import net.avalon.zzz.mapper.generator.po.*;
import net.avalon.zzz.mapper.generator.po.VideoPo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static net.avalon.zzz.mapper.generator.VideoPoDynamicSqlSupport.*;
import static net.avalon.zzz.mapper.generator.TeamPoDynamicSqlSupport.*;
import static net.avalon.zzz.mapper.generator.TeamAgentPoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 17:57
 */
@Repository
public class VideoDao {

    @Autowired
    private VideoPoMapper mapper;

    @Autowired
    private TeamDao teamDao;


    public Long addVideo(Video video) {
        VideoPo po = video.toPo();
        // 状态为审核中
        po.setStatus(VideoStatusEnum.IN_PROGRESS.getCode());
        po.setDeleted(DeletedEnum.NOMAL.getCode());
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

        List<Long> agents = params.getAgents();

        var where = select(videoPo.id).from(videoPo)
                .where(videoPo.deleted, isEqualTo(DeletedEnum.NOMAL.getCode()))
                .and(videoPo.status, isEqualToWhenPresent(params.getStatus()))
                .and(videoPo.levelId, isEqualToWhenPresent(params.getLevelId()));

        if (!CollectionUtils.isEmpty(agents)) {
            where.and(videoPo.id, isIn(select(teamPo.videoId)
                    .from(teamPo)
                    .leftJoin(teamAgentPo).on(teamPo.id, equalTo(teamAgentPo.teamId))
                    .where(teamAgentPo.agentId, isIn(agents))
                    .groupBy(teamPo.id)
                    .having(countDistinct(teamAgentPo.agentId), isEqualTo((long) agents.size()))));
        }

        SelectStatementProvider provider = where.build().render(RenderingStrategies.MYBATIS3);

        PageHelper.startPage(params.getPage(),params.getPageSize(),false).setOrderBy("id desc");

        return mapper.selectMany(provider).stream().map(VideoPo::getId).toList();
    }

    public Video findById(Long vid) {

        Video ret = null;
        try {
            SelectStatementProvider provider = select(videoPo.allColumns())
                    .from(videoPo)
                    .where(videoPo.id, isEqualTo(vid))
                    .build().render(RenderingStrategies.MYBATIS3);
            Optional<VideoPo> optional = mapper.selectOne(provider);
            if (optional.isEmpty()) {
                throw new AvalonException(AvalonStatus.RESOURCE_ID_NOTEXIST);
            }
            ret = new Video(optional.get());
        } catch (DataAccessException e) {
            throw new AvalonException(AvalonStatus.INTERNAL_SERVER_ERR, "数据库访问错误");
        }
        return ret;

    }


}
