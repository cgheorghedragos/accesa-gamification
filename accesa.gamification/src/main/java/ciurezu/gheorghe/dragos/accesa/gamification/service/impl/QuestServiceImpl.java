package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.*;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.PrizeWithBadgesDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.specific_dto.QuestWithCategoryDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.*;
import ciurezu.gheorghe.dragos.accesa.gamification.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {
    private final ModelMapper mapper;
    private final String ALREADY_EXISTS_ERROR_MESSAGE = "Item already exists!";
    private final String TRY_AGAIN_LATTER_MESSAGE = "Try again later";
    private final QuestCategoryRepository questCategoryRepository;
    private final QuestRepository questRepository;
    private final PrizeRepository prizeRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;

    @Override
    public QuestCategoryDto saveCategory(QuestCategoryDto questCategoryDto) throws ConflictException {
        if (questCategoryDto.getCategoryName() == null) {
            return null;
        }

        QuestCategory questCategory = questCategoryRepository.findByCategoryName(questCategoryDto.getCategoryName());

        if (questCategory == null) {
            questCategory = mapper.map(questCategoryDto, QuestCategory.class);
            questCategory = questCategoryRepository.save(questCategory);

            questCategoryDto = mapper.map(questCategory, QuestCategoryDto.class);

            return questCategoryDto;
        }
        throw new ConflictException(ALREADY_EXISTS_ERROR_MESSAGE);

    }

    @Override
    public QuestDto saveQuest(QuestDto questDto) throws Exception {
        if (questDto == null) {
            throw new BadRequestException(TRY_AGAIN_LATTER_MESSAGE);
        }
        QuestCategoryDto questCategoryDto = questDto.getQuestCategory();
        GamificationUserDto userDto = questDto.getUser();

        Quest quest = questRepository.findByTitle(questDto.getTitle());
        GamificationUser user = userRepository.findByUsername(userDto.getUsername());
        QuestCategory questCategory = questCategoryRepository.findByCategoryName(questCategoryDto.getCategoryName());

        if (questCategory == null || user == null) {
            throw new BadRequestException(TRY_AGAIN_LATTER_MESSAGE);
        }

        if (quest != null) {
            throw new ConflictException(ALREADY_EXISTS_ERROR_MESSAGE);
        }

        quest = mapper.map(questDto, Quest.class);
        quest.setQuestCategory(questCategory);
        quest.setUser(user);
        quest.setId(questRepository.save(quest).getId());
        return mapper.map(quest, QuestDto.class);
    }

    @Override
    public List<QuestWithCategoryDto> getAllActiveQuests() {
        List<Quest> allQuests = questRepository.findAllActive();
        List<QuestWithCategoryDto> allQuestsDto = allQuests.stream().map(quest -> mapper.map(quest, QuestWithCategoryDto.class)).toList();
        return allQuestsDto;
    }

    @Override
    public List<ParticipantDto> getAllParticipants(QuestDto questDto) throws BadRequestException {
        if (questDto == null) {
            throw new BadRequestException("Data not found");
        }
        if (questDto.getTitle() == null) {
            throw new BadRequestException("Title not found");
        }
        if (questRepository.findByTitle(questDto.getTitle()) == null) {
            throw new BadRequestException("Quest doesn't exists");
        }

        List<Participant> participants = participantRepository.findByQuestTitle(questDto.getTitle());

        return participants.stream().map(participant ->
                mapper.map(participant, ParticipantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PrizeWithBadgesDto> getAllPrizes(QuestDto questDto) throws BadRequestException {
        if (questDto == null) {
            throw new BadRequestException("Data not found");
        }
        if (questDto.getTitle() == null) {
            throw new BadRequestException("Title not found");
        }
        if (questRepository.findByTitle(questDto.getTitle()) == null) {
            throw new BadRequestException("Quest doesn't exists");
        }

        Quest quest = questRepository.findByTitle(questDto.getTitle());

        return quest.getPrize().stream().map( prize -> {
            System.out.println(prize.getTokens());
        return mapper.map(prize, PrizeWithBadgesDto.class);
        }).collect(Collectors.toList());
    }
}
