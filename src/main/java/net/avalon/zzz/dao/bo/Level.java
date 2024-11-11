package net.avalon.zzz.dao.bo;

import lombok.Data;
import net.avalon.zzz.mapper.generator.po.LevelPo;
import net.avalon.zzz.mapper.generator.po.VideoPo;

import java.time.LocalDateTime;

/**
 * @Author: Heda
 * @Create: 2024/11/6
 */
@Data
public class Level {

    private Long id;

    /**
     * Database Column Remarks:
     *   关卡类型
     */
    private String name;

    private Byte deleted;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;


    public Level(LevelPo po){
        this.id = po.getId();
        this.name = po.getName();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.createBy = po.getCreateBy();
        this.updateTime = po.getUpdateTime();
        this.updateBy = po.getUpdateBy();
    }

    public LevelPo toPo(){
        LevelPo po = new LevelPo();
        po.setName(this.name);
        return po;
    }
}
