package gr.agroscape.dataLoaders;

import gr.agroscape.authorities.LandPropertyRegistry;
import gr.agroscape.authorities.PaymentAuthority;
import gr.agroscape.contexts.CropsContext;
import gr.agroscape.contexts.FarmersContext;
import gr.agroscape.contexts.MainContext;
import gr.agroscape.contexts.PlotsContext;
import gr.agroscape.crops.Crop;

import java.util.HashMap;

import repast.simphony.valueLayer.GridValueLayer;

/**
 * This class loads agents and initial data from an excel file.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class ExcelDataLoader implements ISimulationDataLoader {
	
	private String excel_location;

	@Override
	public void loadCropsContext(CropsContext context) {
		// TODO Auto-ge nerated method stub
		
	}

	@Override
	public void loadPlotsContext(PlotsContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadFarmersContext(FarmersContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadCropSuitabilityMap(HashMap<Crop, GridValueLayer> csmap,
			MainContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLandPropertyRegistry(LandPropertyRegistry lpr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initPaymentAuthority(PaymentAuthority pa) {
		// TODO Auto-generated method stub
		
	}


	

}
