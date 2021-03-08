package com.cstl.erp.repository;

import com.cstl.erp.domain.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageUploadRepository extends JpaRepository<ImageUpload,Long> {
    Optional<ImageUpload> findByName(String name);

    @Query(value = "SELECT MAX(`id`) FROM `image_upload`",nativeQuery = true)
    Long findMaxId();

    @Query(value = "SELECT * FROM `image_upload` WHERE `id`=?1",nativeQuery = true)
    ImageUpload findImageByMaxId(Long id);
}
