package net.avalon.zzz.dao.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalon.zzz.mapper.generator.po.VideoPo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/24 - 18:00
 */
@Data
@NoArgsConstructor
public class Video {
    private Long id;
    private String title;
    //关卡名称
    private String levelName;
    private String address;
    private Byte status;
    private String actionBy;
    private Long levelId;
    private Byte deleted;
    private LocalDateTime createTime;
    private Long createBy;
    private LocalDateTime updateTime;
    private Long updateBy;

    private List<Team> teams;
    private Level level;

    public Video(VideoPo po){
        this.id = po.getId();
        this.title = po.getTitle();
        this.levelName = po.getLevelName();
        this.address = po.getAddress();
        this.status = po.getStatus();
        this.actionBy = po.getActionBy();
        this.levelId = po.getLevelId();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.createBy = po.getCreateBy();
        this.updateTime = po.getUpdateTime();
        this.updateBy = po.getUpdateBy();
    }

    public VideoPo toPo(){
        VideoPo po = new VideoPo();
        po.setTitle(this.title);
        po.setLevelName(this.levelName);
        po.setAddress(this.address);
        po.setActionBy(this.actionBy);
        po.setLevelId(this.levelId);
        return po;
    }
}
