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
public class RouteStyle implements NetworkStyleGIS {
	
	public static Color color= Color.GREEN;

	@Override
	public SurfacePolyline getSurfaceShape(RepastEdge edge, SurfacePolyline shape) {
		return new SurfacePolyline();
	}

	@Override
	public Color getLineColor(RepastEdge edge) {
		return color;
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
