package net.avalon.zzz.mapper.generator;

import net.avalon.zzz.controller.vo.QueryParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 3:05
 */
@Mapper
public interface VideoMapper {

    List<Long> findVideoIdsByQueryParams(QueryParams queryParams);
}
