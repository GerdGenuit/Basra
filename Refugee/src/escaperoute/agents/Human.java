package escaperoute.agents;

import java.util.Random;

import org.geotools.geometry.DirectPosition2D;
import org.opengis.geometry.DirectPosition;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.Network;
import repast.simphony.util.ContextUtils;

/**
 * A geolocated agent with a point location.
 * 
 * @author XX
 *
 */
public class Human extends Route{

	private String name;
	private int waypointCount = 1;
	private Waypoint lastWayPoint = null;
	private Point location = null;
	private Point oldLocation = null;
	private int locationCount = 0;
	private String route;
	private Boolean Chalkidiki = false;
	private Boolean reachedWaypoint = true;

	public Human(String name, Point geom) {
		this.name = name;
		this.location = geom;
		this.route = null;
	}

	@ScheduledMethod(start = 0, priority = ScheduleParameters.FIRST_PRIORITY)
	public void init() {
		Context context = ContextUtils.getContext(this);
		Geography<Human> geography = (Geography)context.getProjection("Geography");

		location = Start();
		geography.move(this, location);
		trackInCoverage();
		dropWaypoint();
		
		int i = Route.StartNumber;
		
		if (i == 1) {
			Random random = new Random();
			int WhichRoute = random.nextInt(1);
		
			if (WhichRoute == 0) {
				//über Chalkidiki
				route = "BalkanRoute";
				Chalkidiki = true;
			} else if (WhichRoute == 1) {
				//über Athen
				route = "BalkanRoute";
			}
		}
		if (i == 2 || i == 3) {
			route = "BalkanRoute";
		}
		if (i == 4) {
			route = "NorthRoute1";
		}
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = ScheduleParameters.FIRST_PRIORITY)
	public void step() {  
		if (Chalkidiki == true) {
			Chalkidiki();
			Chalkidiki = false;
		}
		Walk();
		trackInCoverage();
		if (reachedWaypoint == true) {
			dropWaypoint();
		}
		
		// XX
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dropWaypoint() {
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
		Network net = (Network)context.getProjection("Network");
		
		Coordinate loc = geography.getGeometry(this).getCoordinate();
		
		Waypoint wp = new Waypoint(waypointCount);
		waypointCount++;
		
		context.add(wp);
		geography.move(wp, fac.createPoint(new Coordinate(loc)));
		
		if (lastWayPoint != null)
			net.addEdge(lastWayPoint, wp);
		
		lastWayPoint = wp;
	}
	
	private GeometryFactory fac = new GeometryFactory();
	
	public void trackInCoverage() {
		Context context = ContextUtils.getContext(this);
		Geography geography = (Geography)context.getProjection("Geography");
	
		WritableGridCoverage2D coverage = (WritableGridCoverage2D)geography.getCoverage("My indexed coverage");
		
		if (coverage == null) return;
		
		Coordinate loc = geography.getGeometry(this).getCoordinate();

		double val = 0;

		DirectPosition pos = new DirectPosition2D(loc.x, loc.y);
		
		// Nothing to do if agent position not over coverage, and would throw a
		//   PointOutsideCoverageException if accessed
		if (!coverage.getEnvelope2D().contains(pos)) 
			return;
		
		double[] gridVal = null;
		gridVal = coverage.evaluate(pos, gridVal);
		
		int max = 6;  // max coverage index value
		
		if (gridVal != null) {
			val = gridVal[0] + 1;
		}
		if (val > max) val = max;  // dont exceed max
		
		coverage.setValue(pos, val);
	}

	private void Chalkidiki() {
		Context context = ContextUtils.getContext(this);
		Geography<Human> geography = (Geography)context.getProjection("Geography");
				
		Point geom = fac.createPoint(new Coordinate(23.42, 40.13));
		location = geom;
		locationCount = 3;
		geography.move(this, location);
		trackInCoverage();
		dropWaypoint();
	}
	
	/**
	 * Die Routen werden gewählt und durchgelaufen.
	 */
	private void Walk(){
		Context context = ContextUtils.getContext(this);
		Geography<Human> geography = (Geography)context.getProjection("Geography");
				
		if (route == "BalkanRoute" && locationCount == 1) {
			Random random = new Random();
			int WhichRoute = random.nextInt(3);
			
			if (WhichRoute == 0) {
				//ab Athen kann es weiter auf der Balkanroute gehen
				route = "BalkanRoute";
			} if (WhichRoute == 1) {
				//oder auf der Südroute
				route = "SouthRoute";
			} else if (WhichRoute == 2) {
				//oder auf der Alternativroute
				route = "AlternativeRoute";
			}
		}
		
		if (route == "NorthRoute1" && locationCount == 2) {
			Random random = new Random();
			int WhichRoute = random.nextInt(2);
			
			if (WhichRoute == 0) {
				//ab Sofia kann es weiter auf der Nordroute gehen
				route = "NorthRoute1";
			} if (WhichRoute == 1) {
				//oder auf der zweiten Hälfte der Nordroute
				route = "NorthRoute2";
				locationCount = 0;
			}
		}
		
		//die möglichen Startrouten sind entweder die Balkanroute oder die Nordroute
		if (route == "BalkanRoute") {
			oldLocation = location;
			location = BalkanRoute(locationCount);
		}
		
		if (route == "NorthRoute1") {
			oldLocation = location;
			location = NorthRoute1(locationCount);
			if (locationCount == 4) {
				locationCount = 7;
				route = "BalkanRoute";
			}
		}
		
		//Alternativrouten
		if (route == "SouthRoute") {
			oldLocation = location;
			location = SouthRoute(locationCount);
		}
		
		if (route == "AlternativeRoute") {
			oldLocation = location;
			location = AlternativeRoute(locationCount);
			if (locationCount == 5) {
				locationCount = 7;
				route = "BalkanRoute";
			}
		}
		
		if (route == "NorthRoute2") {
			oldLocation = location;
			location = NorthRoute2(locationCount);
		}
		
		locationCount++;
		speed();
		geography.move(this, location);
	}
	
	public void speed () {
		double x1 = location.getX();
		double y1 = location.getY();
		double x2 = oldLocation.getX();
		double y2 = oldLocation.getY();
		
		double difference = Math.sqrt(111.3 * ((y2 - y1) * (y2 - y1)) + 71.5 * ((x2 - x1) * (x2 - x1)));
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		double humanSpeed = (Double) params.getValue("human_speed");
		
		if (difference > humanSpeed) {
			
			double differenceX = x1 - x2;
			double differenceY = y1 - y2;
			//Steigung der Geraden berechnen
			//double m = (y1 - y2) / (x1 - x2);
			// Winkel der Geraden berechnen
			//double alpha = Math.atan(m);
			//neuen Punkt berechnen
			//double xNeuTest = x2 + (differenceX + (humanSpeed * Math.sin(alpha)));
			//double yNeuTest = y2 + (differenceY + (humanSpeed * Math.cos(alpha)));
			
			double a = difference / humanSpeed;
			double factor = 1 / a;
			
			double xNeu = x2 + (differenceX * factor);
			double yNeu = y2 + (differenceY * factor);
			
			//double control = Math.sqrt(111.3 * y2 * yNeu + 71.5 * x2 * xNeu);
			
			oldLocation = fac.createPoint(new Coordinate(xNeu, yNeu));
			
			reachedWaypoint = false;
		}
		else reachedWaypoint = true;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString(){
		return name;
	}
	
	public Point getLocation() {
		return location;
	}

}