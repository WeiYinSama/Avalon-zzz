package net.avalon.zzz.controller.vo;

import lombok.Data;
import net.avalon.zzz.dao.bo.Agent;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:31
 */
@Data
public class AgentForm {

    private String name;
    private String avatar;

    public Agent toBo(){
        Agent bo = new Agent();
        bo.setName(this.name);
        bo.setAvatar(this.avatar);
        return bo;
    }
}
