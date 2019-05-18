package ru.nsu.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.entity.UploadedFile;
import ru.nsu.exception.http.FileNotFoundException;
import ru.nsu.repository.FileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
    private static final String DIR = "src/main/resources/uploads/";

    private final FileRepository fileRepository;

    public UploadedFile save(String name, String contentType, InputStream content) throws IOException {
        UploadedFile uploadedFile = fileRepository.save(new UploadedFile().setName(name).setContentType(contentType));
        File outputFile = new File(DIR + uploadedFile.getId());
        FileUtils.copyInputStreamToFile(content, outputFile);
        return uploadedFile;
    }

    public UploadedFile getById(long fileId) {
        return fileRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException(fileId));
    }

    public void downloadFile(UploadedFile uploadedFile, ServletOutputStream outputStream) throws IOException {
        File inputFile = new File(DIR + uploadedFile.getId());
        if (!inputFile.exists()) {
            throw new FileNotFoundException(uploadedFile.getId());
        }
        FileUtils.copyFile(inputFile, outputStream);
    }
}
