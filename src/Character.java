
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.WeldJoint;
import org.jbox2d.dynamics.joints.WeldJointDef;


public class Character extends Box {
	
	float movement_speed=50;
	World gameWorld;
	WorldController gameController;
	int bullet_time;
	int bullet_time_delay=300;
	Body sensorBody;
	Box Feet;
	//WeldJointDef sensorBodyJoint;
	
	public Character(WorldController newWorldController, World newWorld, boolean newIsSensor, float newX, float newY, float newWidth, float newHeight,
			float newAngle, float newR, float newG, float newB, float newA) {
		super(newWorld, BodyType.DYNAMIC, newIsSensor, newX, newY, newWidth, newHeight, newAngle, newR, newG, newB, newA);
		gameWorld = newWorld;
		gameController = newWorldController;
		body.setFixedRotation(true);
		
	
		
		/*
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(newX, newY-height/2);
		bodyDef.angle = newAngle;
		sensorBody = newWorld.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(width, 0.2f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1;
		fixtureDef.friction = 0.3f;
		sensorBody.createFixture(fixtureDef);
		*/
		Box Feet = new Box(gameWorld,BodyType.DYNAMIC,true,newX,newY-newHeight,newWidth,0.2f,0,0,1,0,0);
		
		Vec2 anchor = new Vec2(0,newHeight);
		gameController.createBox(Feet);
		
		WeldJointDef jointDef = new WeldJointDef();
		jointDef.initialize(body, Feet.body, anchor);
        jointDef.collideConnected = false;
        jointDef.referenceAngle = 0;
        
        gameWorld.createJoint(jointDef);
		
		

	}
	public void createProjectile(float newSpeed, float newAngle, float newX, float newY){
		Projectile newProjectile = new Projectile(gameWorld,BodyType.DYNAMIC,true,getX()+newX,getY()+newY,0.2f,0.2f,0,1,0f,0f,0,2000);
		newProjectile.applyLinearImpulse(newSpeed, newAngle);
		gameController.createProjectile(newProjectile);
	}
	
	public void update(int delta){
		 //System.out.println(sensorBody.m_contactList);
		 bullet_time+=delta;
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
		 if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && bullet_time>=bullet_time_delay){
			bullet_time=0;
			createProjectile(300,20,2,0.5f);
			createProjectile(300,0f,2,0);
			createProjectile(300,-20f,2,-0.5f);
			
		 }
		 
	}

}
