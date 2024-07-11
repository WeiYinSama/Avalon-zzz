package net.avalon.zzz.controller.file.controller;


import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.util.R;
import net.avalon.generic.core.util.ResponseUtil;
import net.avalon.zzz.controller.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Weiyin
 * @Create: 2024/4/11 - 17:57
 */
@RestController
@Slf4j
public class FileUploadController {

    @Autowired
    private FileService service;

    @PostMapping("/file/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) {

        String filename = service.uploadFile(file);
        return ResponseUtil.created(filename);
    }
}
