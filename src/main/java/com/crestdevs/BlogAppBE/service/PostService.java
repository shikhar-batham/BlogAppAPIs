package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.payload.PostDto;
import com.crestdevs.BlogAppBE.payload.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId);

    //update post
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post
    PostResponse getAllPostPagination(Integer pageNumber, Integer pageSize, String sortBy);

    //get all posts by user_id
    List<PostDto> getAllPostsByUserId(Integer userId);

    //search post
    List<PostDto> searchPost(String keyword);

    PostDto uploadPostImage(Integer postId, String path, MultipartFile file) throws IOException;

    void downloadPostImage(int postId, String path, HttpServletResponse response)throws IOException;
}
