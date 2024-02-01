package ua.oleksii.demo_blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.repository.TagHRepository;
import ua.oleksii.demo_blog.service.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagHRepository tagRepository;
    @Value("${api.page.size}")
    private Integer apiPageSize;

    @Override
    public List<Tag> getTags(Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Tag> tags = tagRepository.findAllTagsPageable(pageable);
        return tags.getContent();
//        var result = new ArrayList<Object>(tags.getContent());
//        result.add(Map.entry("Total pages", tags.getTotalPages()));
//        return result;
    }

    @Override
    public Tag persistNewTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(int tagId) {
        tagRepository.deleteById(tagId);
    }
}
