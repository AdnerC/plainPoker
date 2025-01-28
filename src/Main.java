import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/TestInputFile");
        // you now have a list of Strings from the file "InputFile"
        for (String fileDatum : fileData) {

            String[] fileArray = getCardList(fileDatum);

            int bidAmount = calcBidValue(fileDatum);
            System.out.println(bidAmount);;
            int totalSimilarCount = 0;
            Poker newPoker = new Poker(bidAmount, fileArray);

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