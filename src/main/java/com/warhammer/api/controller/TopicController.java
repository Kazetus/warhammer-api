package com.warhammer.api.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.warhammer.api.model.Topic;
import com.warhammer.api.service.TopicService;

public class TopicController {
	@Autowired
	private TopicService topicService;
	
	@GetMapping("/topics")
	public Iterable<Topic> getTopic() {
		return topicService.getTopic();
	}
	
	@GetMapping("/topics/{id}")
	public Topic getTopic(@PathVariable("id") final Long id){
		Optional<Topic> topic = topicService.getTopic(id);
		if(topic.isPresent()) {
			return topic.get();
		} else {
			return null;
		}
	}
	@PostMapping("/topics")
	public Topic createTopic(@RequestBody Topic topic) {
		//topic.setPassword(passwordEncoder.encode(topic.getPassword()));
		return topicService.saveTopic(topic);
	}
	@PutMapping("/topics/{id}")
	public Topic updateTopic(@PathVariable("id") final Long id, @RequestBody Topic topic) {
		//topic.setPassword(passwordEncoder.encode(topic.getPassword()));
		Optional<Topic> e = topicService.getTopic(id);
		if(e.isPresent()) {
			Topic currentTopic = e.get();
			
			String title = topic.getTitle();
			if(title != null) {
				currentTopic.setTitle(title);
			}
			Date dateTopic = topic.getDateTopic();
			if(dateTopic != null) {
				currentTopic.setDateTopic(dateTopic);
			}
			int idCategorie = topic.getIdCategorie();
			if(idCategorie != 0) {
				currentTopic.setIdCategorie(idCategorie);;
			}
			topicService.saveTopic(currentTopic);
			return currentTopic;
		} else {
			return null;
		}
	}
	@PatchMapping("/topics/{id}")
	public Topic patchTopic(@PathVariable("id") final Long id, @RequestBody Topic topic){
		//topic.setPassword(passwordEncoder.encode(topic.getPassword()));		
		Optional<Topic> e = topicService.getTopic(id);
		if(e.isPresent()) {
			Topic currentTopic = e.get();
			
			String title = topic.getTitle();
			Date dateTopic = topic.getDateTopic();
			int idCategorie = topic.getIdCategorie();
			if(title != null && dateTopic != null && idCategorie != 0) {
				currentTopic.setTitle(title);
				currentTopic.setDateTopic(dateTopic);
				currentTopic.setIdCategorie(idCategorie);
				topicService.saveTopic(currentTopic);
			return currentTopic;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/topics/{id}")
	public void deleteTopic(@PathVariable("id") final Long id) {
		topicService.deleteTopic(id);
	}
}
