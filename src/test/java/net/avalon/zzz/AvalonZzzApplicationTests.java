package net.avalon.zzz;

import lombok.extern.slf4j.Slf4j;
import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.dao.AgentDao;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Team;
import net.avalon.zzz.dao.bo.Video;
import net.avalon.zzz.mapper.generator.AgentPoMapper;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
//@Transactional
class AvalonZzzApplicationTests {

    @Autowired
    private AgentPoMapper agentPoMapper;

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private VideoService videoService;






    @Test
    void contextLoads() {

        AgentPo po = new AgentPo();
        po.setName("18号");
        po.setAvatar("18号.jpg");
        po.setDeleted((byte) 0);
        po.setCreateTime(LocalDateTime.now());

        int i = agentPoMapper.insertSelective(po);
        if(i != 0){
            log.info("插入代理人：{}",po);
        }
    }

    @Test
    void findAll() {
        List<Agent> all = agentDao.queryAll();
        all.forEach(System.out::println);
    }

    @Test
    void addVideo() {
        Video video = new Video();
        video.setTitle("怒肝n天！直播打了个第十层满星！！！绝区零平民深渊挑战");
        video.setLevelName("稳定节点第十防线");
        video.setLevelId(2L);
        video.setAddress("https://www.bilibili.com/video/BV1gZ421E77p");
        video.setActionBy("xiao魔伊大人");
        List<Team> teams = new ArrayList<>();
        Team t1 = new Team();
        t1.setAgents(Stream.of(8L,17L,20L).map(e -> {
            Agent agent = new Agent();
            agent.setId(e);
            return agent;
        }).toList());
        teams.add(t1);
        Team t2 = new Team();
        t2.setAgents(Stream.of(7L,12L,13L).map(e -> {
            Agent agent = new Agent();
            agent.setId(e);
            return agent;
        }).toList());
        teams.add(t2);

        video.setTeams(teams);
        log.info("Video:{}",video);
        videoService.addVideo(video);


    }

    @Test
    void getVideoById() {
        Video video = videoService.getVideoDetailsByVid(5L);

        log.info("Video:{}",video);
    }
}
