package net.avalon.zzz.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalon.zzz.mapper.generator.po.AgentPo;
import net.avalon.zzz.mapper.generator.po.BossPo;

import java.time.LocalDateTime;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 18:00
 */
@Data
@NoArgsConstructor
public class Boss {
    private Long id;
    private String name;
    private String image;
    private Byte deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Boss(BossPo po){
        this.id = po.getId();
        this.name = po.getName();
        this.image = po.getImage();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.updateTime = po.getUpdateTime();
    }

    public BossPo toPo(){
        BossPo po = new BossPo();
        po.setName(this.name);
        po.setImage(this.image);
        return po;
    }
}
