package com.wds.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
	
	Mono<Person> getPersonById(int id);
	
	Flux<Person> getAllPersons();
	
	Mono<Void> savePerson(Mono<Person> Person);

}
