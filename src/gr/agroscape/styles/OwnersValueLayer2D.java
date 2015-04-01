package gr.agroscape.styles;

import java.awt.Color;

import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class OwnersValueLayer2D implements ValueLayerStyleOGL {
	
	protected ValueLayer layer;

	@Override
	public Color getColor(double... coordinates) {
		int v = (int)this.layer.get(coordinates);
		return new Color(v*3);
	}

	@Override
	public float getCellSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init(ValueLayer layer) {
		this.layer=layer;
		
	}
	
	

}
