package ua.oleksii.demo_blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.oleksii.demo_blog.controller.dto.request.TagCreationRequestDTO;
import ua.oleksii.demo_blog.domain.Tag;
import ua.oleksii.demo_blog.service.TagService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public Object getAllPosts(@RequestParam(name = "page", defaultValue = "1") int currentPage) {
        return tagService.getTags(currentPage);
    }

    @PostMapping("/create")
    public Tag createTag(final @RequestBody @Validated TagCreationRequestDTO tag) {
        return tagService.persistNewTag(tag);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTagById(final @PathVariable("tagId") int tagId) {
        tagService.deleteTag(tagId);
    }
}
