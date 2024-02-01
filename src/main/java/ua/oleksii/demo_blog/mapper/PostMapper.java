package ua.oleksii.demo_blog.mapper;

import org.mapstruct.Mapper;
import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.domain.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post dtoToModel(PostCreationRequestDTO dto);

}
