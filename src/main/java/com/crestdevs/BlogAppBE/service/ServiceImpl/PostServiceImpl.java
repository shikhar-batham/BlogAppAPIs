package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.entity.Category;
import com.crestdevs.BlogAppBE.entity.Post;
import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.PostDto;
import com.crestdevs.BlogAppBE.repository.CategoryRepo;
import com.crestdevs.BlogAppBE.repository.PostRepo;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import com.crestdevs.BlogAppBE.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User fetchedUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

        Category fetchedCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(fetchedUser);
        post.setCategory(fetchedCategory);

        Post cratedPost = this.postRepo.save(post);

        return this.modelMapper.map(cratedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post fetchedPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));

        fetchedPost.setTitle(postDto.getTitle());
        fetchedPost.setContent(postDto.getContent());
        fetchedPost.setImageName(postDto.getImageName());


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
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> postPage = this.postRepo.findAll(pageable);

        List<Post> postList = postPage.getContent();

        return postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", categoryId));

        List<Post> postList = this.postRepo.findByCategory(category);

        return postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUserId(Integer userId) {

        User fetchedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

        List<Post> postList = this.postRepo.findByUser(fetchedUser);

        return postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
