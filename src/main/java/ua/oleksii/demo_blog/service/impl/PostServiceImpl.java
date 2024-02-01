package ua.oleksii.demo_blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.mapper.PostMapper;
import ua.oleksii.demo_blog.repository.PostRepository;
import ua.oleksii.demo_blog.service.PostService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Value("${api.page.size}")
    private Integer apiPageSize;
    @Override
    public PageableResponseDTO<Post> getPostsOptionallyFilteredByTags(int currentPage, Collection<String> tagNames) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Post> posts = CollectionUtils.isEmpty(tagNames) ?
                postRepository.findAllPosts(pageable) :
                postRepository.findAllPostsWithTags(tagNames, pageable);

        return PageableResponseDTO.<Post>builder()
                .data(posts.getContent())
                .currentPage(currentPage)
                .totalPages(posts.getTotalPages())
                .build();
    }

    @Override
    public Post persistNewPost(PostCreationRequestDTO post) {
        return postRepository.save(postMapper.dtoToModel(post));
    }

    @Override
    public Post addTagsToPost(int postId, Collection<Tag> tags) {
        return postRepository.findById(postId).map(post -> {
            post.getTags().addAll(tags);
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post with id %s was not found".formatted(postId)));
    }

    @Override
    public Post removeTagsFromPost(int postId, Collection<Tag> tags) {
        return postRepository.findById(postId).map(post -> {
            post.getTags().removeAll(tags);
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post with id %s was not found".formatted(postId)));
    }

    @Override
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }
}