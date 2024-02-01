package ua.oleksii.demo_blog.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import ua.oleksii.demo_blog.domain.Tag;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCreationRequestDTO {
    @NonNull
    private String title;
    @NonNull
    private String content;
    private Set<Tag> tags = new HashSet<>();
}
