package com.GStagram.domain.user;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

import com.GStagram.domain.Image.Image;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity // 디비에 테이블을 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략을 데이터베이스를 따라간다.
	private Long id;

	@Column(length = 20, unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	private String website; // 웹사이트

	private String bio; // 자기소개

	@Column(nullable = false)
	private String email;

	private String phone;

	private String gender;

	private String profileImageUrl; // 사진

	private String role; // 권한

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마라
	// User를 Select할 때 해당 User id로 등록된 images들을 다 가져온다
	// LAZY (default) : User를 Select할 때 해당 User id로 등록된 images를 가져오지않는다.
	// -> getImages() 함수의 images들이 호출될 때 가져온다.
	// Eager : User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져온다.
	private List<Image> imges; // 양방향 배핑
	private LocalDateTime createDate;

	@PrePersist // 디비에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
