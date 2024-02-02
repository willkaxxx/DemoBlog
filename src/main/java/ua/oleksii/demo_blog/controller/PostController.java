package ua.oleksii.demo_blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.service.PostService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public PageableResponseDTO<Post> getPostsByTags(
            final @RequestParam(name = "page", defaultValue = "1") int currentPage,
            final @RequestParam(name = "pageSize", required = false) Integer pageSize,
            final @RequestParam(value = "tag", required = false, defaultValue = "") List<String> tags
    ) {
        return postService.getPostsOptionallyFilteredByTags(currentPage, pageSize, tags);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(final @RequestBody @Validated PostCreationRequestDTO postToCreate) {
        return postService.persistNewPost(postToCreate);
    }

    @PostMapping("/{postId}/add_tags")
    public Post addTagsToPost(final @PathVariable("postId") int postId,
                              final @RequestBody Set<Tag> tags) {
        return postService.addTagsToPost(postId, tags);
    }

    @PostMapping("/{postId}/delete_tags")
    public Post deleteTagsFromPost(final @PathVariable("postId") int postId,
                                   final @RequestBody Set<Tag> tags) {
        return postService.removeTagsFromPost(postId, tags);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(final @PathVariable("postId") int postId) {
        postService.deletePost(postId);
    }

}
