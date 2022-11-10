package refugee.agents;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;

import repast.simphony.context.Context;
import repast.simphony.space.gis.Geography;
import repast.simphony.util.ContextUtils;

/**
 * Zone agents are search areas around Human agents that are used
 * to detect agent types nearby.
 * 
 * @author Marie Genuit
 *
 */
public class ZoneAgent {
	
	// kann in dieser Klasse auch eingefügt werden, dass sich die Flüchtling an gewissen Routen orientieren,
	// also quasi eine Verbindung zur Klasse Route

	protected boolean visible = false;
	protected boolean active = false;
		
	/**
	 * Returns a list objects that intersect this zone's geometry from the list of
	 * near objects provided. 
	 * 
	 * @param nearObjects the list of near objects to check
	 * @return
	 */
	public List<?> lookForObjects(List<?> nearObjects){
		List<Object> objectList = new ArrayList<Object>();
		Context context = ContextUtils.getContext(this);

		Geography geography = (Geography)context.getProjection("Geography");
		
		// Find all features that intersect the zone feature
		Geometry thisGeom = geography.getGeometry(this);
		
		for (Object o : nearObjects){
			if (thisGeom.intersects(geography.getGeometry(o))){
				objectList.add(o);
			}
		}
		
		return objectList;
	}
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}