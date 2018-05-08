package domain;

import java.util.List;

public class Movie {

	private int id;
	private String title;
	private List<Comment> coments;
	private List<String> actors;
	private List<Float> rate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Comment> getComents() {
		return coments;
	}
	public void setComents(List<Comment> coments) {
		this.coments = coments;
	}
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public void deleteComents(int commentId) {
		coments.remove(commentId);
	}
	public float getRate() {
		int x=1;
		float ratex = 0;
		for(float p : rate )
			ratex = (ratex + p)/x++;	
		return ratex;
	}
	
	public void setRate(List<Float> rate) {
		this.rate = rate;
	}
	
}
