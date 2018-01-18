package com.denissys.schedules.api.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.denissys.schedules.api.model.entity.Event;

public interface EventRepository extends MongoRepository<Event, String> {

	List<Event> findBy(String ownerId);
	
	List<Event> findBy(String ownerId, String eventId);
	
}
