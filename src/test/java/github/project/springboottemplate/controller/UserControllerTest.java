package github.project.springboottemplate.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import github.project.springboottemplate.model.dto.UserResponseDto;
import github.project.springboottemplate.service.UserService;
import java.nio.file.Files;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper jacksonObjectMapper;

  @MockBean
  UserService userService;

  @Test
  public void shouldCreateUser() throws Exception {
    final var userRequestBody = Files.readAllBytes(new ClassPathResource(
        "json/request/CreateUserRequest.json").getFile().toPath());

    final var userResponseBody = Files.readString(new ClassPathResource(
        "json/response/CreateUserResponse.json").getFile().toPath());

    when(userService.createUser(any()))
        .thenReturn(jacksonObjectMapper.readValue(
            userResponseBody, UserCreationDtoResponse.class));

    final var resultBodyResponse = mockMvc.perform(
        post("/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userRequestBody))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();

    JSONAssert.assertEquals(userResponseBody, resultBodyResponse, true);
    verify(userService).createUser(any());
  }

  @Test
  public void shouldReturnUserByUuid()  throws Exception {
    var uuid = UUID.randomUUID();
    var url = String.format("/v1/user/%s", uuid);
    var userResponseDto = UserResponseDto.builder()
        .uuid(uuid)
        .build();

    when(userService.findByUuid(uuid)).thenReturn(userResponseDto);

    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.uuid").value(uuid.toString()));
  }

}
