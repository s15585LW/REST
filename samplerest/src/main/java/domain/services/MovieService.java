package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Movie;

public class MovieService {

	private static List<Movie> db = new ArrayList<Movie>();
	private static int currentId = 0;
	
	public List<Movie> getAll(){
		return db;
	}

	public Movie get(int id) {
		for(Movie m : db) {
			if(m.getId()==id)
				return m;
		}
		return null;
	}
	
	public void add(Movie m) {
		m.setId(++currentId);
		db.add(m);
	}

	public void update(Movie m) {
		for(Movie p : db) {
			if(p.getId()==m.getId()) {
				p.setTitle(m.getTitle());
			}
		}
	}


	public void delete(Movie m) {
	db.remove(m);
	}
	
}
