package ua.oleksii.demo_blog.service;

import ua.oleksii.demo_blog.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags(Integer currentPage);
    Tag persistNewTag(Tag tag);
    void deleteTag(int tagId);
}
