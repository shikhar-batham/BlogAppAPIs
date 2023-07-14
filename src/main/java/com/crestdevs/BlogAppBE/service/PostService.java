package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.entity.Post;
import com.crestdevs.BlogAppBE.payload.PostDto;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update post
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post
    List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);

    //get all post by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    //get all posts by user_id
    List<PostDto> getAllPostsByUserId(Integer userId);

    //search post
    List<PostDto> searchPost(String keyword);
}
