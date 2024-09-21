package com.crestdevs.BlogAppBE.repository;

import com.crestdevs.BlogAppBE.entity.Post;
import com.crestdevs.BlogAppBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String title);

    @Query(value = "SELECT * FROM posts ORDER BY post_id DESC", nativeQuery = true)
    List<Post> findAllFeeds();

}
