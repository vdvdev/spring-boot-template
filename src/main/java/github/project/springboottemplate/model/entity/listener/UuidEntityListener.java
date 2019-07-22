package github.project.springboottemplate.model.entity.listener;

import github.project.springboottemplate.model.entity.UuidIdentifiable;
import java.util.UUID;
import javax.persistence.PrePersist;

public class UuidEntityListener {

  @PrePersist
  protected void onCreate(final UuidIdentifiable target) {
    if (target.getUuid() == null) {
      target.setUuid(UUID.randomUUID());
    }
  }

}
