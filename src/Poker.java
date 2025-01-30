import java.util.Arrays;

public class Poker {
    private int bidAmount;
    private String[] cards;

    public Poker(int bid, String[] suits){
        bidAmount = bid;
        cards = suits;
    }
    public String getHandType(String[] hand){
        //array to keep track of all types of cards(suits)
        int [] type = new int[13];
        //type reads one line
        for (String card : hand){
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
                boolean pair = false;
                for (int j : type) {
                    if (j == 2) {
                        pair = true;
                    }

                }
                if (pair){
                    return "Full house";
                }else {
                    return "Three of a kind";
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

    public String[] compareTwoHands(String[] firstHand,String[] secondHand){
        String[] cardTypes = {"Ace","King","Queen","Jack","10","9","8",
                "7","6","5","4","3","2","1"};
        if (getTypeValue(getHandType(firstHand))>getTypeValue(getHandType(secondHand))){
            return firstHand;
        }
        if (getTypeValue(getHandType(firstHand))<getTypeValue(getHandType(secondHand))){
            return secondHand;
        }

        for (int i =0; i<firstHand.length;i++){
            if (getCardValue(firstHand[i])>getCardValue(secondHand[i])){
                return firstHand;
            } else if (getCardValue(firstHand[i])<getCardValue(secondHand[i])) {
                return secondHand;
            }
        }
        return cardTypes;
    }

    public int getTypeValue(String type){
        if (type.equals("Five of a kind")){
            return 100;
        }
        if (type.equals("Four of a kind")){
            return 70;
        }
        if (type.equals("Full house")){
            return 50;
        }
        if (type.equals("Three of a kind")){
            return 30;
        }
        if (type.equals("Two pair")){
            return 20;
        }
        if (type.equals("One pair")){
            return 10;
        }
        return 9999;
    }

    public int getCardValue(String card){
        if (card.equals("Ace")){
            return 100;
        }
        if (card.equals("King")){
            return 95;
        }
        if (card.equals("Queen")){
            return 85;
        }
        if (card.equals("Jack")){
            return 80;
        }
        if (card.equals("10")){
            return 70;
        }
        if (card.equals("9")){
            return 65;
        }
        if (card.equals("8")){
            return 55;
        }
        if (card.equals("7")){
            return 50;
        }
        if (card.equals("6")){
            return 40;
        }
        if (card.equals("5")){
            return 35;
        }
        if (card.equals("4")){
            return 25;
        }
        if (card.equals("3")){
            return 20;
        }
        if (card.equals("2")){
            return 10;
        }
        return 9999;
    }



}
