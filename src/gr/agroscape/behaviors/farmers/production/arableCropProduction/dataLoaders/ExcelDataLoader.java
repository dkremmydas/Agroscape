package gr.agroscape.behaviors.farmers.production.arableCropProduction.dataLoaders;

import gr.agroscape.agents.Farmer;
import gr.agroscape.behaviors.ABehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.AArableCropProductionBhv;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhvContext;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhv_MP;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhv_Network;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import repast.simphony.valueLayer.GridValueLayer;

/**
 * For an arable crop behavior, loads from an excel workbook all necessary elements. <br />
 * Those elements are:
 * <ul>
 * <li>Crops, from sheet "crops"</li>
 * <li>Crop suitability
 * <ul>
 * @author Dimitris Kremmydas
 *
 */
public class ExcelDataLoader implements IScheduledBehaviorDataLoader<AArableCropProductionBhv> {

	private Workbook excelWB; 
	
	private ArrayList<ArableCropCultivation> crops;
	private SimulationContext simulationContext;
	
	private HashMap<Class<? extends AArableCropProductionBhv>,Collection<Farmer>> owners;
	
	
	public ExcelDataLoader(Workbook excelWB) {
		super();
		this.excelWB = excelWB;
		simulationContext=SimulationContext.getInstance();
	}



	@Override
	public void setup(ABehaviorContext<AArableCropProductionBhv> container) {
		this.loadCrops(container);
		this.loadCropSuitabilities(container);
		this.setupPaymentAuthority(container);
		this.addAgents(container);
	}
	
	/**
	 * 
	 * @param container
	 */
	private void loadCrops(ABehaviorContext<AArableCropProductionBhv> container) {
		Sheet sh = this.excelWB.getSheet("Crops");
		this.crops = new ArrayList<ArableCropCultivation>();
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int crop_id = (int)row.getCell(0).getNumericCellValue();
			String cropName = row.getCell(1).getStringCellValue();			
			this.crops.add(new ArableCropCultivation(cropName, crop_id, null));
		}
		
		//add those crops
		((ArableCropProductionBhvContext)container).getAvailableCrops().addAll(crops);

	}
	
	/**
	 * 
	 * @param container
	 */
	private void loadCropSuitabilities(ABehaviorContext<AArableCropProductionBhv> container) {
		if(this.crops==null) throw new NullPointerException("Crops shuld be loaded first !");
		
		
		//load crop suitability, all value equals to 1		
		for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
			GridValueLayer gv = new GridValueLayer("CropSuitability_"+c.getName(), true, simulationContext.getGridWidth(),simulationContext.getGridHeight());
			Sheet sh = this.excelWB.getSheet("CropSuitability_"+c.getName());
			
					for (int i = 0; i < gv.getDimensions().getWidth(); i++) {
						for (int j = 0; j <  gv.getDimensions().getHeight(); j++) {
							gv.set(1d, i,j);
						}
					}
					((ArableCropProductionBhvContext)container).getCropSuitabilities().put(c, gv);
		}
	} //end class
	
	
	/**
	 * 
	 * @param container
	 */
	private void setupPaymentAuthority(ABehaviorContext<AArableCropProductionBhv> container) {
		//load PaymentAuthority couple payments
				for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
					SimulationContext.getInstance().getPaymentAuthority().getCoupledPayments().put(c, 0l);
				}
	}
	
	
	/**
	 * 
	 * @param container
	 */
	private void addAgents(ABehaviorContext<AArableCropProductionBhv> container) {
		Collection<IScheduledBehavior<AArableCropProductionBhv>> r = new ArrayList<IScheduledBehavior<AArableCropProductionBhv>>();
		
		for (Map.Entry<Class<? extends AArableCropProductionBhv>, Collection<Farmer>> entry : owners.entrySet()) {
			if(entry.getKey() == ArableCropProductionBhv_MP.class) {
				for(Farmer f : entry.getValue()) {
					r.add(new ArableCropProductionBhv_MP(((ArableCropProductionBhvContext)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProductionBhvContext) container));
				}
			}
			else if (entry.getKey() == ArableCropProductionBhv_Network.class) {
				for(Farmer f : entry.getValue()) {
					r.add(new ArableCropProductionBhv_Network(((ArableCropProductionBhvContext)container).getAvailableCrops(),1000,(Farmer)f,(ArableCropProductionBhvContext) container));
				}
			}
			else  {
				throw new NullPointerException("Only ArableCropProductionBhv_Network.class and ArableCropProductionBhv_MP.class are currently supported");
			}
		}
		
		container.addAll(r);
	}
	
	

}
