package ru.nsu.controller.file;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.entity.UploadedFile;
import ru.nsu.exception.http.FileProcessingException;
import ru.nsu.service.FileService;

@RestController
@Slf4j
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public UploadedFile uploadFile(@RequestParam(value = "file") MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            return fileService.save(file.getOriginalFilename(), file.getContentType(), is);
        } catch (IOException e) {
            throw new FileProcessingException("Error while uploading file with name=[" + file.getName() + "]");
        }
    }

    @GetMapping("/{fileId}")
    public void downloadFile(@PathVariable long fileId, HttpServletResponse response) {
        UploadedFile uploadedFile = fileService.getById(fileId);
        try {
            response.setContentType(uploadedFile.getContentType());
            response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", uploadedFile.getName()));
            fileService.downloadFile(uploadedFile, response.getOutputStream());
        } catch (IOException e) {
            throw new FileProcessingException("Error while downloading file with id=[" + fileId + "]");
        }
    }
}
