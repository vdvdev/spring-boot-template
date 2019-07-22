package github.project.springboottemplate.model.entity;

import github.project.springboottemplate.model.entity.listener.UuidEntityListener;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

  @CreatedDate
  @Column(updatable = false)
  private ZonedDateTime created;

  @LastModifiedDate
  @Column
  private ZonedDateTime modified;

}