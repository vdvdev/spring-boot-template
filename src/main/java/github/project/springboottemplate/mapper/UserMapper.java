package github.project.springboottemplate.mapper;

import github.project.springboottemplate.model.dto.UserCreationDtoRequest;
import github.project.springboottemplate.model.dto.UserCreationDtoResponse;
import github.project.springboottemplate.model.dto.UserResponseDto;
import github.project.springboottemplate.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "uuid", ignore = true)
  @Mapping(target = "modified", ignore = true)
  @Mapping(target = "created", ignore = true)
  User mapToUser(UserCreationDtoRequest userCreationDtoRequest);

  @Mapping(target = "uuid", source = "uuid")
  UserCreationDtoResponse mapToUserResponse(User user);

  UserResponseDto mapToUserResponseDto(User user);

}
