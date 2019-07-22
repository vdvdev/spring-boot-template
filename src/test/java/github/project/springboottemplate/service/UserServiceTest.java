package github.project.springboottemplate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import github.project.springboottemplate.model.dto.UserCreationDtoRequest;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import github.project.springboottemplate.model.entity.User;
import github.project.springboottemplate.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

  @MockBean
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Test
  public void shouldCreateUserTest() {

    final var userDtoRequest = UserCreationDtoRequest.builder().name("name").build();
    final var uuid = UUID.randomUUID();
    when(userRepository.save(any())).thenReturn(User.builder().uuid(uuid).build());

    final var userDtoResponse = userService.createUser(userDtoRequest);

    assertThat(userDtoResponse).isEqualTo(
        UserCreationDtoResponse.builder().uuid(uuid).build());
    verify(userRepository).save(eq(User.builder().name(userDtoRequest.getName()).build()));
  }

  @Test
  public void getUserByCookieUuid() {
    final var uuid = UUID.randomUUID();
    final var user = User.builder().uuid(uuid).build();

    when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(user));

    final var userResponseDto = userService.findByUuid(uuid);

    assertThat(userResponseDto.getUuid()).isEqualTo(uuid);
  }

  @Test
  public void shouldThrowException404getUserByCookieUuid() {
    final var uuid = UUID.randomUUID();
    when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());
    assertThatThrownBy(() -> userService.findByUuid(uuid))
        .isInstanceOf(RuntimeException.class)
        .hasMessage(String.format("User with uuid: %s is not persisted", uuid));
    verify(userRepository).findByUuid(uuid);
  }

}
