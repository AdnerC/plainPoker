import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Keeps track of different hand types
        int fiveOfAKind = 0;
        int fullHouse = 0;
        int fourOfAKind = 0;
        int threeOfAKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;
        
        //List to store all poker hands
        ArrayList<Poker> hands = new ArrayList<Poker>();

        //List to store hands based on type
        ArrayList<Poker> fiveHands = new ArrayList<Poker>();
        ArrayList<Poker> fourHands = new ArrayList<Poker>();
        ArrayList<Poker> threeHands = new ArrayList<Poker>();
        ArrayList<Poker> fullHands = new ArrayList<Poker>();
        ArrayList<Poker> twoHands = new ArrayList<Poker>();
        ArrayList<Poker> oneHands = new ArrayList<Poker>();
        ArrayList<Poker> highHands = new ArrayList<Poker>();

        //Reads input file and processes hands
        ArrayList<String> fileData = getFileData("src/TestInputFile");
        // you now have a list of Strings from the file "TestInputFile"
        for (String fileDatum : fileData) {

            //Creates poker hand from file data
            Poker newPoker = new Poker(calcBidValue(fileDatum), getCardList(fileDatum));
            hands.add(newPoker);

            //Identifies hand type and increments variables
            String hand = Poker.getHandType(getCardList(fileDatum));
            if(hand.equals("Five of a kind")){
                fiveOfAKind++;
                fiveHands.add(newPoker);
            }
            if(hand.equals("Four of a kind")){
                fourOfAKind++;
                fourHands.add(newPoker);
            }
            if(hand.equals("Full house")){
                fullHouse++;
                fullHands.add(newPoker);
            }
            if(hand.equals("Three of a kind")){
                threeOfAKind++;
                threeHands.add(newPoker);
            }
            if(hand.equals("Two pair")){
                twoPair++;
                twoHands.add(newPoker);
            }
            if(hand.equals("One pair")){
                onePair++;
                oneHands.add(newPoker);
            }
            if(hand.equals("High card")){
                highCard++;
                highHands.add(newPoker);
            }
        }

        //Prints the number of each hand type
        System.out.println("Number of five of a kind hands: " + fiveOfAKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfAKind);
        System.out.println("Number of three of a kind hands: " + threeOfAKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);
        System.out.println();
        System.out.println("----------------------------------");

        //Sort hands based on type
        Poker.sortHands(fiveHands);
        Poker.sortHands(fourHands);
        Poker.sortHands(threeHands);
        Poker.sortHands(fullHands);
        Poker.sortHands(twoHands);
        Poker.sortHands(oneHands);
        Poker.sortHands(highHands);

        //Combine sorted list into one
        ArrayList<Poker> allHands =Poker.getHandSortedList(fiveHands,fourHands,fullHands,threeHands,twoHands,oneHands,highHands);

        //Calculate to bid value
        int totalBidAmount=0;
        int rank =1;

        for (Poker hand : allHands){
            int bidAmount = calcBidValue(hand.toString());
            totalBidAmount+=rank*bidAmount;
            rank++;
        }
        System.out.println("Total Bid Value: "+totalBidAmount);
        System.out.println("Total Bid Value With Jack Wild");

    }

    //Verifies data file is present
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
    //Obtains bid value from each line
    public static int calcBidValue(String fileDatum){
        int indexLine = fileDatum.indexOf("|");
        int bidValue = Integer.parseInt(fileDatum.substring(indexLine+1).trim());
        return bidValue;
    }
    //Returns the card list from each line
    public static String[] getCardList(String fileDatum){
        int indexLine = fileDatum.indexOf("|");
        String fileDatum1 = fileDatum.substring(0, indexLine);
        String[] fileArray = fileDatum1.split(",");
        return fileArray;
    }


}
