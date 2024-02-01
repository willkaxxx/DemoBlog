package ua.oleksii.demo_blog.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_post", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content")
    private String content;
    @ManyToMany
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "id_post"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
}
