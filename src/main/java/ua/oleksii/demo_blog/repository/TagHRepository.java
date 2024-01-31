package ua.oleksii.demo_blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.oleksii.demo_blog.domain.Post;
import ua.oleksii.demo_blog.domain.Tag;

@Repository
public interface TagHRepository extends CrudRepository<Tag, Integer> {

    Page<Tag> findAll(Pageable pageable);

}
