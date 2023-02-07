package com.GStagram.domain.comment;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

import com.GStagram.domain.Image.Image;
import com.GStagram.domain.likes.Likes;
import com.GStagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment { // N, 1
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략을 데이터베이스를 따라간다.
	private int id;

	@Column(length = 100, nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "image_id")
	private Image image;

	private LocalDateTime createDate;

	@PrePersist // 디비에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
