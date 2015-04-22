//package Lyft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Problem Statement: 
 * 
 * Calculate the detour distance between two different rides.
 * Given four latitude / longitude pairs, where driver one is traveling from
 * point A to point B and driver two is traveling from point C to point D, write
 * a function (in your language of choice) to calculate the shorter of the
 * detour distances the drivers would need to take to pick-up and drop-off the
 * other driver.
 * 
 * @author Akhileshwar Guli
 *
 */
public class CalcShortestDetourDistance {

	static final String SETTINGS_PATH = "LyftSettings.prop";
	
	static Driver D1 = null;
	static Driver D2 = null;
	
	public static void main(String[] args) {
		
	 readProperties();
	 
	 // If Driver 1 needs a Lift
	 double detourDistance_1 = (distance(D2.startPoint, D1.startPoint)+distance(D1.startPoint, D1.finishPoint)+distance(D1.finishPoint, D2.finishPoint));
	 // If Driver 2 needs a Lift
	 double detourDistance_2 = (distance(D1.startPoint, D2.startPoint)+distance(D2.startPoint, D2.finishPoint)+distance(D2.finishPoint, D1.finishPoint));
	
	 if(detourDistance_1 < detourDistance_2){
		 System.out.println("The Detour distance for Driver-2 is less at "+detourDistance_1
				             +"\n when compared to detour distance for Driver-1 at "+detourDistance_2
				             +".\n Hence Driver-2 can pick-up and drop Driver-1");
	 }
	 else if(detourDistance_1 > detourDistance_2){
		 System.out.println("The Detour distance for Driver-1 is less at "+detourDistance_2
	             +"\n when compared to detour distance for Driver-2 at "+detourDistance_1
	             +".\n Hence Driver-1 can pick-up and drop Driver-2"); 
	 }
	 else{
		 System.out.println("Detour distance for both the drivers is the same.\n It doesnt matter who give a lift to who.");
	 }
	 
	}
	
	static double distance(Point a, Point b){
		
		double dist = Math.sqrt((Math.pow((Math.abs(a.latitude-b.latitude)), 2))+(Math.pow((Math.abs(a.longitude-b.longitude)), 2)));
		return dist;
	}

	public static void readProperties(){
		
		Properties p = new Properties();
		BufferedReader reader;
		
		try{
			reader = new BufferedReader(new FileReader(SETTINGS_PATH));
			p.load(reader);
			
			double d1_X1 = Double.parseDouble(p.getProperty("Driver_1_startPoint_Lat"));
			double d1_Y1 = Double.parseDouble(p.getProperty("Driver_1_startPoint_Long"));
			double d1_X2 = Double.parseDouble(p.getProperty("Driver_1_finishPoint_Lat"));
			double d1_Y2 = Double.parseDouble(p.getProperty("Driver_1_finishPoint_Long"));
			
			
			double d2_X1 = Double.parseDouble(p.getProperty("Driver_2_startPoint_Lat"));
			double d2_Y1 = Double.parseDouble(p.getProperty("Driver_2_startPoint_Long"));
			double d2_X2 = Double.parseDouble(p.getProperty("Driver_2_finishPoint_Lat"));
			double d2_Y2 = Double.parseDouble(p.getProperty("Driver_2_finishPoint_Long"));
			
			D1 = new Driver(new Point(d1_X1,d1_Y1), new Point(d1_X2, d1_Y2));
			D2 = new Driver(new Point(d2_X1,d2_Y1), new Point(d2_X2, d2_Y2));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
