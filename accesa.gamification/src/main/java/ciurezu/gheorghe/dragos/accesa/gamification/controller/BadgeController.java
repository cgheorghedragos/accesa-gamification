package ciurezu.gheorghe.dragos.accesa.gamification.controller;

import ciurezu.gheorghe.dragos.accesa.gamification.data.response.GamificationResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.BadgeDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/badge")
public class BadgeController {
    private final BadgeService badgeService;

    private final String NORMAL_ERROR = "An Error Has occured!";

    @PostMapping(value = "/save-badge-category")
    public ResponseEntity<GamificationResponse<?>> saveBadgeCategory(@RequestBody BadgeCategoryDto badgeCategoryDto) throws Exception {
        BadgeCategoryDto dto = badgeService.saveBadgeCategory(badgeCategoryDto);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/save-badge")
    public ResponseEntity<GamificationResponse<?>> saveBadge(@RequestBody BadgeDto badgeDto) throws ConflictException {
        BadgeDto dto = badgeService.saveBadge(badgeDto);

        if (dto == null || dto.getId() == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping(value = "/get-all-my-badges")
    public ResponseEntity<GamificationResponse<?>> getAllMyBadges(@RequestBody GamificationUserDto userDto) {
        List<BadgeDto> dto = badgeService.getAllMyBadge(userDto);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

}
