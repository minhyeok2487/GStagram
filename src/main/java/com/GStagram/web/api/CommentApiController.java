package com.GStagram.web.api;

import com.GStagram.config.auth.PrincipalDetails;
import com.GStagram.domain.comment.Comment;
import com.GStagram.service.CommentService;
import com.GStagram.web.dto.CMRespDto;
import com.GStagram.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final CommentService commentService;

	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@RequestBody CommentDto commentDto, @AuthenticationPrincipal
	PrincipalDetails principalDetails) {
		Comment comment = commentService.commentSave(commentDto.getContent(),
			commentDto.getImageId(), principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기 성공", comment), HttpStatus.CREATED);
	}

	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id) {
		return null;
	}
}
