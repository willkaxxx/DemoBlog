package ua.oleksii.demo_blog.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.oleksii.demo_blog.domain.Tag;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCreationRequestDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private Set<Tag> tags = new HashSet<>();
}
