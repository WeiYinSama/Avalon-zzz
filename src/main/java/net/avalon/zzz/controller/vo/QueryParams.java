package net.avalon.zzz.controller.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    private Long stageId;
    private List<Long> agents;
    private Long bossId;

    // 0：审核中，1：审核通过，2：审核未通过
    private Integer status;

    private Integer offset;
    private int agentsSize;

    public void initOffset() {
        this.offset = (page - 1) * pageSize;
    }
    public void initAgentsSize(){
        this.agentsSize = agents.size();
    }
}
