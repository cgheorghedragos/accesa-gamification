package ciurezu.gheorghe.dragos.accesa.gamification.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadPhoto(MultipartFile photo);
}
