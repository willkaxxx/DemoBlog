package ua.oleksii.demo_blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.oleksii.demo_blog.domain.Post;

@Repository
public interface PostHRepository extends CrudRepository<Post, Integer> {

    Page<Post> findAll(Pageable pageable);

}
