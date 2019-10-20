package github.project.springboottemplate.service;

import github.project.springboottemplate.mapper.UserMapper;
import github.project.springboottemplate.model.dto.UserCreationDtoRequest;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import github.project.springboottemplate.model.dto.UserResponseDto;
import github.project.springboottemplate.model.entity.Book;
import github.project.springboottemplate.model.entity.User;
import github.project.springboottemplate.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

  UserRepository userRepository;

  UserMapper userMapper;

  public UserCreationDtoResponse createUser(
      final UserCreationDtoRequest userCreationDtoRequest) {
    final var user = userRepository.save(
        userMapper.mapToUser(userCreationDtoRequest));
    log.info("User was created with uuid={}", user.getUuid());
    return userMapper.mapToUserResponse(user);
  }

  public UserResponseDto findByUuid(final UUID uuid) {
    final var user = findUserByUuid(uuid);

    return userMapper.mapToUserResponseDto(user);
  }

  public void createNewUserBook() {

//    final var user1 = User.builder().bookList(
//            List.of(
//                    Book.builder().build(),
//                    Book.builder().build()))
//            .build();
//
//    final var user2 = User.builder().bookList(
//            List.of(
//                    Book.builder().build(),
//                    Book.builder().build()))
//            .build();
//
//    user1.getBookList().forEach(b -> b.setUser(user1));
//    user2.getBookList().forEach(b -> b.setUser(user2));

//    userRepository.saveAll(List.of(user1, user2));

    userRepository.saveAll(
            List.of(
                    User.builder().build(),
                    User.builder().build(),
                    User.builder().build(),
                    User.builder().build(),
                    User.builder().build(),
                    User.builder().build()));
  }


  private User findUserByUuid(final UUID uuid) {
    return userRepository.findByUuid(uuid).orElseThrow(() ->
        new RuntimeException(String.format("User with uuid: %s is not persisted", uuid)));
  }

}
