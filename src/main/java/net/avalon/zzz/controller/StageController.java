package net.avalon.zzz.controller;

import jakarta.validation.Valid;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import net.avalon.zzz.controller.vo.BossForm;
import net.avalon.zzz.controller.vo.StageForm;
import net.avalon.zzz.dao.bo.Boss;
import net.avalon.zzz.dao.bo.Stage;
import net.avalon.zzz.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:26
 */
@RestController
public class StageController {

    @Autowired
    private StageService service;


    /**
     * 添加关卡类型
     *
     * @param form
     * @return
     */
    @PostMapping("/stage/add")
    public R addStage(@RequestBody @Valid StageForm form) {

        service.addStage(form.toBo());
        return ResponseUtil.created();
    }

    /**
     * 删除关卡类型
     *
     * @param id
     * @return
     */
    @DeleteMapping("/stage/delete/{id}")
    public R deleteStageById(@PathVariable Long id) {
        service.deleteStageById(id);
        return ResponseUtil.ok();
    }

    /**
     * 查看所有关卡类型
     * @return
     */
    @GetMapping("/stage/all")
    public R findAll() {
        List<Stage> ret = service.findAll();

        return ResponseUtil.ok(ret);
    }
}
