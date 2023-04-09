package ciurezu.gheorghe.dragos.accesa.gamification.service;

import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.RoleDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;

public interface RoleService {
    RoleDto addRole(RoleDto roleDto) throws BadRequestException, Exception;
}
