package sw.atu.ie;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class Dapping {

    public double getDappingLevel(int windBeaufort, int tempCelsius) { 
    	// for sugeno
    	FIS fis = FIS.load("dappingSugeno.fcl", true);	
    	// for Mamdani 
//        FIS fis = FIS.load("dapping.fcl", true);			
        FunctionBlock fb = fis.getFunctionBlock("getDappingLevel"); 
        fis.setVariable("wind", windBeaufort); 
        fis.setVariable("temperature", tempCelsius); 
        fis.evaluate(); 
        Variable dapping = fb.getVariable("dapping"); 
        JFuzzyChart.get().chart(fis); 
        JFuzzyChart.get().chart(dapping.getDefuzzifier(), "Dapping Level", true); 
        return dapping.getValue(); 
    }
    
    public static void main(String[] args) {
        Dapping dappingTest = new Dapping();
        double result = dappingTest.getDappingLevel(8, 10); 
        System.out.println("Dapping Level: " + result + "%");
    }
}
