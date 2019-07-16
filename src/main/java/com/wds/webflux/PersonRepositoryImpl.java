package com.wds.webflux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository{
	
	Map<Integer, Person> personMap= new ConcurrentHashMap<Integer, Person>();
	public PersonRepositoryImpl()
	{
		personMap.put(1, new Person(1, "Robert","Ludlum","r@gmailcom"));
		personMap.put(2, new Person(2, "Luis","Grisham","l@gmail.com"));
		personMap.put(3, new Person(3, "Wanda","Lisden","w@gmail.com"));
	}
	@Override
	public Mono<Person> getPersonById(int id){
		return Mono.justOrEmpty(personMap.get(id));
	}
	
	@Override
	public Flux<Person> getAllPersons(){
		return Flux.fromStream(personMap.values().stream());		
	}
	
	@Override
	public Mono<Void> savePerson(Mono<Person> person){
		Mono<Person> personMono = person.doOnNext(value->{
			personMap.put((personMap.keySet().size()+1),value);
		});
		return personMono.then();
	}
}
