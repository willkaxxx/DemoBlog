package ua.oleksii.demo_blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.repository.PostHRepository;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostHRepository postRepository;

    @Value("${api.page.size}")
    private Integer apiPageSize;

    @GetMapping
    public Object getAllPosts(@RequestParam(name = "page", defaultValue = "1") Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Post> all = postRepository.findAll(pageable);
        var result = new ArrayList<Object>(all.getContent());
        result.add(Map.entry("Total pages", all.getTotalPages()));
        return result;
    }

    @PostMapping("/create")
    public Post createPost(final @RequestBody @Validated Post postToCreate) {
        Post saved = postRepository.save(postToCreate);
        return saved;
    }

    @PostMapping("/{postId}/add_tags")
    public Optional<Post> addTagsToPost(
            final @PathVariable("postId") Integer postId,
            final @RequestBody Set<Tag> tags
    ) {
        Optional<Post> post = postRepository.findById(postId);
        return post.stream()
                .peek(p -> p.getTags().addAll(tags))
                .map(postRepository::save)
                .findFirst();
    }

    @PostMapping("/{postId}/delete_tags")
    public Optional<Post> deleteTagsFromPost(
            final @PathVariable("postId") Integer postId,
            final @RequestBody Set<Tag> tags
    ) {
        Optional<Post> post = postRepository.findById(postId);
        Set<Integer> tagIdsToExclude = tags.stream().map(Tag::getId).collect(Collectors.toSet());
        return post.stream()
                .peek(p -> p.setTags(p.getTags().stream()
                        .filter(tag -> !tagIdsToExclude.contains(tag.getId()))
                        .collect(Collectors.toSet())))
                .map(postRepository::save)
                .findFirst();
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(final @PathVariable("postId") String postId) {
    }

}
