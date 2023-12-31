package com.crestdevs.BlogAppBE.repository;

import com.crestdevs.BlogAppBE.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
