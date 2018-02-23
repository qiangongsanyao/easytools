package test;

public class Boss {

	protected String name;
	protected int lv;
	protected int life;
	
	public Boss(String name, int lv, int life) {
		super();
		this.name = name;
		this.lv = lv;
		this.life = life;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
}
