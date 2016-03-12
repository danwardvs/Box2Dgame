
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;

public class Character extends Box {
	
	float movement_speed=50;
	
	public Character(World newWorld, BodyType newBodyType, float newX, float newY, float newWidth, float newHeight,
			float newAngle, float newR, float newG, float newB, float newA) {
		super(newWorld, newBodyType, newX, newY, newWidth, newHeight, newAngle, newR, newG, newB, newA);
		// TODO Auto-generated constructor stub
	}
	public void update(){
		 if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			applyImpulse(movement_speed,0);
		 }
		 if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				applyImpulse(-movement_speed,0);
		 }
		 if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
			 if(body.getLinearVelocity().y<=0.1f && body.getLinearVelocity().y>=-0.1f )
				applyImpulse(0,2000);
		 }
	}

}
