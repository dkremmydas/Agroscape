package gr.agroscape.behaviors.farmers.production.arableCropProduction.dataLoaders;

import gr.agroscape.behaviors.BehaviorContext;
import gr.agroscape.behaviors.IScheduledBehavior;
import gr.agroscape.behaviors.IScheduledBehaviorDataLoader;
import gr.agroscape.behaviors.farmers.production.agriculturalActivities.ArableCropCultivation;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.AArableCropProductionBhv;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhvContext;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhv_Immitator;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhv_MP;
import gr.agroscape.behaviors.farmers.production.arableCropProduction.ArableCropProductionBhv_Network;
import gr.agroscape.behaviors.farmers.production.products.Product;
import gr.agroscape.contexts.SimulationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
	
	public ExcelDataLoader(Workbook excelWB) {
		super();
		this.excelWB = excelWB;
		simulationContext=SimulationContext.getInstance();
	}



	@Override
	public void setup(BehaviorContext<AArableCropProductionBhv> container) {
		this.loadCrops(container);
		this.loadCropSuitabilities(container);
		this.setupPaymentAuthority(container);
		this.addAgents(container);
	}
	
	/**
	 * 
	 * @param container
	 */
	private void loadCrops(BehaviorContext<AArableCropProductionBhv> container) {
		Sheet sh = this.excelWB.getSheet("Crops");
		this.crops = new ArrayList<ArableCropCultivation>();
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int crop_id = (int)row.getCell(0).getNumericCellValue();
			String cropName = row.getCell(1).getStringCellValue();			
			this.crops.add(new ArableCropCultivation(cropName, crop_id, new ArrayList<Product>()));
		}
		
		//add those crops
		((ArableCropProductionBhvContext)container).getAvailableCrops().addAll(crops);

	}
	
	/**
	 * 
	 * @param container
	 */
	private void loadCropSuitabilities(BehaviorContext<AArableCropProductionBhv> container) {
		if(this.crops==null) throw new NullPointerException("Crops shuld be loaded first !");
		
		
		//load crop suitability, all value equals to 1		
		for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
			GridValueLayer gv = new GridValueLayer("CropSuitability_"+c.getName(), true, simulationContext.getGridWidth(),simulationContext.getGridHeight());
			Sheet sh = this.excelWB.getSheet("CropSuitability_"+c.getName());
			
			Iterator<Row> rowItr = sh.iterator(); 
			rowItr.next(); //skip first row
			for (int i = 0; i < gv.getDimensions().getWidth(); i++) {
				Row row = rowItr.next();
				for (int j = 0; j <  gv.getDimensions().getHeight(); j++) {
							gv.set(row.getCell(1+j).getNumericCellValue(), i,j);
				} //for cols
			} //for rows
			((ArableCropProductionBhvContext)container).getCropSuitabilities().put(c, gv);
		}//for crops
	} //end class
	
	
	/**
	 * 
	 * @param container
	 */
	private void setupPaymentAuthority(BehaviorContext<AArableCropProductionBhv> container) {
		if(this.crops==null) throw new NullPointerException("Crops shuld be loaded first !");
		//load PaymentAuthority couple payments
				for (ArableCropCultivation c : ((ArableCropProductionBhvContext)container).getAvailableCrops()) {
					SimulationContext.getInstance().getPaymentAuthority().getCoupledPayments().put(c, 0l);
				}
	}
	
	
	/**
	 * 
	 * @param container
	 */
	private void addAgents(BehaviorContext<AArableCropProductionBhv> container) {
		Collection<IScheduledBehavior<AArableCropProductionBhv>> r = new ArrayList<IScheduledBehavior<AArableCropProductionBhv>>();
		Sheet sh = this.excelWB.getSheet("Farmers");
		
		Iterator<Row> rowItr = sh.iterator(); 
		rowItr.next(); //skip first row
		while(rowItr.hasNext()) {
			Row row = rowItr.next();
			int agent_id = (int)row.getCell(0).getNumericCellValue();
			int isFarmer = (int)row.getCell(1).getNumericCellValue();	
			long liquidity = (long)row.getCell(2).getNumericCellValue();
			String farmer_type = row.getCell(3).getStringCellValue();
			if(isFarmer==1) {
				if(farmer_type.equals("Farmer_MP")) {
					r.add(new ArableCropProductionBhv_MP(
							((ArableCropProductionBhvContext)container).getAvailableCrops(),
							liquidity,
							simulationContext.getFarmersContext().findFarmerById(agent_id),
							(ArableCropProductionBhvContext) container)
						);
				 }
				else if(farmer_type.equals("Farmer_Net")) {
					r.add(new ArableCropProductionBhv_Network(
							((ArableCropProductionBhvContext)container).getAvailableCrops(),
							liquidity,
							simulationContext.getFarmersContext().findFarmerById(agent_id),
							(ArableCropProductionBhvContext) container)
						);
				 }
				else if(farmer_type.equals("Farmer_Immit")) {
					r.add(new ArableCropProductionBhv_Immitator(
							((ArableCropProductionBhvContext)container).getAvailableCrops(),
							liquidity,
							simulationContext.getFarmersContext().findFarmerById(agent_id),
							(ArableCropProductionBhvContext) container)
						);
				 }
				else  {
					throw new NullPointerException("Only ArableCropProductionBhv_Network.class, ArableCropProductionBhv_MP.class,"
							+ "ArableCropProductionBhv_Immitator.class are currently supported");
				}
			}
		}
		container.addAll(r);
	}
	
	

}
