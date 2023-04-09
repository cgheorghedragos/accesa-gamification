package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Role;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.RoleDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.RoleRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public RoleDto addRole(RoleDto roleDto) throws Exception {
        if (roleDto == null){
            throw new BadRequestException("No data found");
        }
        if(roleDto.getName() == null){
            throw new BadRequestException("role name not found");
        }
        Role role = roleRepository.findByName(roleDto.getName());

        if(role != null){
            throw new ConflictException("Item already exits");
        }

        role = mapper.map(roleDto,Role.class);
        role = roleRepository.save(role);
        return mapper.map(role,RoleDto.class);
    }
}
