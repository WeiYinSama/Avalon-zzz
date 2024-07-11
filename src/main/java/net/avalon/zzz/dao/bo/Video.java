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
    private String stageName;
    private String address;
    private Byte status;
    private String actionBy;
    private Long stageId;
    private Byte deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<Team> teams;
    private Stage stage;

    public Video(VideoPo po){
        this.id = po.getId();
        this.title = po.getTitle();
        this.stageName = po.getStageName();
        this.address = po.getAddress();
        this.status = po.getStatus();
        this.actionBy = po.getActionBy();
        this.stageId = po.getStageId();
        this.deleted = po.getDeleted();
        this.createTime = po.getCreateTime();
        this.updateTime = po.getUpdateTime();
    }

    public VideoPo toPo(){
        VideoPo po = new VideoPo();
        po.setTitle(this.title);
        po.setStageName(this.stageName);
        po.setAddress(this.address);
        po.setActionBy(this.actionBy);
        po.setStageId(this.stageId);
        return po;
    }
}
