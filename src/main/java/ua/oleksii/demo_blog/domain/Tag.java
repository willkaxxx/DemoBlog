package ua.oleksii.demo_blog.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tag", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
}
