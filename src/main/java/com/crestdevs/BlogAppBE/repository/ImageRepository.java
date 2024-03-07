package com.crestdevs.BlogAppBE.repository;

import com.crestdevs.BlogAppBE.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByFileName(String fileName);

    Boolean existsByFileName(String fileName);
}
