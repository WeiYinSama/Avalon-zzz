package net.avalon.zzz.controller;

import jakarta.validation.Valid;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import net.avalon.zzz.controller.vo.AgentForm;
import net.avalon.zzz.controller.vo.BossForm;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.dao.bo.Boss;
import net.avalon.zzz.service.AgentService;
import net.avalon.zzz.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:26
 */
@RestController
public class BossController {

    @Autowired
    private BossService service;


    /**
     * 添加Boss
     *
     * @param form
     * @return
     */
    @PostMapping("/boss/add")
    public R addBoss(@RequestBody @Valid BossForm form) {

        service.addBoss(form.toBo());
        return ResponseUtil.created();
    }

    /**
     * 删除Boss
     *
     * @param id
     * @return
     */
    @DeleteMapping("/boss/delete/{id}")
    public R deleteBossById(@PathVariable Long id) {
        service.deleteBossById(id);
        return ResponseUtil.ok();
    }

    /**
     * 查看所有代理人
     * @return
     */
    @GetMapping("/boss/all")
    public R findAll() {
        List<Boss> ret = service.findAll();

        return ResponseUtil.ok(ret);
    }
}
