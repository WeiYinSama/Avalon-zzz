package net.avalon.zzz.controller.vo;

import lombok.Data;
import net.avalon.zzz.dao.bo.Boss;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:31
 */
@Data
public class BossForm {

    private String name;
    private String image;

    public Boss toBo(){
        Boss bo = new Boss();
        bo.setName(this.name);
        bo.setImage(this.image);
        return bo;
    }
}
