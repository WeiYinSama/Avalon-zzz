package net.avalon.zzz.mapper.generator;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 14:58
 */
@Mapper
public interface TeamMapper {

    List<Long> selectTeamIdsByAgentIds(List<Long> aids,int size);

}
