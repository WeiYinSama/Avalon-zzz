package net.avalon.zzz.controller.vo;

import lombok.Data;
import net.avalon.zzz.dao.bo.Stage;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:31
 */
@Data
public class StageForm {

    private String name;


    public Stage toBo(){
        Stage bo = new Stage();
        bo.setName(this.name);
        return bo;
    }
}
