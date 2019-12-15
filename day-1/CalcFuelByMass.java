package adventofcode.day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;

public class CalcFuelByMass {
    BufferedReader reader;
    
    CalcFuelByMass(String inputPath) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get(inputPath));
    }
    
    public int calcModuleMassRecursive(int mass, int totalMass) {
        int fuel = (mass / 3) - 2;

        if (fuel <= 0) {
            return totalMass;
        }
        
        totalMass = calcModuleMassRecursive(fuel, totalMass + fuel);
        return totalMass;
    }
    
    public int calcModuleMass(int mass) {
        return (mass / 3) - 2;
    }
    
    public BufferedReader getReader() {
        return this.reader;
    }
    
    public static void main(String[] args) throws IOException {
        String line;
        long sum = 0;
        
        CalcFuelByMass calculator = new CalcFuelByMass("day-1-input.txt");
        
        while ((line = calculator.getReader().readLine()) != null) {
            sum += calculator.calcModuleMassRecursive(Integer.parseInt(line), 0);
        }
        
        System.out.println(sum);
    }
}