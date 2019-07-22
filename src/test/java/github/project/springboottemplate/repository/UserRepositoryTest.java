package github.project.springboottemplate.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DBRider
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  @DataSet(value = "datasets/Users.yml")
  public void findByUuidExists() {
    var uuid = UUID.fromString("1114e3d0-06ee-4851-b3cc-4121b0a49327");
    var offerOptional = userRepository.findByUuid(uuid);

    assertThat(offerOptional.orElseThrow().getUuid()).isEqualTo(uuid);
  }

  @Test
  @DataSet(value = "datasets/Empty.yml")
  public void findByUuidNotFound() {
    var userOptional = userRepository.findByUuid(UUID.randomUUID());

    assertThat(userOptional.isPresent()).isFalse();
  }

}