package com.GStagram.service;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.Image.Image;
import com.GStagram.domain.Image.ImageRepository;
import com.GStagram.web.dto.image.ImageUploadDto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

	private final ImageRepository imageRepository;

	@Value("${file.path}")
	private String uploadFolder;

	public void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		//log.info(imageFileName);

		Path imageFilePath = Paths.get(uploadFolder+imageFileName);

		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		imageRepository.save(image);
		//log.info(imageEntity.toString());
	}

	@Transactional(readOnly = true) // 영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영) X
	public Page<Image> imageStory(int principalId, Pageable pageable) {
		Page<Image> images = imageRepository.mStory(principalId, pageable);
		return images;
	}

}
