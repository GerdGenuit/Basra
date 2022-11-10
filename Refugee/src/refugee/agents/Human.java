
package refugee.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.gis.Geography;
import repast.simphony.util.ContextUtils;

/**
 * Refugee flee from a country and tries to find a new home.
 * 
 * @author Marie Genuit
 *
 */
public class Human{
	
	// hilfreich wären get- (erster Versuch besteht) und set-Methoden für die Koordinaten der Personen
	
	protected boolean running = false;
	
	static Coordinate currentPosition = null;
	
	public Human () {
	}

	
	//public Human(int energy) {
	//}
	
	
	/**
	 * Initialization occurs once at tick 0.
	 */
	@ScheduledMethod(start = 0)
	public void init(){
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		
		// erzeugt eine zufällige Zahl zwischen 0 und 3, hiermit wird eine der vier Routen gewählt
		Random random = new Random();
		int WhichRoute = random.nextInt(3);
		
		if (WhichRoute == 0) {
				
		} else if (WhichRoute == 1) {
				
		} else if (WhichRoute == 2) {
				
		} else if (WhichRoute == 3) {
				
		}
	}
	
	/**
	 * XX
	 */
	@ScheduledMethod(start = 1, interval = 1)
//	public void step(Human human) {
//		Context context = ContextUtils.getContext(this);
//		Geography geography = (Geography)context.getProjection("Geography");
//		Point geom = human.BalkanRoute();
//		geography.move(human, geom);
//	}
	
	protected void updatePosition (Coordinate lastPosition){
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		
		Coordinate currentPosition = geography.getGeometry(this).getCoordinate();
		
		double x = (currentPosition.x - lastPosition.x);
		double y = (currentPosition.y - lastPosition.y);
	}
	
	protected void setPosition (Coordinate Position){
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		
		Coordinate currentPosition = geography.getGeometry(this).getCoordinate();
		
		double x = (Position.x);
		double y = (Position.y);
	}

	/**
	 * Move the Human towards the specified zone.
	 * 
	 * @param zone
	 */
	public void moveTowards(ZoneAgent zone) {
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		
		// Move towards the zone center point
		Point zoneCenter = geography.getGeometry(zone).getCentroid();
		currentPosition = geography.getGeometry(this).getCoordinate();
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		int speed = (Integer) params.getValue("humanSpeed");
		
		// The rate of movement.
		double moveSlowDown = 100 / speed;
		
		double x = (zoneCenter.getX() - currentPosition.x) / moveSlowDown;
		double y = (zoneCenter.getY() - currentPosition.y) / moveSlowDown;
		
		// Move towards the center by some displacement amount from the current position.
		geography.moveByDisplacement(this, x, y);
			
	}

	/**
	 * Move the search zone areas around the agent based on the agent's current
	 * location.
	 * 
	 * @param lastPosition
	 */
	protected void updateSearchZone(Coordinate lastPosition){
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		
		Coordinate currentPosisition = geography.getGeometry(this).getCoordinate();
		
		double x = (currentPosisition.x - lastPosition.x);
		double y = (currentPosisition.y - lastPosition.y);
	}
	
	public Coordinate getPosition() {
		Coordinate Position = currentPosition;
		return Position;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
