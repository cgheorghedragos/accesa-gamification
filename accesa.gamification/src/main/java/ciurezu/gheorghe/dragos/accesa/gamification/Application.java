package ciurezu.gheorghe.dragos.accesa.gamification;

import ciurezu.gheorghe.dragos.accesa.gamification.data.CategoriesForQuests;
import ciurezu.gheorghe.dragos.accesa.gamification.data.StandardQuestCategory;
import ciurezu.gheorghe.dragos.accesa.gamification.data.StandardRoles;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.BadgeCategory;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.QuestCategory;
import ciurezu.gheorghe.dragos.accesa.gamification.data.entity.Role;
import ciurezu.gheorghe.dragos.accesa.gamification.data.shared.*;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.BadRequestException;
import ciurezu.gheorghe.dragos.accesa.gamification.exceptions.ConflictException;
import ciurezu.gheorghe.dragos.accesa.gamification.service.BadgeService;
import ciurezu.gheorghe.dragos.accesa.gamification.service.QuestService;
import ciurezu.gheorghe.dragos.accesa.gamification.service.UserService;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, BadgeService badgeService, QuestService questService){

		return args ->{
			initBadgeCategories(badgeService);
			initUsers(userService);
			initBadge(badgeService);
			initQuestCategory(questService);
		};
	}

	private void initQuestCategory(QuestService questService) throws ConflictException {
		QuestCategoryDto questCategory = new QuestCategoryDto();
		questCategory.setCategoryName(CategoriesForQuests.RESPONSE_NORMAL);
		questService.saveCategory(questCategory);

		questCategory.setCategoryName(CategoriesForQuests.RESPONSE_PHOTO);
		questService.saveCategory(questCategory);
	}

	private void initBadge(BadgeService badgeService) throws ConflictException {
		BadgeCategoryDto badgeCategoryDto = new BadgeCategoryDto();
		badgeCategoryDto.setCategoryName(StandardQuestCategory.birdLover);

		BadgeDto badgeDto = new BadgeDto();
		badgeDto.setTitle("Bird lover");
		badgeDto.setBadgeCategory(badgeCategoryDto);
		badgeDto.setLevel(0);
		badgeDto.setMaxValue(5);
		badgeDto.setImgUrl("https://w7.pngwing.com/pngs/111/645/png-transparent-monster-energy-emblem-monster-energy-energy-drink-logo-blue-monster-desktop-monster-energy-logo-emblem-food-shield.png");

		badgeService.saveBadge(badgeDto);

		badgeDto.setLevel(1);
		badgeDto.setTitle("Gold bird lover");
		badgeDto.setMaxValue(100);
		badgeDto.setImgUrl("https://thumbs.dreamstime.com/b/werewolf-full-moon-badge-emblem-monochrome-icon-sticker-classic-halloween-style-monster-motif-featuring-against-95117872.jpg");
		badgeService.saveBadge(badgeDto);

		badgeCategoryDto.setCategoryName(StandardQuestCategory.questMaker);
		badgeDto.setLevel(0);
		badgeDto.setMaxValue(100);
		badgeDto.setTitle("Quest Maker");
		badgeDto.setImgUrl("https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71lY0SWC9bL._SL1500_.jpg");
		badgeService.saveBadge(badgeDto);

		badgeDto.setLevel(1);
		badgeDto.setTitle("Gold Quest Maker");
		badgeDto.setImgUrl("https://t4.ftcdn.net/jpg/03/50/11/83/360_F_350118359_fs2GIXzHjBhStQtRXq4yI927EcSxfS9A.jpg");
		badgeService.saveBadge(badgeDto);

	}

	private void initBadgeCategories(BadgeService badgeService) throws ConflictException {
		BadgeCategoryDto badgeCategoryDto = new BadgeCategoryDto();
		badgeCategoryDto.setCategoryName(StandardQuestCategory.questMaker);
		badgeService.saveBadgeCategory(badgeCategoryDto);

		badgeCategoryDto.setCategoryName(StandardQuestCategory.birdLover);
		badgeService.saveBadgeCategory(badgeCategoryDto);
	}

	private  void initUsers(UserService userService) throws Exception {
		RoleDto role = new RoleDto();
		role.setName(StandardRoles.ROLE_USER);

		GamificationUserDto user1 = new GamificationUserDto();
		user1.setEmail("aaa@yahoo.ro");
		user1.setPassword("mypsawrod");
		user1.setUsername("dragos");
		userService.saveUser(user1);

		GamificationUserDto user2 = new GamificationUserDto();
		user2.setEmail("aaabc@yahoo.ro");
		user2.setPassword("marrrrrr");
		user2.setUsername("calaretuviteaz");
		userService.saveUser(user2);

		userService.addRoleToUser(user1,role);
		userService.addRoleToUser(user2,role);

	}
}
