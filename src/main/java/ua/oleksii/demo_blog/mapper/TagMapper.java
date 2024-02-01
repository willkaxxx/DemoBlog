package ua.oleksii.demo_blog.mapper;

import org.mapstruct.Mapper;
import ua.oleksii.demo_blog.controller.dto.request.PostCreationRequestDTO;
import ua.oleksii.demo_blog.controller.dto.request.TagCreationRequestDTO;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag dtoToModel(TagCreationRequestDTO dto);

}
