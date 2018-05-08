package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;

public class ActorService {

	private static List<Actor> dba = new ArrayList<Actor>();
	private static int currentId = 0;
	
	public List<Actor> getAll(){
		return dba;
	}
	
	public Actor get(int id) {
		for(Actor a : dba) {
			if(a.getId()==id)
				return a;
		}
		return null;
	}
	
	public void add(Actor a) {
		a.setId(++currentId);
		dba.add(a);
	}

	public void update(Actor a) {
		for(Actor p : dba) {
			if(p.getId()==a.getId()) {
				p.setName(a.getName());
				p.setSurname(a.getSurname());
			}
		}
	}
	public List<String> showMovies(Actor a) {
		return a.getMovies();
	}
	
	public void delete(Actor a) {
	dba.remove(a);
	}
	

}
