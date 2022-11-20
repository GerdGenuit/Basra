package escaperoute.agents;

import java.awt.Color;

import org.geotools.coverage.Category;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.util.NumberRange;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import repast.simphony.context.Context;
import repast.simphony.context.space.gis.GeographyFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.gis.Geography;
import repast.simphony.space.gis.GeographyParameters;
import repast.simphony.space.gis.RepastCoverageFactory;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.Network;

/**
 * XXX
 * 
 * @author XXX
 *
 */
public class ContextCreator implements ContextBuilder {
		
	public Context build(Context context) {
	
		GeographyParameters geoParams = new GeographyParameters();
		Geography geography = GeographyFactoryFinder.createGeographyFactory(null)
				.createGeography("Geography", context, geoParams);

		GeometryFactory fac = new GeometryFactory();

		NetworkBuilder<?> netBuilder = new NetworkBuilder<Object>("Network", context, true);
		Network net = netBuilder.buildNetwork();
				
		// Startpunkt irgendwo im Meer - kann eigentlich weg
		Point geom = fac.createPoint(new Coordinate(31, 33));

		Parameters params = RunEnvironment.getInstance().getParameters();
		
		int humanCount = (Integer) params.getValue("human_count");
		for (int i = 0; i < humanCount; i++) {
			Human agent = new Human("Refugee", geom);
			context.add(agent);
			geography.move(agent, geom);
		}
		

		// um Europa
		ReferencedEnvelope env = new ReferencedEnvelope(30, -12, 36, 60, DefaultGeographicCRS.WGS84);

		// Create a coverage to act as a heat map that becomes more intense with
		//  the number of times the agent has visited a point.
//		int maxColorIndex = 10; //RepastCoverageFactory.MAX_BYTE_COLOR_INDEX;
//		Color[] whiteRedColorScale = new Color[maxColorIndex];

		// white to red color scale
//		for (int i=0; i<whiteRedColorScale.length; i++) {
//			int blueGreen = (255/maxColorIndex*(maxColorIndex-i));			
//			whiteRedColorScale[i] = new Color(255, blueGreen, blueGreen); 
//		}

		// Color scale coverage with no-data
		Category[] categories	= new Category[] {	
				new Category("No data", new Color(0,0,0,0), 0),  // transparent
//				new Category("Level", whiteRedColorScale, NumberRange.create(1, maxColorIndex))
		};

		WritableGridCoverage2D coverage2 = RepastCoverageFactory.createWritableByteIndexedCoverage(
				"My data indexed", 20, 20, env, categories, null, 0);

		geography.addCoverage("My indexed coverage", coverage2);
				
		return context;
	}
}