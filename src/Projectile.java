

import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class Projectile extends Box {
	
	float time;
	float lifetime;
	
	public Projectile(World newWorld, BodyType newBodyType, float newX, float newY, float newWidth, float newHeight,
			float newAngle, float newR, float newG, float newB, float newA, float newLifetime) {
		super(newWorld, newBodyType, newX, newY, newWidth, newHeight, newAngle, newR, newG, newB, newA);
		// TODO Auto-generated constructor stub
		lifetime = newLifetime;
		body.setBullet(true);
		
		
	}
	public boolean update(int newDelta){
		time += newDelta;
		if(time>=lifetime)
			return true;
		else
			return false;
			
			
		
	}

}
