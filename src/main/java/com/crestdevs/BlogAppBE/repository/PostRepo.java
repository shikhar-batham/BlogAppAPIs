package com.crestdevs.BlogAppBE.repository;

import com.crestdevs.BlogAppBE.entity.Category;
import com.crestdevs.BlogAppBE.entity.Post;
import com.crestdevs.BlogAppBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);

}
