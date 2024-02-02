package ua.oleksii.demo_blog.service;

import ua.oleksii.demo_blog.controller.dto.request.TagCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Tag;

public interface TagService {
    PageableResponseDTO<Tag> getTags(int currentPage, Integer pageSize);
    Tag persistNewTag(TagCreationRequestDTO tag);
    void deleteTag(int tagId);
}
