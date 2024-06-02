package user;
import java.util.Random;

public class Coordinate {
	

	private double x;
	private double y;
	
	public Coordinate(double d, double e) {
		this.x = d;
		this.y = e;
	}
	
	public Coordinate() {
		Random rand = new Random();
		int upperbound = 100; 
		this.x = rand.nextInt(upperbound);
		this.y = rand.nextInt(upperbound);
		
	};
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Coordinate: x=" + x + ", y=" + y;
	}
	

}
