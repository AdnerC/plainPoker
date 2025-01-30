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
        //type reads one line
        for (String card : cards){
            int index = getCardIndex(card);
            if(index!=-1){
                type[index]++;
            }
        }
        //example for type
        //{2,3,0,0,0,0,0,0,0,0,0,0,0}
        //first count will be 2, next 3, then 0

        for (int count : type){
            if (count==5){
                return "Five of a kind";
            }
            if (count==4){
                return "Four of a kind";
            }

            if(count == 3){
                boolean pair =false;
                for (int j : type) {
                    if (j == 2) {
                        pair = true;
                    }
                    if (pair = true){
                        return "Full house";
                    }else {
                        return "Three of a kind";
                    }
                }
            }
            if (count==2){
                boolean onePair1 = false;
                boolean onePair2 = false;

                for (int j : type) {
                    if (j==2){
                        if (!onePair2 && onePair1){
                            onePair2=true;
                        }
                        if (!onePair1){
                            onePair1=true;
                        }
                    }
                }
                if (onePair1&&onePair2){
                    return "Two pair";
                }else {
                    return "One pair";
                }

                }

        }
        return "High card";

    }

    public int countCards(){
        return 0;
    }

    public int getCardIndex(String card){
        //if a specific card type is found, its index is returned
        String[] cardTypes = {"Ace","King","Queen","Jack","10","9","8",
        "7","6","5","4","3","2","1"};
        for (int i = 0; i < cardTypes.length; i++){
            if(cardTypes[i].equals(card))
                return i;
        }
        return -1;
    }

}
