package github.project.springboottemplate.config;

import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class UpperCaseSpringPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

  @Override
  protected boolean isCaseInsensitive(final JdbcEnvironment jdbcEnvironment) {
    return false;
  }

}
