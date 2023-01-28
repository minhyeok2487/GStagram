package com.GStagram.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	@Modifying
	@Query(value = "INSERT INTO likes(image_Id, user_Id, CREATE_DATE) VALUES(:imageId, :userId, NOW())"
		, nativeQuery = true)
	int mLikes(@Param("imageId") int imageId, @Param("userId") int userId);

	@Modifying
	@Query(value = "DELETE FROM likes WHERE image_Id = :imageId AND user_Id = :userId"
		, nativeQuery = true)
	int mUnLikes(@Param("imageId") int imageId, @Param("userId") int userId);
}
