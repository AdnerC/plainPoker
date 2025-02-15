import java.util.ArrayList;
import java.util.Arrays;

public class Poker {
    private int bidAmount;
    private String[] cards;
    private static boolean jackWilds;

    //Initializes poker hand with an array for the types of cards
    public Poker(int bid, String[] suits){
        bidAmount = bid;
        cards = suits;
        jackWilds =false;
    }
    //Determines type of poker hand
    public static String getHandType(String[] hand){
        //array to keep track of all types of cards(suits)
        int [] type = new int[13];
        int indexCount = 0;
        boolean hasJack =false;
        //Counts appearances for each card
        for (String card : hand){
            int index = getCardIndex(card);
            if(index!=-1){
                type[index]++;
                if (index==3){
                    hasJack=true;
                }
            }
        }
        //Checks for hand type based on counts
        for (int count : type){

            if (count==5){
                return "Five of a kind";
            }
            if (count==4){
                if (indexCount!=3){
                    return "Four of a kind";

                }
            }
            if(count == 3){
                for (int j : type) {
                    if (j == 2) {
                        return "Full house";
                    }else if (j==1){
                        return "Three of a kind";
                    }
                }
            }
            if (count==2){
                //to check for multiple 2 pairs we have 2 booleans, and one only triggers if the other has already be triggered
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
                    } else if (j==3) {// making sure we still get a full house even if we come across a two first
                        return "Full house";
                    }
                }
                if (onePair1&&onePair2){
                    return "Two pair";
                }else {
                    return "One pair";
                }
                }
            indexCount++;
        }
        return "High card";
    }


    //Gets the index of each card 
    public static int getCardIndex(String card){
        //if a specific card type is found, its index is returned
        String[] cardTypes = {"Ace","King","Queen","Jack","10","9","8",
        "7","6","5","4","3","2"};
        for (int i = 0; i < cardTypes.length; i++){
            if(cardTypes[i].equals(card))
                return i;
        }
        return -1;
    }
    //Compares 2 hands and returns the stronger hand
    public static String[] compareTwoHands(Poker first,Poker second){
        String[] firstHand = first.getCards();
        String[] secondHand = second.getCards();
        String[] cardTypes = {"Ace","King","Queen","Jack","10","9","8",
                "7","6","5","4","3","2"};//list that contains all possible card
        //initially compares the hand types
        if (getTypeValue(getHandType(firstHand))>getTypeValue(getHandType(secondHand))){
            return firstHand;
        }
        if (getTypeValue(getHandType(firstHand))<getTypeValue(getHandType(secondHand))){
            return secondHand;
        }

        //if types are the same: then compares the corresponding card indexes until there is a difference
        for (int i =0; i<firstHand.length;i++){
            if (getCardValue(firstHand[i])>getCardValue(secondHand[i])){
                return firstHand;
            } else if (getCardValue(firstHand[i])<getCardValue(secondHand[i])) {
                return secondHand;
            }
        }
        return cardTypes;
    }

    public static void sortHands(ArrayList<Poker> hands) {    //Sorts lists of poker hands from strongest to weakest
        int n = hands.size();//size of the hand we are sorting
        for (int i = 0; i < n - 1; i++) {// initial loop, runs n-1 amount of times
            for (int j = 0; j < n - i - 1; j++) {//to make sure we don't iterate over already sorted values
                if (Poker.compareTwoHands(hands.get(j), hands.get(j + 1)) == hands.get(j + 1).getCards()) {
                    //swaps hands
                    Poker temp = hands.get(j);
                    hands.set(j, hands.get(j + 1));
                    hands.set(j + 1, temp);
                }
            }
        }
    }
    //Assigns value to each hand for comparisons
    public static   int getTypeValue(String type){ //returns the value of a card by assigning each type an arbitrary value
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
    //Assigns value to each card to define their rank
    public static int getCardValue(String card){ // gets the value of a card based on an arbitrary value on the rank of the card
        if (card.equals("Ace")){
            return 100;
        }
        if (card.equals("King")){
            return 95;
        }
        if (card.equals("Queen")){
            return 85;
        }
        if (card.equals("Jack")&& !jackWilds) {
            return 80;
        } else if (card.equals("Jack")&& jackWilds) {
            return 5;

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

    public String toString() {// toString method
        return Arrays.toString(cards) + " | " + bidAmount;
    }
    //Getter for bid amount 
    public int getBidAmount() {//gets the bid amount
        return bidAmount;
    }

    public String[] getCards() { //gets the array with the cards
        return cards;
    }

    public void setJackWilds(boolean jackWilds) {
        Poker.jackWilds = jackWilds;
    }

    //Combines all sorted hand lists into a single list from weakest to strongest
    public static ArrayList<Poker> getHandSortedList(ArrayList<Poker> fiveHands, ArrayList<Poker> fourHands, ArrayList<Poker> fullHands, ArrayList<Poker> threeHands, ArrayList<Poker> twoHands, ArrayList<Poker> oneHands, ArrayList<Poker> highHands){

        ArrayList<Poker> allHands = new ArrayList<Poker>();//arrayList is used for the addAll method
        // must be reversed since the arrayLists are in strongest to weakest order
        allHands.addAll(highHands.reversed());
        allHands.addAll(oneHands.reversed());
        allHands.addAll(twoHands.reversed());
        allHands.addAll(threeHands.reversed());
        allHands.addAll(fullHands.reversed());
        allHands.addAll(fourHands.reversed());
        allHands.addAll(fiveHands.reversed());
        return allHands;
    }


}
