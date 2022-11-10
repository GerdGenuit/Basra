package refugee.agents;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.graph.Network;
import repast.simphony.util.ContextUtils;

/**
 * States are constant and can accept Refugees or support them with goods.
 * 
 * @author Marie Genuit
 * 
 */
public class State {
	
	// hilfreich wären get- (erster Versuch besteht) und set-Methoden für die Koordinaten des Staates
	
	static Coordinate currentPosition = null;

		
	/**
	 * Initialization occurs once at tick 0.
	 */
	@ScheduledMethod(start = 0)
	public void init(){
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
	}
	
	/**
	 * XXX
	 */
	
	// kann hier das Aufnahmeverfahren der Staaten reinprogrammiert werden? Oder besser eine seperate Klasse dafür, auf
	// die in der step()-Methode verwiesen wird?
	
//	@ScheduledMethod(start = 1, interval = 1)
//	public void step() {
//	}

	/**
	 * This Method gives the position of the state.
	 */
	public static Coordinate getPosition() {
		Coordinate Position = currentPosition;
		return Position;
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
		
		Coordinate currentPosition = geography.getGeometry(this).getCoordinate();
		
		double x = (currentPosition.x - lastPosition.x);
		double y = (currentPosition.y - lastPosition.y);
	}
	
	// hört deswegen das Modell häufig bei 11 auf, wenn ich versuche, dass das Modell endet, wenn die Flüchtlinge
	// einen Staat erreichen?
	// das Modell soll enden, wenn z.B. 90% der Flüchtlinge einen Staat erreicht haben (wo sie z.B. in einem
	// Aufnahmeverfahren sind) -> dieser Parameter soll eine Variable sein, damit man ihn im Modell noch ändern kann
	/**
	 * Use a single state to track whether all humans are dead or no humans are
	 * moving to end the run.
	 */
	@ScheduledMethod(start=10, interval=1, pick=1)
	public void checkHumans(){
		Context context = ContextUtils.getContext(this);
		
		double tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		
		// If all humans dead
		if (!context.getAgentLayer(Human.class).iterator().hasNext()){
			RunEnvironment.getInstance().endAt(tick + 1);
		}
		
		// If humans exist but none are moving
		else{
			boolean humansRunning = false;
			for(Object o : context.getAgentLayer(Human.class)){
				if ( ((Human)o).isRunning() ){
					humansRunning = true;
					break;
				}
			}
			if (!humansRunning){
				RunEnvironment.getInstance().endAt(tick + 1);
			}
		}
	}
}
