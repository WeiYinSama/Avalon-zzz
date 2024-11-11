package net.avalon.zzz.controller.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Team;
import net.avalon.zzz.dao.bo.Video;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 13:38
 */
@Data
public class VideoForm {

    @NotBlank(message = "视频标题不能为空")
    @Length(max = 32,message = "视频标题长度不能超过32个字")
    private String title;
    //关卡名称
    @NotBlank(message = "关卡名称不能为空")
    @Length(max = 32,message = "关卡名称长度不能超过32个字")
    private String levelName;
    @NotBlank(message = "视频不能为空")
    @Length(max = 128,message = "视频地址长度不能超过128个字")
    private String address;
    @NotBlank(message = "必须要署名哦")
    @Length(max = 16,message = "署名不能超过16个字")
    private String actionBy;
    @NotNull(message = "关卡类型不能为空")
    private Long levelId;
    @NotEmpty(message = "至少需要一个队伍")
    private List<TeamForm> teams;



    public Video toBo() {
        Video bo = new Video();
        bo.setTitle(this.title);
        bo.setLevelName(this.levelName);
        bo.setAddress(this.address);
        bo.setActionBy(this.actionBy);
        bo.setLevelId(this.levelId);
        bo.setTeams(teams.stream().map(TeamForm::toBo).toList());
        return bo;
    }


    @Data
    static class TeamForm {
        private List<Long> agents;

        public Team toBo(){
            Team bo = new Team();
            bo.setAgents(this.agents.stream().map(Agent::new).toList());
            return bo;
        }
    }
}
