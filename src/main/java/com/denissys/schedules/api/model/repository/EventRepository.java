package com.denissys.schedules.api.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.denissys.schedules.api.model.entity.Event;

public interface EventRepository extends MongoRepository<Event, String> {

	@Query("{ 'ownerId' : ?0 }")
	List<Event> findBy(String ownerId);
	
	@Query("{ 'ownerId' : ?0, '_id' : ?1 }")
	List<Event> findBy(String ownerId, String id);
	
	@Query("{ 'ownerId' : ?0, '_id' : ?1 }")
	Event findOneBy(String ownerId, String eventId);

}
