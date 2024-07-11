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
    private Long bossId;
    private Long videoId;
    private Byte deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Agent> agents;
    private Boss boss;

    public Team(TeamPo po){
        this.id = po.getId();
        this.bossId = po.getBossId();
        this.videoId = po.getVideoId();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.updateTime = po.getUpdateTime();
    }

    public TeamPo toPo(){
        TeamPo po = new TeamPo();
        po.setBossId(this.bossId);
        po.setVideoId(this.videoId);
        return po;
    }
}
