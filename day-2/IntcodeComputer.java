import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class IntcodeComputer {
    BufferedReader reader;
    
    IntcodeComputer(String inputPath) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get(inputPath));
    }
    
    /* Converts the input string into an integer array*/
    public int[] convertInputToArray(String input) {
        return Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
    
    public int[] executeIntcode(int[] inputArray) throws RuntimeException {
        try {
            for (int i = 0; i < inputArray.length; i += 4) {
                switch (inputArray[i]) {
                    case 1:
                        inputArray = intcodeAdd(inputArray, i);
                        break;
                    case 2:
                        inputArray = intcodeMult(inputArray, i);
                        break;
                    case 99:
                        return inputArray;
                    default:
                        throw new RuntimeException("Invalid intcode string");
                }
            }
            
            return inputArray;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
         
    }
    
    public int[] intcodeAdd(int[] inputArray, int startIndex) {
        inputArray[inputArray[startIndex + 3]] = inputArray[inputArray[startIndex + 1]] + inputArray[inputArray[startIndex + 2]];
        return inputArray;
    }
    
    public int[] intcodeMult(int[] inputArray, int startIndex) {
        inputArray[inputArray[startIndex + 3]] = inputArray[inputArray[startIndex + 1]]  * inputArray[inputArray[startIndex + 2]];
        return inputArray;
    }
    
    public BufferedReader getReader() {
        return this.reader;
    }
    
    public static void main(String[] args) throws IOException {
        IntcodeComputer computer = new IntcodeComputer("day-2-input.txt");
        int[] myArray = computer.convertInputToArray(computer.getReader().readLine());
        
        int[] arrayPart1 = myArray.clone();
        arrayPart1 = computer.executeIntcode(arrayPart1);
        System.out.println(Arrays.toString(arrayPart1));
        
        System.out.println(computer.findIntcodeNounVerb(myArray, 19690720));
    }
    
    public int findIntcodeNounVerb(int[] intcodeArr, int targetValue) {
        
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                int[] intcodeCopy = intcodeArr.clone();
                intcodeCopy[1] = i;
                intcodeCopy[2] = j;
            
                intcodeCopy = executeIntcode(intcodeCopy);
                
                if (intcodeCopy[0] == targetValue) {
                    return 100 * i + j;
                }
            }
        }
        
        return -1;
    }

}