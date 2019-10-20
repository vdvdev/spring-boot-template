package github.project.springboottemplate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "BOOK")
@EntityListeners(AuditingEntityListener.class)
public class Book {

  @Id
  @Column(columnDefinition = "CHAR(36)",
          name = "UUID",
          nullable = false,
          updatable = false,
          unique = true
  )

  @Type(type = "uuid-char")
  private UUID uuid;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @CreatedDate
  @Column(updatable = false)
  private ZonedDateTime created;
}