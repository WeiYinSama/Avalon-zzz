package net.avalon.zzz.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalon.zzz.mapper.generator.po.AgentPo;

import java.time.LocalDateTime;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 18:00
 */
@Data
@NoArgsConstructor
public class Agent {
    private Long id;
    private String name;
    private String avatar;
    private Byte deleted;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;

    public Agent(AgentPo po){
        this.id = po.getId();
        this.name = po.getName();
        this.avatar = po.getAvatar();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.createBy = po.getCreateBy();
        this.updateTime = po.getUpdateTime();
        this.updateBy = po.getUpdateBy();
    }

    public Agent(Long id){
        this.id = id;
    }

    public AgentPo toPo(){
        AgentPo po = new AgentPo();
        po.setName(this.name);
        po.setAvatar(this.avatar);
        return po;
    }
}
