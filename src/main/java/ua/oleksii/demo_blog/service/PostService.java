package ua.oleksii.demo_blog.service;

import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;

import java.util.Collection;

public interface PostService {
    PageableResponseDTO<Post> getPostsOptionallyFilteredByTags(int currentPage, Integer pageSize, Collection<String> tagNames);
    Post persistNewPost(PostCreationRequestDTO post);
    Post addTagsToPost(int postId, Collection<Tag> tags);
    Post removeTagsFromPost(int postId, Collection<Tag> tags);
    void deletePost(int postId);
}
