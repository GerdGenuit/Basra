package escaperoute.styles;

import java.awt.Color;

import org.geotools.coverage.Category;
import org.geotools.util.NumberRange;

import gov.nasa.worldwind.render.SurfacePolyline;
import repast.simphony.space.gis.RepastCoverageFactory;
import repast.simphony.space.gis.WritableGridCoverage2D;
import repast.simphony.space.graph.RepastEdge;
import repast.simphony.visualization.gis3D.style.NetworkStyleGIS;

/**
 * Simple network style class example.
 * 
 * @author XX
 *
 */
public class MyNetworkStyle implements NetworkStyleGIS {

	@Override
	public SurfacePolyline getSurfaceShape(RepastEdge edge, SurfacePolyline shape) {
		return new SurfacePolyline();
	}

	@Override
	public Color getLineColor(RepastEdge edge) {
		// Create a coverage to act as a heat map that becomes more intense with
		//  the number of times the agent has visited a point.
		int maxColorIndex = 10; //RepastCoverageFactory.MAX_BYTE_COLOR_INDEX;
		Color[] whiteRedColorScale = new Color[maxColorIndex];

		// white to red color scale
		for (int i=0; i<whiteRedColorScale.length; i++) {
			int blueGreen = (255/maxColorIndex*(maxColorIndex-i));			
			whiteRedColorScale[i] = new Color(255, blueGreen, blueGreen); 
		}

		// Color scale coverage with no-data
		Category[] categories	= new Category[] {	
			new Category("No data", new Color(0,0,0,0), 0),  // transparent
			new Category("Level", whiteRedColorScale, NumberRange.create(1, maxColorIndex))
		};

//		WritableGridCoverage2D coverage2 = RepastCoverageFactory.createWritableByteIndexedCoverage(
//			"My data indexed", 20, 20, env, categories, null, 0);

//		geography.addCoverage("My indexed coverage", coverage2);
		
		return Color.GREEN;
	}

	@Override
	public double getLineOpacity(RepastEdge edge) {
		return 1.0;
	}

	@Override
	public double getLineWidth(RepastEdge edge) {
		return 5.0;
	}
}
