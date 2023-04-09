package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.RoleDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.BadgeUserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.RoleRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.UserService;
import ciurezu.gheorghe.dragos.accesa.gamification.util.EmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final BadgeUserRepository badgeUserRepository;
    private final RoleRepository roleRepository;


    @Override
    public GamificationUserDto saveUser(GamificationUserDto userDto) throws BadRequestException, ConflictException {
        if (userDto == null) {
            throw new BadRequestException("Data not found");
        }

        if (userDto.getEmail() == null || userDto.getPassword().length() < 6) {
            throw new BadRequestException("Bad request");
        }

        if (!EmailValidator.validate(userDto.getEmail())) {
            throw new BadRequestException("Bad email format");
        }

        GamificationUser searchExistingUser = userRepository.findByUsername(userDto.getUsername());
        if (searchExistingUser != null) {
            throw new ConflictException("Username already Exists");
        }

        GamificationUser user = mapper.map(userDto, GamificationUser.class);
        user.setTokens(0);
        user.setNrSolvedQuests(0);
        user.setBird(new Bird());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return mapper.map(user, GamificationUserDto.class);
    }

    @Override
    public GamificationUserDto addRoleToUser(GamificationUserDto userDto, RoleDto roleDto) throws Exception {
        if (userDto == null || roleDto == null) {
            throw new BadRequestException("No data found");
        }
        if (userDto.getUsername() == null || roleDto.getName() == null) {
            throw new BadRequestException("No data found");
        }
        GamificationUser user = userRepository.findByUsername(userDto.getUsername());
        Role role = roleRepository.findByName(roleDto.getName());

        if (user == null) {
            throw new BadRequestException("User not found");
        }
        if (role == null) {
            role = mapper.map(roleDto, Role.class);
            role = roleRepository.save(role);
        }

        for (Role curRole : user.getRoles()) {
            if (role.getName().equals(curRole.getName())) {
                throw new ConflictException("Role already exists");
            }
        }

        user.getRoles().add(role);
        user = userRepository.save(user);
        return mapper.map(user, GamificationUserDto.class);
    }


    @Override
    public GamificationUserDto getUser(GamificationUserDto userDto) {
        return null;
    }

    @Override
    public List<GamificationUserDto> getTopOfUsers(Integer numbers) {
        List<GamificationUser> users = userRepository.getTopByQuestSolved(numbers);
        return users.stream().map(user -> mapper.map(user, GamificationUserDto.class)).toList();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GamificationUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
