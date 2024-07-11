package net.avalon.zzz.mapper.generator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 15:03
 */
@SpringBootTest
@Slf4j
class TeamMapperTest {

    @Autowired
    private TeamMapper teamMapper;

    @Test
    void selectTeamIdsByAgentIds() {
        List<Long> aids = Stream.of(1L, 2L).toList();
        List<Long> tids = teamMapper.selectTeamIdsByAgentIds(aids,aids.size());
        tids.forEach(System.out::println);
    }
}