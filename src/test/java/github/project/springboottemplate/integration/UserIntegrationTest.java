package github.project.springboottemplate.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import java.nio.file.Files;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@DBRider
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest
public class UserIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DataSet(value = "datasets/Empty.yml")
  @ExpectedDataSet(value = {"datasets/expected/UserCreated.yml"})
  public void shouldCreateUser() throws Exception {
    var requestBody = Files.readAllBytes(
        new ClassPathResource("json/request/CreateUserRequest.json").getFile().toPath());

    final var resultBodyResponse = mockMvc.perform(
        post("/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();

    final var userDtoResponse =
        objectMapper.readValue(resultBodyResponse, UserCreationDtoResponse.class);
    assertThat(userDtoResponse.getUuid()).isNotNull();
  }

  @Test
  @DataSet(value = "datasets/Users.yml")
  public void shouldReturnUserByUuid() throws Exception {
    final var uuid = "1114e3d0-06ee-4851-b3cc-4121b0a49327";
    final var url = String.format("/v1/user/%s", uuid);
    final var expectedBodyResponse = Files.readString(new ClassPathResource(
        "json/response/ReturnUserResponse.json").getFile().toPath());

    final var resultBodyResponse = mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    JSONAssert.assertEquals(expectedBodyResponse, resultBodyResponse, true);
  }

}
