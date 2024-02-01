package ua.oleksii.demo_blog.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder
@Data
public class PageableResponseDTO<T> {
    private Collection<T> data;
    private int currentPage;
    private int totalPages;
}
