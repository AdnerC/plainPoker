import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fiveOfAKind = 0;
        int fullHouse = 0;
        int fourOfAKind = 0;
        int threeOfAKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;

        ArrayList<String> fileData = getFileData("src/TestInputFile");
        // you now have a list of Strings from the file "InputFile"
        for (String fileDatum : fileData) {

            String[] fileArray = getCardList(fileDatum);

            int bidAmount = calcBidValue(fileDatum);
            System.out.println(bidAmount);;
            int totalSimilarCount = 0;
            Poker newPoker = new Poker(bidAmount, fileArray);
            String hand = newPoker.getHandType();
            if(hand.equals("Five of a kind")){
                fiveOfAKind++;
            }
            if(hand.equals("Four of a kind")){
                fourOfAKind++;
            }
            if(hand.equals("Full house")){
                fullHouse++;
            }
            if(hand.equals("Three of a kind")){
                threeOfAKind++;
            }
            if(hand.equals("Two pairs")){
                twoPair++;
            }
            if(hand.equals("One pair")){
                onePair++;
            }
            if(hand.equals("High Card")){
                highCard++;
            }
        }
        System.out.println("Number of five of a kind hands: " + fiveOfAKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfAKind);
        System.out.println("Number of three of a kind hands: " + threeOfAKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);


    }


    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }

    public static int calcBidValue(String fileDatum){
        int indexLine = fileDatum.indexOf("|");
        int bidValue = Integer.parseInt(fileDatum.substring(indexLine+1));
        return bidValue;
    }

    public static String[] getCardList(String fileDatum){
        int indexLine = fileDatum.indexOf("|");
        String fileDatum1 = fileDatum.substring(0, indexLine);
        String[] fileArray = fileDatum1.split(",");
        return fileArray;
    }
}