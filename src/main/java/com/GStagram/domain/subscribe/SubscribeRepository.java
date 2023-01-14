package com.GStagram.domain.subscribe;


import com.GStagram.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
public interface SubscribeRepository extends JpaRepository<Subscribe,Long> {

	@Modifying // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
	@Query(value = "INSERT INTO subscribe(FROM_USER_ID, TO_USER_ID, CREATE_DATE) VALUES (:fromUserId, :toUserId, now())"
	, nativeQuery = true)
	void mSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId); // 1 (변경된 행의 개수가 리턴됨), 실패하면 -1 리턴

	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE FROM_USER_ID = :fromUserId AND TO_USER_ID = :toUserId"
		, nativeQuery = true)
	void mUnSubscribe(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId =: pageUserId",nativeQuery = true)
	int mSubscribeState(@Param("principalId") Long principalId, @Param("pageUserId") Long pageUserId);

	@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId",nativeQuery = true)
	int mSubscribeCount(@Param("pageUserId") Long pageUserId);
}
