package org.cocktail.db.file;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findByFileName(String fileName);
}
