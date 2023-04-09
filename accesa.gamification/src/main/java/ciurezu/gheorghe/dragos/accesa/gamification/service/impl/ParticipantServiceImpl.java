package ciurezu.gheorghe.dragos.accesa.gamification.service.impl;

import ciurezu.gheorghe.dragos.accesa.gamification.data.CategoriesForQuests;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.GamificationUser;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Participant;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Quest;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.ParticipantDto;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.QuestDto;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.ParticipantRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.QuestRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.repository.UserRepository;
import ciurezu.gheorghe.dragos.accesa.gamification.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final String ITEM_ALREADY_EXIST_MESSAGE = "Item Already Exits!";
    private final String TRY_AGAIN_LATER_MESSAGE = "Try again later";
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public ParticipantDto addParticipantToQuest(ParticipantDto participantDto, QuestDto questDto, String imgUrl) throws BadRequestException {

        if (participantDto == null || questDto == null) {
            throw new BadRequestException(TRY_AGAIN_LATER_MESSAGE);
        }
        if (participantDto.getUser() == null) {
            throw new BadRequestException(TRY_AGAIN_LATER_MESSAGE);
        }

        GamificationUser user = userRepository.findByUsername(participantDto.getUser().getUsername());
        Quest quest = questRepository.findByTitle(questDto.getTitle());

        if (user == null || quest == null) {
            throw new BadRequestException(TRY_AGAIN_LATER_MESSAGE);
        }

        if (quest.getUser().getUsername().equals(user.getUsername())) {
            throw new BadRequestException("The owner can't participate at it's own quest");
        }

        if (quest.getQuestCategory().getCategoryName().equals(CategoriesForQuests.RESPONSE_PHOTO)) {
            if (imgUrl == null) {
                throw new BadRequestException("Image Required!");
            }
        } else if (quest.getQuestCategory().getCategoryName().equals(CategoriesForQuests.RESPONSE_NORMAL)) {
            if (participantDto.getResponseMessage() == null) {
                throw new BadRequestException("Message response required");
            }
        }

        Participant participant = participantRepository.findByUserUsernameAndQuestTitle(user.getUsername(), quest.getTitle());
        if (participant != null) {
            throw new BadRequestException(ITEM_ALREADY_EXIST_MESSAGE);
        }

        Integer entrancePrice = quest.getEntrancePrice();
        int newTokens = user.getTokens() - entrancePrice;
        if (newTokens < 0) {
            throw new BadRequestException("Not enough money");
        }

        user.setTokens(newTokens);
        userRepository.save(user);
        participantDto.setImgUrl(imgUrl);

        participant = mapper.map(participantDto, Participant.class);
        participant = participantRepository.save(participant);

        return mapper.map(participant, ParticipantDto.class);
    }
}
