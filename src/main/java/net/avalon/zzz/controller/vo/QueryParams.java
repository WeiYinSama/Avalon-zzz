package net.avalon.zzz.controller.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 14:07
 */
@Data
public class QueryParams {

    @Max(value = 50,message = "请求不能超过50页")
    private Integer page;

    @Max(value = 100,message = "每页大小不能超过100")
    private Integer pageSize;

    private Long levelId;

    private List<Long> agents;

    // 0：审核中，1：审核通过，2：审核未通过
    private byte status;

    private Integer offset;


    public QueryParams initOffset() {
        this.offset = (page - 1) * pageSize;
        return this;
    }

}
