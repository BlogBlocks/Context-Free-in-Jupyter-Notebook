import java.util.Scanner; 
import java.io.*;
import java.util.Random;

public class ContextFreeGrammar {

    private static String nt, useExp = "";
    private static int totalWeight = 0, randomNum;
    private static ArrayUnsortedList<Production> data = new ArrayUnsortedList<Production>();
    private static Production pObj;

    public static void main(String[] args) throws IOException {

        String result = "<S>";
        File myFile = new File("cfg.txt");
        Scanner fileScan = new Scanner(myFile);

        // Read and process each line of the file
        while (fileScan.hasNext()) {
            Production p = new Production(fileScan.nextLine());
            data.add(p);
        }

        // If the result string still contains non-terminals
        while (result.contains("<")) {

            // Get the non-terminal
            nt = result.substring(result.indexOf('<')+1, result.indexOf('>'));

            // Cannot condense because we have an ambiguous
            // totalWeight. Hence, we need to iterate twice:
            // 1. To generate appropriate random number from totalWeight
            // 2. To determine when the totalWeight exceeds that random number.

            iterateData(false);

            Random rand = new Random(); // Generate a random number object
            randomNum = rand.nextInt(totalWeight - 1) + 1; // Generate actual random number

            // Resets
            totalWeight = 0;
            data.reset();

            iterateData(true);

            // Replace the non-terminal with the expression to use
            result = result.replaceFirst("<" + nt + ">", useExp);
            totalWeight = 0; // Reset totalWeight;
        }

        System.out.println(result);
    }

    public static void iterateData(boolean GETEXP) {
        // Iterate through list again
        for (int i = 0; i < data.size(); i++) {

            // Get the current value
            pObj = data.getNext();

            // Keep track of weight
            if (nt.equalsIgnoreCase(pObj.getNonTerminal())) {
                totalWeight += pObj.getWeight();

                if (GETEXP) {
                    if (totalWeight > randomNum) {
                        useExp = pObj.getExpression(); // This is expression to use
                        break;
                    }
                }
            }
        }
    }
}