package ciurezu.gheorghe.dragos.accesa.gamification.controller;

import ciurezu.gheorghe.dragos.accesa.gamification.data.StandardRoles;
import ciurezu.gheorghe.dragos.accesa.gamification.data.response.GamificationResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.RoleDto;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeUserService;
import ciurezu.gheorghe.dragos.accesa.gamification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final BadgeUserService badgeUserService;
    private final UserService userService;
    private final String NORMAL_ERROR = "An Error Has occured!";

    @PostMapping(value = "/save-user")
    public ResponseEntity<GamificationResponse<?>> saveUser(@RequestBody GamificationUserDto userDto) throws Exception {
        GamificationUserDto dto = userService.saveUser(userDto);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setName(StandardRoles.ROLE_USER);
        userService.addRoleToUser(userDto,roleDto);

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/saveBadgeUser")
    public ResponseEntity<GamificationResponse<?>> saveBadgeCategory(@RequestBody BadgeUserDto badgeUserDto) throws Exception {
        BadgeUserDto dto = badgeUserService.addUserBadge(badgeUserDto);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

}
