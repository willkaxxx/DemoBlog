package ua.oleksii.demo_blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.exceptions.PostNotFoundException;
import ua.oleksii.demo_blog.exceptions.TagNotFoundException;
import ua.oleksii.demo_blog.mapper.PostMapper;
import ua.oleksii.demo_blog.repository.PostRepository;
import ua.oleksii.demo_blog.service.PostService;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private static final String INSERT_OR_UPDATE_CONSTRAINT_VIOLATION_CODE = "23506";

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Value("${api.page.default_size}")
    private Integer apiPageSize;

    @Override
    public PageableResponseDTO<Post> getPostsOptionallyFilteredByTags(final int currentPage,
                                                                      @Nullable final Integer pageSize,
                                                                      final Collection<String> tagNames) {
        Pageable pageable = PageRequest.of(currentPage - 1, Optional.ofNullable(pageSize).orElse(apiPageSize));
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
    public Post persistNewPost(final PostCreationRequestDTO post) {
        try {
            return postRepository.save(postMapper.dtoToModel(post));
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains(INSERT_OR_UPDATE_CONSTRAINT_VIOLATION_CODE)) {
                throw new TagNotFoundException("Some of tags were not found", e);
            }
            throw e;
        }
    }

    @Override
    public Post addTagsToPost(final int postId, final Collection<Tag> tags) {
        return postRepository.findById(postId).map(post -> {
            post.getTags().addAll(tags);
            try {
                return postRepository.save(post);
            } catch (JpaObjectRetrievalFailureException e) {
                throw new TagNotFoundException("Some of tags were not found", e);
            }
        }).orElseThrow(() -> new PostNotFoundException("Post with id %s was not found".formatted(postId)));
    }

    @Override
    public Post removeTagsFromPost(final int postId, final Collection<Tag> tags) {
        return postRepository.findById(postId).map(post -> {
            post.getTags().removeAll(tags);
            return postRepository.save(post);
        }).orElseThrow(() -> new PostNotFoundException("Post with id %s was not found".formatted(postId)));
    }

    @Override
    public void deletePost(final int postId) {
        postRepository.deleteById(postId);
    }
}
