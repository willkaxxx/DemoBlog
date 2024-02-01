package ua.oleksii.demo_blog.service;

import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;

import java.util.Collection;
import java.util.List;

public interface PostService {
    List<Post> getPostsOptionallyFilteredByTags(int currentPage, Collection<String> tagNames);
    Post persistNewPost(Post post);
    Post addTagsToPost(int postId, Collection<Tag> tags);
    Post removeTagsFromPost(int postId, Collection<Tag> tags);
    void deletePost(int postId);
}
