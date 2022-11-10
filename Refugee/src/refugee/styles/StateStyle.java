package refugee.styles;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.render.BasicWWTexture;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.WWTexture;
import refugee.agents.State;
import repast.simphony.visualization.gis3D.PlaceMark;
import repast.simphony.visualization.gis3D.style.DefaultMarkStyle;
import repast.simphony.visualization.gis3D.style.MarkStyle;

/**
 * Style for a State.
 * 
 * @author Marie Genuit
 *
 */
public class StateStyle implements MarkStyle<State>{
	
	// können Ländergrenzen eingefügt werden? -> am besten für die Länder, die aktiv beteiligt sind, also das
	// und die Länder, die durchreist werden

	private Map<String, WWTexture> textureMap;
	
	public StateStyle() {
		/**
		 * Use of a map to store textures significantly reduces CPU and memory use
		 * since the same texture can be reused.  Textures can be created for different
		 * agent states and re-used when needed.
		 */
		textureMap = new HashMap<String, WWTexture>();
	
		String fileNameState = "icons/Gelber_Punkt-klein.svg.png";
		
		URL localUrl = WorldWind.getDataFileStore().requestFile(fileNameState);
		if (localUrl != null)	{
			textureMap.put("state", new BasicWWTexture(localUrl, false));
		}
	}
	
	/**
	 * The PlaceMark is a WWJ PointPlacemark implementation with a different 
	 *   texture handling mechanism.  All other standard WWJ PointPlacemark 
	 *   attributes can be changed here.  PointPlacemark label attributes could be
	 *   set here, but are also available through the MarkStyle interface.
	 *   
	 *   @see gov.nasa.worldwind.render.PointPlacemark for more info.
	 */
	@Override
	public PlaceMark getPlaceMark(State agent, PlaceMark mark) {
		
		// PlaceMark is null on first call.
		if (mark == null)
			mark = new PlaceMark();
		
		/**
		 * The Altitude mode determines how the mark appears using the elevation.
		 *   WorldWind.ABSOLUTE places the mark at elevation relative to sea level
		 *   WorldWind.RELATIVE_TO_GROUND places the mark at elevation relative to ground elevation
		 *   WorldWind.CLAMP_TO_GROUND places the mark at ground elevation
		 */
		mark.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
		mark.setLineEnabled(false);
		
		return mark;
	}
	
	/**
	 * Here we set the appearance of the TowerAgent using a non-changing icon.
	 */
	@Override
	public WWTexture getTexture(State agent, WWTexture currentTexture) {
			
		// If the texture is already defined, then just return the same texture since
		//  we don't want to update the tower agent appearance.  The only time the 
		//  below code will actually be used is on the initialization of the display
		//  when the icons are created.
		if (currentTexture != null)
			return currentTexture;
		
		return textureMap.get("state");
	}
	
	@Override
	public Offset getIconOffset(State agent){
		return Offset.CENTER;
	}

	@Override
	public double getElevation(State obj) {
		return 0;
	}

	@Override
	public double getScale(State obj) {
		return 1;
	}

	@Override
	public double getHeading(State obj) {
		return 0;
	}

	@Override
	public String getLabel(State obj) {
		return null;
	}

	@Override
	public Color getLabelColor(State obj) {
		return null;
	}

	@Override
	public Font getLabelFont(State obj) {
		return null;
	}

	@Override
	public Offset getLabelOffset(State obj) {
		return null;
	}

	@Override
	public double getLineWidth(State obj) {
		return 0;
	}

	@Override
	public Material getLineMaterial(State obj, Material lineMaterial) {
		return null;
	}
}