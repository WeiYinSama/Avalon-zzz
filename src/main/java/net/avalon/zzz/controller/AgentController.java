package net.avalon.zzz.controller;

import jakarta.validation.Valid;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import net.avalon.zzz.controller.vo.AgentForm;
import net.avalon.zzz.dao.bo.Agent;
import net.avalon.zzz.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Weiyin
 * @Create: 2024/5/27 - 15:25
 */
@RestController
public class AgentController {

    @Autowired
    private AgentService service;


    /**
     * 添加代理人
     *
     * @param form
     * @return
     */
    @PostMapping("/agent/add")
    public R addAgent(@RequestBody @Valid AgentForm form) {

        service.addAgent(form.toBo());
        return ResponseUtil.created();
    }

    /**
     * 删除代理人
     *
     * @param id
     * @return
     */
    @DeleteMapping("/agent/delete/{id}")
    public R deleteAgentById(@PathVariable Long id) {
        service.deleteAgentById(id);
        return ResponseUtil.ok();
    }

    /**
     * 查看所有代理人
     * @return
     */
    @GetMapping("/agent/all")
    public R findAll() {
        List<Agent> ret = service.findAll();

        return ResponseUtil.ok(ret);
    }
}
