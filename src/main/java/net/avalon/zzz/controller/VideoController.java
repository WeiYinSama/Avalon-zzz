package net.avalon.zzz.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import net.avalon.zzz.controller.vo.QueryParams;
import net.avalon.zzz.controller.vo.VideoForm;
import net.avalon.zzz.dao.bo.Video;
import net.avalon.zzz.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Weiyin
 * @Create: 2024/5/26 - 13:35
 */
@RestController
@Slf4j
public class VideoController {

    @Autowired
    private VideoService service;


    /**
     * 上传视频信息
     * @param form
     * @return
     */
    @PostMapping("/video")
    public R addVodeo(@RequestBody @Valid VideoForm form){
        service.addVideo(form.toBo());
        return ResponseUtil.created();
    }

    /**
     * 查询相关视频
     * @param params
     * @return
     */
    @PostMapping("/query")
    public R query(@RequestBody @Valid QueryParams params){

        List<Video> ret = service.query(params.initOffset());
        return ResponseUtil.ok(ret);
    }

}
