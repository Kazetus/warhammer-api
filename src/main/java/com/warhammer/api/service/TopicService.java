package com.warhammer.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.warhammer.api.model.Topic;
import com.warhammer.api.repository.TopicRepository;

public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public Optional<Topic> getTopic(final Long id){
		return topicRepository.findById(id);
	}
	public Iterable<Topic> getTopic() {
		return topicRepository.findAll();
	}
	public void deleteTopic(final Long id) {
		topicRepository.deleteById(id);
	}
	public Topic saveTopic(Topic topic) {
		Topic savedTopic = topicRepository.save(topic);
		return savedTopic;
	}

}
