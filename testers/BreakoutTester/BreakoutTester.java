import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.program.Program;
import acm.util.JTFTools;

@SuppressWarnings("deprecation")
public class BreakoutTester {

	public static Breakout game;
	
	public static void testWallReflection() throws InterruptedException {
		GraphicsProgram.canvasObjects = new ArrayList<>();
		Program.bricks.clear();
		Program.programWidth = 400;
		Program.programHeight = 600;
		
		System.out.println("Testing Breakout on wall reflection:");
		game = new Breakout();
		Worker gameRunner = new Worker();
		gameRunner.start();
				
		
		for (int i = 0; i < 10; i++) {
			Thread.sleep(400);
			if (i % 2 == 0) {
				Program.ball.setLocation(5, 300);
			} else {
				Program.ball.setLocation(Program.programWidth - Program.ball.getWidth() - 5, 300);
			}
		}
		
		if (GOval.wasOutOfWall) {
			System.out.println(GOval.outOfWallText);
			System.out.println("FAILED");
		} else {
			System.out.println("PASSED");
		}
		
		gameRunner.interrupt();
		gameRunner.stop();
		System.out.println();
	}
	
	public static void testPaddleStucking() throws InterruptedException {
		GraphicsProgram.canvasObjects = new ArrayList<>();
		Program.bricks.clear();
		Program.programWidth = 400;
		Program.programHeight = 600;
		
		System.out.println("Testing Breakout on paddle stucking:");
		game = new Breakout();
		Worker gameRunner = new Worker();
		gameRunner.start();
		
		boolean failed = false;
		
		for (int i = 0; i < 5; i++) {
			double x = (Program.paddle.getX() + (Program.paddle.getWidth() - Program.ball.getWidth()) / 2);
			double y = Program.paddle.getY() - 12;
			Program.ball.setLocation(x, y);
			
			Thread.sleep(300);
			
			double ballBottom = Program.ball.getY() + Program.ball.getHeight(); 
			if (ballBottom > 540) {
				System.out.println("After setting ball inside paddle, at coordinates (" + x + ", " + y + "),"
								+ " and waiting for 0.3 seconds, ball's position became " + Program.ball.getLocation()
								+ " which is too close to paddle.");
				failed = true;
				break;
			}
		}	
		
		gameRunner.interrupt();
		gameRunner.stop();
		
		System.out.println(failed ? "FAILED" : "PASSED");
		System.out.println();
	}
	
	public static void testBrickCollision() throws InterruptedException {
		GraphicsProgram.canvasObjects = new ArrayList<>();
		Program.bricks.clear();
		Program.programWidth = 400;
		Program.programHeight = 600;
		
		System.out.println("Testing Breakout on brick collision:");
		game = new Breakout();
		Worker gameRunner = new Worker();
		gameRunner.start();
		
		Thread.sleep(500);
		for (int i = 0; i < Program.bricks.size(); i++) {
			game.remove(Program.bricks.get(i));
		}
		
		Program.ball.setLocation(150, 1);
		Thread.sleep(100);
		
		boolean failed = false, collisionFailed = false, removeFailed = false;
		
		for (int i = 0; i < 10; i++) {
			GRect cxBrick = new GRect(50, 50);
			game.add(cxBrick, 125, 275);
			
			cxBrick.setFilled(true);
			
			double dx = GOval.dx, dy = GOval.dy;
			
			if (dx == 0 || dy == 0) {
				failed = true;
				System.out.println("Horizontal or vertical speed is 0");
				break;
			}
			
			double collisionX, collisionY, ballX, ballY, expdx = dx, expdy = dy;
			
//			if (i % 2 == 0) {
				// horizontal
				expdy = -dy;
				collisionX = cxBrick.getX() + cxBrick.getWidth() / 2;

				if (dy > 0) {
					collisionY = cxBrick.getY() + 1;
					ballX = collisionX - Program.ball.getWidth()/2 - dx;
					ballY = collisionY - Program.ball.getHeight() - dy;
				} else {
					collisionY = cxBrick.getY() + cxBrick.getHeight() - 1;
					ballX = collisionX - Program.ball.getWidth()/2 - dx;
					ballY = collisionY - dy;
				}
//			} 
//			else {
//				// vertical
//				expdx = -dx;
//				collisionY = cxBrick.getY() + cxBrick.getHeight() / 2;
//				
//				if (dx > 0) {
//					collisionX = cxBrick.getX() + 1;
//					ballX = collisionX - Program.ball.getWidth() - dx * 3;
//					ballY = collisionY - Program.ball.getHeight()/2 - dy * 3;
//				} else {
//					collisionX = cxBrick.getX() + cxBrick.getWidth() - 1;
//					ballX = collisionX - dx * 3;
//					ballY = collisionY - Program.ball.getHeight()/2 - dy * 3;
//				} 
//			}
			
			Program.ball.setLocation(ballX, ballY);
			Thread.sleep(300);
			
			
			if (!collisionFailed && (Math.abs(GOval.dx - expdx) > 0.01 || Math.abs(GOval.dy - expdy) > 0.01)) {
				failed = true;
				collisionFailed = true;
				System.out.println("After collision expected speedX and speedY: " + expdx + " " + expdy);
				System.out.println("Found: " + GOval.dx + " " + GOval.dy);
				System.out.println("Reflection was not correct");
			}
			
			if (!removeFailed && GraphicsProgram.canvasObjects.contains(cxBrick)) {
				failed = true;
				removeFailed = true;
				System.out.println("Brick was not removed after collision");
			}
			
			game.remove(cxBrick);
		}	

		gameRunner.interrupt();
		gameRunner.stop();
		System.out.println(failed ? "FAILED" : "PASSED");
		System.out.println();
	}
	

	
	public static void main(String[] args) throws IOException, InterruptedException {
		testWallReflection();
		testPaddleStucking();
		testBrickCollision();
	}
	
	private static class Worker extends Thread {
		@Override
		public void run() {
			game.init();
			game.run();
		}
	}
}
