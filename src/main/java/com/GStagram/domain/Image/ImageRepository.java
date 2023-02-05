package com.GStagram.domain.Image;


import com.GStagram.domain.subscribe.Subscribe;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
public interface ImageRepository extends JpaRepository<Image,Integer> {
	@Query(value = "SELECT * FROM image WHERE user_id IN (SELECT TO_USER_ID FROM subscribe WHERE FROM_USER_ID = :principalId) ORDER BY id DESC",nativeQuery = true)
	Page<Image> mStory(@Param("principalId") int principalId, Pageable pageable);

	@Query(value = "SELECT i.* FROM image i INNER JOIN (SELECT image_id, count(image_id) likeCount FROM likes GROUP BY image_id) c ON i.id = c.image_id ORDER BY c.likeCount DESC", nativeQuery = true)
	List<Image> mPopular();
}
