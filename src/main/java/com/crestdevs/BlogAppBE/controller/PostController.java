package com.crestdevs.BlogAppBE.controller;

import com.crestdevs.BlogAppBE.payload.ApiResponse;
import com.crestdevs.BlogAppBE.payload.PostDto;
import com.crestdevs.BlogAppBE.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @RequestParam Integer userId,
                                              @RequestParam Integer categoryId) {

        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {

        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @GetMapping("/getPostsByCategoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable("categoryId") Integer categoryId) {

        List<PostDto> postDto = this.postService.getAllPostsByCategory(categoryId);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/getPostsByUserId/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable("userId") Integer userId) {

        List<PostDto> postDtos = this.postService.getAllPostsByUserId(userId);

        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {

        List<PostDto> postDtoList = this.postService.getAllPost(pageNumber, pageSize);

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
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
}
