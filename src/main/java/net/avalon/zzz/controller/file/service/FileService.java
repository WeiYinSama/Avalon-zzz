package net.avalon.zzz.controller.file.service;

import lombok.extern.slf4j.Slf4j;
import net.avalon.generic.core.exception.AvalonException;
import net.avalon.generic.core.exception.AvalonStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author: Weiyin
 * @Create: 2024/4/11 - 18:23
 */
@Service
@Slf4j
public class FileService {

    @Value("${file.targetDir}")
    private String fileTargetDir;

    public String uploadFile(MultipartFile file) {
        //获取文件扩展名
        String fileName = file.getOriginalFilename();
        log.debug("文件名：{}", fileName);
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 根据不同操作系统，创建不同存储文件的文件夹
        String os = System.getProperty("os.name").toLowerCase();
        String targetDir = fileTargetDir;
        if (!os.contains("win")) {
            String[] split = targetDir.split(":");
            targetDir = split[1];
        }
        Path targetDirPath = Paths.get(targetDir);
        if (!Files.exists(targetDirPath)) {
            try {
                Files.createDirectories(targetDirPath);
            } catch (IOException e) {
                throw new AvalonException(AvalonStatus.FILE_UPLOAD_FAIL,"初始化文件夹失败");
            }
        }
        //定义新文件名
        Path newName = Paths.get(targetDir, UUID.randomUUID().toString().concat(suffix));
        log.debug("新的文件名：{}", newName);
        //文件上传
        try {
            file.transferTo(newName);
            log.debug("文件上传成功");
        } catch (IOException e) {
            throw new AvalonException(AvalonStatus.FILE_UPLOAD_FAIL);
        }

        return newName.getFileName().toString();
    }
}
