package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.entity.Image;
import com.crestdevs.BlogAppBE.entity.Post;
import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.PostDto;
import com.crestdevs.BlogAppBE.payload.PostResponse;
import com.crestdevs.BlogAppBE.repository.PostRepo;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import com.crestdevs.BlogAppBE.service.ImageService;
import com.crestdevs.BlogAppBE.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageService imageService;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId) {

        User fetchedUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));


        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImage("default.png");
        post.setAddedDate(new Date());
        post.setUser(fetchedUser);

        Post cratedPost = this.postRepo.save(post);

        return this.modelMapper.map(cratedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post fetchedPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));

        fetchedPost.setTitle(postDto.getTitle());
        fetchedPost.setContent(postDto.getContent());
        fetchedPost.setImage(postDto.getImage());


        Post updatedPost = this.postRepo.save(fetchedPost);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));

        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post fetchedPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));

        return this.modelMapper.map(fetchedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPostPagination(Integer pageNumber, Integer pageSize, String soryBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(soryBy).descending());

        Page<Post> postPage = this.postRepo.findAll(pageable);

        List<Post> postList = postPage.getContent();

        List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setLastPage(postPage.isLast());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setContent(postDtoList);
        postResponse.setTotalElement(postPage.getTotalPages());


        return postResponse;
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Integer userId) {

        User fetchedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

        List<Post> postList = this.postRepo.findByUser(fetchedUser);

        return postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> allPostList = this.postRepo.findAllFeeds();

        return allPostList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {

        List<Post> postList = this.postRepo.findByTitleContaining(keyword);

        return postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto uploadPostImage(Integer postId, String path, MultipartFile file) throws IOException {

        Post fetchedPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Image image = this.imageService.saveImage(file);

        fetchedPost.setImage(image.getFileName());
        this.postRepo.save(fetchedPost);

        return this.modelMapper.map(fetchedPost, PostDto.class);
    }

    @GetMapping(value = "/getImage/{postId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadPostImage(int postId, String path, HttpServletResponse response) throws IOException {

        log.info("image from {},{}", "database", postId + " " + path);

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", postId));
        String image = post.getImage();

        if (!image.isEmpty()) {
            Image img = imageService.getImage(image);
            StreamUtils.copy(img.getData(), response.getOutputStream());
        }
    }
}
