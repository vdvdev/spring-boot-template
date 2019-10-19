package github.project.springboottemplate.model.entity;

import github.project.springboottemplate.model.entity.listener.UuidEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "USER")
@EntityListeners({AuditingEntityListener.class, UuidEntityListener.class})
public class User implements UuidIdentifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(columnDefinition = "CHAR(36)",
            name = "UUID",
            nullable = false,
            updatable = false,
            unique = true
    )
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(columnDefinition = "CHAR(45)",
            name = "NAME")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Book> bookList;

    @CreatedDate
    @Column(updatable = false)
    private ZonedDateTime created;

    @LastModifiedDate
    @Column
    private ZonedDateTime modified;

}