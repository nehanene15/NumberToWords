import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by NehaNene on 6/14/17.
 */
public class App {

    public static void main(String args[]) throws Exception {
       // String input= args[0];

        String oneDigString[]={"zero","one","two","three","four","five","six","seven","eight","nine"};
        String tenTwenString[]={"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
        String dubDigString[]={"ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        Scanner scan = new Scanner(System.in);
        try {
            while (true) { //keeps prompting for new inputs
                String input = scan.next();
                int inputLength = input.length();
                int inputNum = Integer.parseInt(input);
                if (inputLength == 0 || inputLength > 4) {
                    System.out.println("Number out of bounds");
                }
                if (inputLength == 1) {
                    System.out.println(singleDigit(inputNum, oneDigString));
                }
                if (inputLength == 2) {
                    System.out.println(doubleDigit(input, oneDigString, dubDigString, tenTwenString));
                }
                if (inputLength == 3) {
                    System.out.println(threeDig(input, oneDigString, dubDigString, tenTwenString));
                }
                if (inputLength == 4) {
                    System.out.println(fourDig(input, oneDigString, dubDigString, tenTwenString));
                }

            }
        }
        catch (Exception e){
            throw new Exception("Invalid entry. Please a valid number");
        }

    }

    //method to get individual digits in an array from a String number
    public static int[] getIndividualDigits(String in){
        String indig[]=in.split("");
        int[] intDigits=new int[indig.length];
        for(int i=0;i<indig.length;i++){
            int digit = Integer.parseInt(indig[i]);
            intDigits[i]=digit;
        }
        return intDigits;
    }

    public static String singleDigit(int inputNum, String oneDigString[]){
        return oneDigString[inputNum];
    }

    public static String doubleDigit(String input, String oneDigString[],String dubDigString[],String tenTwenString[]){
        int[] dubDigits = getIndividualDigits(input);

        if(dubDigits[1]==0){ //second dig ends in 0
            return dubDigString[dubDigits[0]-1];
        }
        else{
            if(dubDigits[0]==1){ //ten-twenty
                return tenTwenString[dubDigits[1]-1];
            }
            else {
                return dubDigString[dubDigits[0]-1] +" "+ singleDigit(dubDigits[1],oneDigString);
            }
        }
    }

    public static String threeDig(String input, String oneDigString[],String dubDigString[],String tenTwenString[]){
        int[] threeDigits = getIndividualDigits(input);
        String dubDigits = input.substring(1);
        if(threeDigits[1]==0){ //if tens is 0
            return singleDigit(threeDigits[0],oneDigString)+" hundred and " + singleDigit(threeDigits[2],oneDigString);
        }
        return singleDigit(threeDigits[0],oneDigString)+" hundred and " + doubleDigit(dubDigits,oneDigString,dubDigString,tenTwenString);

    }

    public static String fourDig(String input, String oneDigString[],String dubDigString[],String tenTwenString[]){
        int[] fourDigits = getIndividualDigits(input);
        String thrDigits = input.substring(1); //last three digits
        String dubDigits = input.substring(2); //last two digits
        //5467
        if(fourDigits[1]==0){ //if hundreds place is 0
            return singleDigit(fourDigits[0],oneDigString)+" thousand and "+singleDigit(fourDigits[3],oneDigString);
        }
        return singleDigit(fourDigits[0],oneDigString)+" thousand, " + threeDig(thrDigits,oneDigString,dubDigString,tenTwenString);
    }
}
