package com.wds.webflux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonHandler {
	private final PersonRepository personRepository;
	
	public PersonHandler(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public Mono<ServerResponse> listPerson(ServerRequest request){
		Flux<Person> person =  personRepository.getAllPersons();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(person, Person.class);
	}
	
	public Mono<ServerResponse> getPerson(ServerRequest request){
		int personId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound=ServerResponse.notFound().build();
		Mono<Person> personMono = personRepository.getPersonById(personId);
		return personMono.flatMap(person -> ServerResponse.ok()
				.contentType(APPLICATION_JSON)
				.body(BodyInserters.fromObject(person)))
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> createPerson(ServerRequest request){
		System.out.println("in create person");
		Mono<Person> person = request.bodyToMono(Person.class);
		return ServerResponse.ok().build(personRepository.savePerson(person));
	}
}
