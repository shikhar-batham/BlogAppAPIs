package com.crestdevs.BlogAppBE.controller;

import com.crestdevs.BlogAppBE.payload.ApiResponse;
import com.crestdevs.BlogAppBE.payload.PostDto;
import com.crestdevs.BlogAppBE.payload.PostResponse;
import com.crestdevs.BlogAppBE.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Value("${project.postImages}")
    private String path;

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer userId
                                              ) {

        PostDto createdPost = this.postService.createPost(postDto, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {

        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @GetMapping("/getPostsByUserId/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable("userId") Integer userId) {

        List<PostDto> postDtos = this.postService.getAllPostsByUserId(userId);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPostPagination(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy) {

        PostResponse postResponse = this.postService.getAllPostPagination(pageNumber, pageSize, sortBy);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {

        PostDto postDto = this.postService.getPostById(postId);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse> deletePost(@RequestParam Integer postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post was deleted!", true), HttpStatus.OK);
    }

    @GetMapping("/searchPost/")
    public ResponseEntity<List<PostDto>> searchPost(@RequestParam("keyword") String keyword) {

        List<PostDto> postDtoList = this.postService.searchPost(keyword);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @PostMapping("/postImage/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable("postId") Integer postId, @RequestParam MultipartFile file) throws IOException {

        PostDto postDto = this.postService.uploadPostImage(postId, path, file);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadPostImage/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadPostImage(@PathVariable int postId, HttpServletResponse response) throws IOException {

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try {
            this.postService.downloadPostImage(postId, path, response);
        } catch (IOException ignored) {

        }
    }

}
