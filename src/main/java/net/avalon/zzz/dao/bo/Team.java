package net.avalon.zzz.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalon.zzz.mapper.generator.po.TeamPo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 18:01
 */
@Data
@NoArgsConstructor
public class Team {
    private Long id;
    private String name;
    private Long videoId;
    private Byte deleted;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;

    private List<Agent> agents;


    public Team(TeamPo po){
        this.id = po.getId();
        this.name = po.getName();
        this.videoId = po.getVideoId();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.createBy = po.getCreateBy();
        this.updateTime = po.getUpdateTime();
        this.updateBy = po.getUpdateBy();
    }

    public TeamPo toPo(){
        TeamPo po = new TeamPo();
        po.setName(this.name);
        po.setVideoId(this.videoId);
        return po;
    }
}
