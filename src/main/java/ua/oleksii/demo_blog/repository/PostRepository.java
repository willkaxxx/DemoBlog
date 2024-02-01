package ua.oleksii.demo_blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.oleksii.demo_blog.domain.Post;

import java.util.Collection;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query(value = """
            SELECT DISTINCT p.*
            FROM post p
            LEFT JOIN post_tag pt ON p.id_post = pt.id_post
            LEFT JOIN tag t ON pt.id_tag = t.id_tag
            WHERE t.name IN :tagNames
            """, countQuery = """
            SELECT COUNT(DISTINCT p.id_post)
            FROM post p
            LEFT JOIN post_tag pt ON p.id_post = pt.id_post
            JOIN tag t ON pt.id_tag = t.id_tag
            WHERE t.name IN :tagNames
            """, nativeQuery = true)
    Page<Post> findAllPostsWithTags(@Param("tagNames") Collection<String> tagNames, Pageable pageable);

    @Query(value = """
            SELECT DISTINCT p.*
            FROM post p
            LEFT JOIN post_tag pt ON p.id_post = pt.id_post
            LEFT JOIN tag t ON pt.id_tag = t.id_tag
            """, countQuery = """
            SELECT COUNT(DISTINCT p.id_post)
            FROM post p
            LEFT JOIN post_tag pt ON p.id_post = pt.id_post
            LEFT JOIN tag t ON pt.id_tag = t.id_tag
            """, nativeQuery = true)
    Page<Post> findAllPosts(Pageable pageable);

}
