package github.project.springboottemplate.controller;

import github.project.springboottemplate.model.dto.UserCreationDtoRequest;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import github.project.springboottemplate.model.dto.UserResponseDto;
import github.project.springboottemplate.service.UserService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

  UserService userService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(path = "/v1/user")
  public UserCreationDtoResponse createUser(
      @RequestBody @Valid final UserCreationDtoRequest userCreationDtoRequest) {
    return userService.createUser(userCreationDtoRequest);
  }

  @GetMapping("/v1/user/{uuid}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponseDto retrieveUserByUuid(@PathVariable UUID uuid) {

    log.info("Retrieve user with uuid: {}", uuid);

    return userService.findByUuid(uuid);
  }
}
