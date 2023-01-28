package com.GStagram.domain.likes;

import com.GStagram.domain.Image.Image;
import com.GStagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// 외래키 유니크 제약조건 추가
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			name = "likes_uk",
			columnNames = {"imageId", "userId"}
		)
	}
)
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략을 데이터베이스를 따라간다.
	private int id;


	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image image; // 하나의 이미지는 좋아요 여러개 1 : N

	// 오류가 터지고 나서 잡기
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user; // 한 명의 유저는 좋아요 여러개 1 : N

	private LocalDateTime createDate;

	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
