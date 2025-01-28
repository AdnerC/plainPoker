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
            int indexLine = fileDatum.indexOf("|");
            int bidValue = Integer.parseInt(fileDatum.substring(indexLine+1));
            String fileDatum2 = fileDatum.substring(0, indexLine);
            String[] fileArray = fileDatum2.split(",");
//            System.out.println(Arrays.toString(fileArray));
//            System.out.println(bidValue);

            for (String card : fileArray){
                System.out.println(card);
                int totalSimilarCount = 0;
                for (int i = 1; i<fileArray.length-1;i++){
                    if (card.equals(fileArray[i])){
                        totalSimilarCount++;
                    }

                }
            }
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

}