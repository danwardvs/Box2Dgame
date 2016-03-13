
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;

public class Character extends Box {
	
	float movement_speed=50;
	World gameWorld;
	WorldController gameController;
	
	public Character(WorldController newWorldController, World newWorld, BodyType newBodyType, float newX, float newY, float newWidth, float newHeight,
			float newAngle, float newR, float newG, float newB, float newA) {
		super(newWorld, newBodyType, newX, newY, newWidth, newHeight, newAngle, newR, newG, newB, newA);
		gameWorld = newWorld;
		gameController = newWorldController;
		// TODO Auto-generated constructor stub
	}
	public void update(){
		 if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D) ){
			applyImpulse(movement_speed,0);
		 }
		 if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A) ){
				applyImpulse(-movement_speed,0);
		 }
		 if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W) ){
			 if(body.getLinearVelocity().y<=0.1f && body.getLinearVelocity().y>=-0.1f )
				applyImpulse(0,2000);
		 }
		 if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			Box newProjectile = new Box(gameWorld,BodyType.DYNAMIC,getX()+2,getY(),0.2f,0.2f,0,1,0f,0f,0);
			newProjectile.applyLinearImpulse(50, 0);
 			gameController.createProjectile(newProjectile);
		 }
		 
	}

}
