package ua.oleksii.demo_blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.repository.TagHRepository;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tags")
public class TagController {

    private final TagHRepository tagRepository;

    @Value("${api.page.size}")
    private Integer apiPageSize;

    @GetMapping
    public Object getAllPosts(@RequestParam(name = "page", defaultValue = "1") Integer currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, apiPageSize);
        Page<Tag> all = tagRepository.findAll(pageable);
        var result = new ArrayList<Object>(all.getContent());
        result.add(Map.entry("Total pages", all.getTotalPages()));
        return result;
    }

    @PostMapping("/create")
    public Tag createTag(final @RequestBody @Validated Tag tagToCreate) {
        Tag saved = tagRepository.save(tagToCreate);
        return saved;
    }

    @DeleteMapping("/{postId}")
    public void deleteTagById(final @PathVariable("tagId") String tagId) {
    }
}
