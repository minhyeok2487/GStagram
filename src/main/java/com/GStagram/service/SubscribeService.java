package com.GStagram.service;

import com.GStagram.domain.subscribe.SubscribeRepository;
import com.GStagram.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;

	@Transactional
	public void subscribe(Long fromUserId, Long toUserId) {
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}

	}

	@Transactional
	public void unsubscribe(Long fromUserId, Long toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
