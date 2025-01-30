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
        // you now have a list of Strings from the file "TestInputFile"
        for (String fileDatum : fileData) {
            String[] fileArray = getCardList(fileDatum);

            int bidAmount = calcBidValue(fileDatum);
            Poker newPoker = new Poker(bidAmount, fileArray);
            System.out.println(Arrays.toString(fileArray));
            System.out.println(newPoker.getHandType());

        }


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