package ua.oleksii.demo_blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.oleksii.demo_blog.domain.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query(value = """
            SELECT DISTINCT t.*
            FROM tag t
            """, countQuery = """
            SELECT COUNT(DISTINCT t.id_tag)
            FROM tag t
            """, nativeQuery = true)
    Page<Tag> findAllTagsPageable(Pageable pageable);

}
