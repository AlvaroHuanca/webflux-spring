package com.wds.webflux;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonWebClient {
	
	
	private WebClient client = WebClient.create("http://localhost:9090");
	
	private Mono<ClientResponse> result = client.get()
			.uri("/person")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange();
	

	private Mono<Person> singlePerson = client.get()
			.uri("/person/1")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.flatMap(res -> res.bodyToMono(Person.class));
	
	public List<Person> getResult(){
		Flux<Person> personList = result.flatMapMany(res -> res.bodyToFlux(Person.class));
		return personList.collectList().block();
	}
}
