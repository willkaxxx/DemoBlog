package ua.oleksii.demo_blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.oleksii.demo_blog.controller.dto.request.TagCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.response.PageableResponseDTO;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.exceptions.TagInUseException;
import ua.oleksii.demo_blog.mapper.TagMapper;
import ua.oleksii.demo_blog.repository.TagRepository;
import ua.oleksii.demo_blog.service.TagService;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    @Value("${api.page.size}")
    private Integer apiPageSize;

    @Override
    public PageableResponseDTO<Tag> getTags(final int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Tag> tags = tagRepository.findAllTagsPageable(pageable);

        return PageableResponseDTO.<Tag>builder()
                .data(tags.getContent())
                .currentPage(currentPage)
                .totalPages(tags.getTotalPages())
                .build();
    }

    @Override
    public Tag persistNewTag(final TagCreationRequestDTO tag) {
        return tagRepository.save(tagMapper.dtoToModel(tag));
    }

    @Override
    public void deleteTag(final int tagId) {
        try {
            tagRepository.deleteById(tagId);
        } catch (DataIntegrityViolationException e) {
            throw new TagInUseException("Tag with id %s in use".formatted(tagId), e);
        }
    }
}
