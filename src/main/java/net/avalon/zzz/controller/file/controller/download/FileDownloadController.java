package net.avalon.zzz.controller.file.controller.download;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Weiyin
 * @Create: 2024/4/11 - 18:27
 */
@RestController
public class FileDownloadController {

    @Value("${file.targetDir}")
    private String fileTargetDir;

    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        // 设置服务器上存储图片的文件夹路径
        try {
            // 根据不同操作系统，创建不同存储文件的文件夹
            String os = System.getProperty("os.name").toLowerCase();
            String targetDir = fileTargetDir;
            if (!os.contains("win")) {
                String[] split = targetDir.split(":");
                targetDir = split[1];
            }
            Path file = Paths.get(targetDir).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                // 文件不存在，返回404 Not Found
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
