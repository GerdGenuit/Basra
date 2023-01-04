package escaperoute.styles;

import java.awt.Color;

import escaperoute.agents.ContextCreator;

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
		for (int i = 0; i < ContextCreator.agents.length; i++) {
			if (ContextCreator.agents[i].name == "BalkanRoute") {
				//helles rot
				return new Color(255,102,102);
			}
			else if (ContextCreator.agents[i].name == "SouthRoute") {
				//helles blau
				return new Color(51,204,255);
			}
			else if (ContextCreator.agents[i].name == "NorthRoute1") {
				//helles orange
				return new Color(255,153,0);
			}
			else if (ContextCreator.agents[i].name == "NorthRoute2") {
				//helles grün
				return new Color(0,204,0);
			}
			else if (ContextCreator.agents[i].name == "AlternativeRoute") {
				//helles gelb
				return new Color(255,255,153);
			}
		}
		
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
