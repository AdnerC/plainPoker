import java.util.Arrays;

public class Poker {
    private int bidAmount;
    private String[] cards;

    public Poker(int bid, String[] suits){
        bidAmount = bid;
        cards = suits;
    }
    public String getHandType(){
        //array to keep track of all types of cards(suits)
        int [] type = new int[13];
        for (String card : cards){

        }
        return "e";

    }

    public int countCards(){

        return 0;
    }



}
