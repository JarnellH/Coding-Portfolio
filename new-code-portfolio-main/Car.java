public class Car extends LandVehicle{
	public int maxSpeed;

	public Car(String name, double velocity, double mass, int maxSpeed){
		super(name, mass);
		this.maxSpeed = maxSpeed;
	}

	public Car(String name, double mass, int maxSpeed){
		super(name,mass);
		this.maxSpeed = maxSpeed;
	}

	public void accelerate(double maxSpeed){
		if(newSpeed > this.maxSpeed){
			super.setVelocity(maxSpeed);
		}
		else{
			super.setVelocity(newSpeed);
		}
	}
	public String toString(){
		return super.toString()+", maxspeed:"+this.maxspeed;	
	}
}