package escaperoute.styles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import escaperoute.agents.Route;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.render.BasicWWTexture;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PatternFactory;
import gov.nasa.worldwind.render.WWTexture;
import repast.simphony.visualization.gis3D.PlaceMark;
import repast.simphony.visualization.gis3D.style.MarkStyle;

public class RouteStyle implements MarkStyle<Route>{

	@Override
	public WWTexture getTexture(Route object, WWTexture texture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceMark getPlaceMark(Route object, PlaceMark mark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offset getIconOffset(Route obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getElevation(Route obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getScale(Route obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeading(Route obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel(Route obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Route obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getLabelFont(Route obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offset getLabelOffset(Route obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getLineWidth(Route obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Material getLineMaterial(Route obj, Material lineMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

}
