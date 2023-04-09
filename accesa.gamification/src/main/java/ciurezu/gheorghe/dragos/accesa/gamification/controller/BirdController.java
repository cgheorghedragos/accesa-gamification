package ciurezu.gheorghe.dragos.accesa.gamification.controller;

import ciurezu.gheorghe.dragos.accesa.gamification.data.response.GamificationResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.GamificationUserDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BirdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bird")
public class BirdController {
    private final BirdService birdService;

    @GetMapping(value = "/get-user-bird")
    public ResponseEntity<GamificationResponse<?>> getBirdForUser(@RequestParam GamificationUserDto userDto) throws Exception {
        String myBird = birdService.getMyBird(userDto);

        GamificationResponse gamificationResponse = new GamificationResponse(myBird,null);
        return ResponseEntity.ok().body(gamificationResponse);
    }

    @GetMapping(value = "/get-another-bird")
    public ResponseEntity<GamificationResponse<?>> getAnotherBird(@RequestBody GamificationUserDto userDto) throws Exception {
        String birdUrl = birdService.getAnotherBird(userDto);

        GamificationResponse gamificationResponse = new GamificationResponse(birdUrl,null);
        return ResponseEntity.ok().body(gamificationResponse);
    }

    @PutMapping (value = "/bird-loved-update-user")
    public ResponseEntity<GamificationResponse<?>> birdLovedUpdateUser(@RequestBody GamificationUserDto userDto) throws Exception {
        String birdUrl = birdService.lovedBird(userDto);

        GamificationResponse gamificationResponse = new GamificationResponse(birdUrl,null);
        return ResponseEntity.ok().body(gamificationResponse);
    }

}
