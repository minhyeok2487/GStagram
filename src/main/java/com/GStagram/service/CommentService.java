package com.GStagram.service;

import com.GStagram.domain.Image.Image;
import com.GStagram.domain.comment.Comment;
import com.GStagram.domain.comment.CommentRepository;
import com.GStagram.domain.user.User;
import com.GStagram.domain.user.UserRepository;
import com.GStagram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	private final UserRepository userRepository;
	@Transactional
	public Comment commentSave(String content, int imageId, int userId) {
		// Tip (객체를 만들 때 id값만 담아서 insert 할 수 있다.
		// 대신 return 시에 image 객체와 user객체는 id값만 가지고 있는 빈 객체를 리턴받는다.
		Image image = new Image();
		image.setId(imageId);

		//User user = new User();
		//user.setId(userId);

		User userEntity = userRepository.findById(userId).orElseThrow(() -> {
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});

		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		return commentRepository.save(comment);
	}

	@Transactional
	public void commentDelete() {
		return;
	}
}
