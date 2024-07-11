package net.avalon.zzz.service;

import net.avalon.zzz.controller.vo.QueryParams;
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

    @Test
    void query() {

        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setPageSize(10);
        queryParams.setAgents(new ArrayList<>());
        queryParams.setAgents(List.of(1L));
        List<Video> list = videoService.query(queryParams);

    }
}