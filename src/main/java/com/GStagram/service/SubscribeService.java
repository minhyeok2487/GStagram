package com.GStagram.service;

import com.GStagram.domain.subscribe.SubscribeRepository;
import com.GStagram.handler.ex.CustomApiException;
import com.GStagram.web.dto.subscribe.SubscribeDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // Repository는 EntityManager를 구현해서 만들어져 있는 구현체

	@Transactional
	public void subscribe(int fromUserId, int toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}

	}

	@Transactional
	public void unsubscribe(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}

	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profile_image_url, ");
		sb.append("if ((SELECT 1 FROM subscribe WHERE from_user_id = ? AND to_user_id = u.id), 1, 0) subscribeState, "); // 물음표 principalId
		sb.append("if ((?=u.id), 1, 0) equalUserState "); // 물음표 principalId
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.to_user_id ");
		sb.append("WHERE s.from_user_id = ?"); // 물음표 pageUserId

		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
			.setParameter(1, principalId)
			.setParameter(2, principalId)
			.setParameter(3, pageUserId);

		// 쿼리 실행 (qlrm 라이브러리 필요 = Dto에 DB결과를 매핑하기 위해서)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
		return subscribeDtos;
	}
}
