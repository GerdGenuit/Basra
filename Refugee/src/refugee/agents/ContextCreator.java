package refugee.agents;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.context.space.gis.GeographyFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.GeographyParameters;
import repast.simphony.space.graph.Network;

/**
 * ContextBuilder for the Refugee model.
 * 
 * @author Marie Genuit
 *
 */
public class ContextCreator implements ContextBuilder {
	
	// Wo wird die Karte erstellt? Wie kann die Defaultkarte einen gr��eren Ausschnitt zeigen -> also den gesamten
	// relevanten Bereich Europas?
  
	int numAgents;
	double zoneDistance;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Context build(Context context) {		
		
		// Create the Geography projection that is used to store geographic locations
		// of agents in the model.
		GeographyParameters geoParams = new GeographyParameters();
		Geography geography = GeographyFactoryFinder.createGeographyFactory(null)
				.createGeography("Geography", context, geoParams);

		// Create the Network projection that is used to create the infection network.
		NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>(
				"infection network", context, true);
		@SuppressWarnings("unused")
		Network net = netBuilder.buildNetwork();
		
		// Geometry factory is used to create geometries
		GeometryFactory fac = new GeometryFactory();
		
		// Create State agents
		Parameters params = RunEnvironment.getInstance().getParameters();
	
		for (int i = 0; i < 1; i++) {
			//Create Germany
			State Germany = new State();
			context.add(Germany);
			Coordinate coord = new Coordinate(13, 52); // Create coordinates in Europe
			Point geom = fac.createPoint(coord);
			geography.move(Germany, geom);
		}

		// Create Human agents
		int humanCount = (Integer) params.getValue("human_count");
		for (int i = 0; i < humanCount; i++) {
			int energy = RandomHelper.nextIntFromTo(4, 10);
			
			final Human human = new Human();
			context.add(human);
			Coordinate coord0 = new Coordinate(Route.getBalkanRoute(0)); // Create coordinates in Europe
			Point geom0 = fac.createPoint(coord0);
			geography.move(human, geom0);
			if (i > 0) {
				Coordinate coord1 = new Coordinate(Route.getBalkanRoute(1));
				Point geom1 = fac.createPoint(coord1);
				geography.move(human, geom1);

			}
		}
		
		if (RunEnvironment.getInstance().isBatch()) {
			RunEnvironment.getInstance().endAt(100);
		}
		
		return context;
	}
}