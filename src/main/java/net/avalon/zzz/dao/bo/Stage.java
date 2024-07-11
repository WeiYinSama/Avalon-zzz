package net.avalon.zzz.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalon.zzz.mapper.generator.po.StagePo;

import java.time.LocalDateTime;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 18:00
 */
@Data
@NoArgsConstructor
public class Stage {
    private Long id;
    private String name;
    private Byte deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Stage(StagePo po){
        this.id = po.getId();
        this.name = po.getName();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.updateTime = po.getUpdateTime();
    }

    public StagePo toPo(){
        StagePo po = new StagePo();
        po.setName(this.name);
        return po;
    }
}
