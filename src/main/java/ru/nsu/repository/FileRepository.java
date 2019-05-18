package ru.nsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.entity.UploadedFile;

public interface FileRepository extends JpaRepository<UploadedFile, Long> {
}
