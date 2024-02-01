package ua.oleksii.demo_blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.repository.PostHRepository;
import ua.oleksii.demo_blog.service.PostService;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostHRepository postRepository;
    @Value("${api.page.size}")
    private Integer apiPageSize;
    @Override
    public List<Post> getPostsOptionallyFilteredByTags(int currentPage, Collection<String> tagNames) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Post> posts = CollectionUtils.isEmpty(tagNames) ?
                postRepository.findAllPosts(pageable) :
                postRepository.findAllPostsWithTags(tagNames, pageable);

        return posts.getContent();
//        var result = new ArrayList<Object>(posts.getContent());
//        result.add(Map.entry("Total pages", posts.getTotalPages()));
//        return result;
    }

    @Override
    public Post persistNewPost(Post post) {
        return postRepository.save(post);
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
