package net.avalon.zzz.service;

import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.dao.VideoDao;
import net.avalon.zzz.dao.bo.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 17:13
 */
@SpringBootTest
class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoDao videoDao;

    @Test
    void query() {

        QueryParams queryParams = new QueryParams();
        queryParams.setPage(5);
        queryParams.setPageSize(10);

        List<Long> list = videoDao.findVidsByQueryParams(queryParams);

        queryParams.setLevelId(2L);
        list = videoDao.findVidsByQueryParams(queryParams);
        queryParams.setAgents(List.of(1L));
        list = videoDao.findVidsByQueryParams(queryParams);
        list.forEach(System.out::println);

    }
}