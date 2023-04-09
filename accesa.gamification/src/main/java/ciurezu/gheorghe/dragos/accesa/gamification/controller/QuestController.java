package ciurezu.gheorghe.dragos.accesa.gamification.controller;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Prize;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Quest;
import ciurezu.gheorghe.dragos.accesa.gamification.data.response.GamificationResponse;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.QuestWithCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.QuestRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quest")
public class QuestController {
    private final String NORMAL_ERROR = "An Error Has occured!";
    private final QuestService questService;
    private final BadgeUserService badgeUserService;
    private final BadgeForRewardService badgeForRewardService;
    private final ParticipantService participantService;
    private final PrizeService prizeService;
    private final ImageService imageService;
    private final ModelMapper mapper;

    @PostMapping(value = "/save-quest")
    @Transactional(rollbackFor = {ConflictException.class, BadRequestException.class})
    public ResponseEntity<GamificationResponse<?>> saveQuest(@RequestBody QuestDto questDto) throws Exception {
        Set<PrizeDto> prizeDtos = questDto.getPrize();

        questDto = questService.saveQuest(questDto);

        prizeDtos = prizeService.addPrizes(prizeDtos, questDto);

        badgeForRewardService.transferBadgeFromUserToReward(prizeDtos, questDto);

        GamificationResponse<?> apiResponse = new GamificationResponse<>("Success", null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/save-quest-category")
    public ResponseEntity<GamificationResponse<?>> saveQuestCategory(@RequestBody QuestCategoryDto questCategoryDto) throws Exception {
        QuestCategoryDto dto = questService.saveCategory(questCategoryDto);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/add-participant")
    public ResponseEntity<GamificationResponse<?>> saveParticipant(
            @RequestPart("file") MultipartFile photo, @RequestPart("quest") QuestDto questDto,
            @RequestPart("participant") ParticipantDto participantDto) throws Exception {
        String imgUrl = imageService.uploadPhoto(photo);

        ParticipantDto dto = participantService.addParticipantToQuest(participantDto, questDto, imgUrl);

        if (dto == null) {
            GamificationResponse<?> apiResponse = new GamificationResponse<>(null, NORMAL_ERROR);
            return ResponseEntity.badRequest().body(apiResponse);
        }

        GamificationResponse<?> apiResponse = new GamificationResponse<>(dto, null);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/mark-winners")
    @Transactional(rollbackFor = {BadRequestException.class})
    public ResponseEntity<GamificationResponse<?>> markWinners(@RequestBody QuestDto questDto,@RequestBody List<ParticipantDto> participants) throws BadRequestException {
        List<PrizeWithBadgesDto> prizes = questService.getAllPrizes(questDto);
        badgeForRewardService.transferBadgeFromRewardToUser(prizes,participants,questDto);

        GamificationResponse gamificationResponse = new GamificationResponse(prizes, null);

        return ResponseEntity.ok().body(gamificationResponse);
    }

    @GetMapping(value = "/get-all-active-quests")
    public ResponseEntity<GamificationResponse<?>> getAllActiveQuests() {
        List<QuestWithCategoryDto> quests = questService.getAllActiveQuests();
        GamificationResponse gamificationResponse = new GamificationResponse(quests, null);

        return ResponseEntity.ok().body(gamificationResponse);
    }

    @GetMapping(value = "/get-all-participants-for-quest")
    public ResponseEntity<GamificationResponse<?>> getAllParticipantsForQuest(@RequestBody QuestDto questDto) throws BadRequestException {
        List<ParticipantDto> participantDtos = questService.getAllParticipants(questDto);
        GamificationResponse gamificationResponse = new GamificationResponse(participantDtos, null);

        return ResponseEntity.ok().body(gamificationResponse);
    }

    @GetMapping(value = "/get-all-prizes")
    public ResponseEntity<GamificationResponse<?>> getAllPrizesForQuest(@RequestBody QuestDto questDto) throws BadRequestException {
        List<PrizeWithBadgesDto> prizeDtos = questService.getAllPrizes(questDto);
        GamificationResponse gamificationResponse = new GamificationResponse(prizeDtos, null);

        return ResponseEntity.ok().body(gamificationResponse);
    }
}
