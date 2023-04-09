package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.RoleDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;

import java.util.List;

public interface UserService {
    GamificationUserDto saveUser(GamificationUserDto userDto) throws BadRequestException, ConflictException;

    GamificationUserDto addRoleToUser(GamificationUserDto userDto, RoleDto roleDto) throws BadRequestException, Exception;
    GamificationUserDto getUser(GamificationUserDto userDto);
    List<GamificationUserDto> getTopOfUsers(Integer numbers);
}
