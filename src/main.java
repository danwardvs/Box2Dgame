import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
	  


public class main {
	
	  
	    public void start() {
	        try {
	        Display.setDisplayMode(new DisplayMode(800,600));
	        Display.create();
	    } catch (LWJGLException e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	  
	    // init OpenGL
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0,800, 0, 600, 1, -1);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glTranslatef(400, 300, 0);
	    while (!Display.isCloseRequested()) {
	    	
	        // Clear the screen and depth buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	         
	        // set the color of the quad (R,G,B,A)
	        GL11.glColor3f(0.5f,0.5f,1.0f);
	             
	        // draw quad
	        GL11.glBegin(GL11.GL_QUADS);
	            GL11.glVertex2f(0,0);
	        GL11.glVertex2f(200,0);
	        GL11.glVertex2f(200,200);
	        GL11.glVertex2f(0,200);
	        GL11.glEnd();
	  
	        Display.update();
	    }
	  
	    Display.destroy();
	    }
	  
	
	public static void main(String[] args) {
		 main quadExample = new main();
	        quadExample.start();
	}

}
