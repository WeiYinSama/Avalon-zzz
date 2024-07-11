package net.avalon.zzz.service;

import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.dao.*;
import net.avalon.zzz.dao.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 19:19
 */
@Service
@Slf4j
public class VideoService {

    @Autowired
    private VideoDao dao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private StageDao stageDao;

    @Autowired
    private BossDao bossDao;

    @Autowired
    private AgentDao agentDao;


    /**
     * 添加视频
     * @param video
     */
    @Transactional(rollbackFor = AvalonException.class)
    public void addVideo(Video video){
        // 1. 添加视频信息，返回视频id
        Long vid = dao.addVideo(video);
        // 2. 添加队伍信息
        video.getTeams().forEach(team -> {
            team.setVideoId(vid);
            teamDao.addTeam(team);
        });
    }

    /**
     * 查询符合条件的视频
     *
     * 1. 找到符合查询条件的视频id
     * 2. 根据id查询详情
     * @param params
     * @return
     */
    public List<Video> query(QueryParams params) {
        List<Long> vids = dao.findVidsByQueryParams(params);
        return vids.stream().map(this::getVideoDetailsByVid).toList();
    }

    /**
     * 根据视频id获取视频详情
     * @param vid
     * @return
     */
    public Video getVideoDetailsByVid(Long vid){

        // video
        Video video = dao.findById(vid);
        // stage
        Long stageId = video.getStageId();
        Stage stage = stageDao.findById(stageId);
        video.setStage(stage);
        // team
        List<Team> teams = teamDao.findByVid(vid);
        teams.forEach(team -> {
            // boss
            Boss boss = bossDao.findById(team.getBossId());
            team.setBoss(boss);
            // agent
            List<Agent> agents = agentDao.findByTeamId(team.getId());
            team.setAgents(agents);
        });
        video.setTeams(teams);

        return video;
    }
}
