package Uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SmartBot extends Player{
    private boolean hasDrawn = false;

    public SmartBot(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "SmartBot " + name;
    }


    @Override
    public void showHand() {
        System.out.println("Player " + name + " : Here are the cards in hand: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + hand.get(i));
        }
    }

    @Override
    public int getPoints() {
        return super.getPoints();
    }

//    smart bot counts for each color how many cards he has in hand and choose the color that he most cards has.
//    If he has no cards with regular colors. He will choose a random color.
    @Override
    public String chooseColor() {
        HashMap<String, Integer> colorCount = new HashMap<>();
        String colorCode = "";
        String cardColor = "";
        for(Card c: hand){
            cardColor = c.color.name();
            if(cardColor.equals("RED")){
                if(colorCount.containsKey("R")){
                    colorCount.put("R", colorCount.get("R") + 1);
                } else colorCount.put("R", 1);
            }
            else if(cardColor.equals("GREEN")){
                if(colorCount.containsKey("G")){
                    colorCount.put("G", colorCount.get("G") + 1);
                } else colorCount.put("G", 1);
            }
            else if(cardColor.equals("YELLOW")){
                if(colorCount.containsKey("Y")){
                    colorCount.put("Y", colorCount.get("Y") + 1);
                } else colorCount.put("Y", 1);
            }
            else if(cardColor.equals("BLUE")){
                if(colorCount.containsKey("B")){
                    colorCount.put("B", colorCount.get("B") + 1);
                } else colorCount.put("B", 1);
            }
        }

        String[] colorStrings = {"R", "Y", "G", "B"};
        if (colorCount.isEmpty()) {
           return colorStrings[(int) Math.floor(Math.random() * 4)];
        }
        else{
            int max= 0;
            for(Map.Entry<String, Integer> entry: colorCount.entrySet()){
                if(entry.getValue()>max){
                    max = entry.getValue();
                    colorCode = entry.getKey();
                }
            }
            return colorCode;
        }
    }

//when the input is a number, bot chooses the card with the index to play
    @Override
    public Card play(String indexStr) {
//        int cardIndex = 0;
        int cardIndex = Integer.parseInt(indexStr);
        System.out.println("*************************************************************");
        System.out.println("Card played: " + hand.get(cardIndex));
        return hand.remove(cardIndex);
    }

//after each round, the hasdrawn will be reset to false in the game loop
    @Override
    public void resetBotHasDrawn() {
        hasDrawn = false;
    }

//    the method below shows how the bot decide his next move.
//    First, bots check if he has valid cards to play
//    Then, he will select a valid card with the highest points to play. He shall not play the draw 4 card until he has no other valid cards
//    If he has no valid card to play,he will draw a card and skip if the new drawn card is not valid
    @Override
    public String inputAction(Card topCard, Color currentColor) {
        showHand();
        ArrayList<Card> validCards = new ArrayList<>();
        for (Card c : hand) {
            if (c.number == topCard.number || c.color.name().equals(currentColor.name())||(c.color.name().equals(currentColor.name()) && c.number == 13) ||c.number ==14) {
                validCards.add(c);
            }
        }
        if (validCards.isEmpty()) {
            if (!hasDrawn) {
                hasDrawn = true;
                return "draw";
            } else{
                hasDrawn = false;
                return "skip";
            }
//If he has only one valid card with number 13 or 14
        } else if (validCards.size() == 1 && (validCards.get(0).number == 14 ||validCards.get(0).number == 13)) {
            int indexOf14Or13 = 0;
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).number == 14 || hand.get(i).number == 13) {
                    indexOf14Or13 = i;
                }
            }
            return String.valueOf(indexOf14Or13);
        } else {
//            If bot has more than 1 valid card, he should choose the highest value card to play (but not the card 14).
            Card bestCardToPlay = new Card(0, Color.BLACK);
            for (Card c : validCards) {
                if (c.number != 14) {
                    if (c.number >= bestCardToPlay.number) {
                        bestCardToPlay = c;
                    }
                }
            }
            int indexOfBestCard = 0;
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).number == bestCardToPlay.number && hand.get(i).color == bestCardToPlay.color) {
                    indexOfBestCard = i;
                }
            }
            return String.valueOf(indexOfBestCard);
        }

    }


    @Override
    public int cardValueinHand() {
        return super.cardValueinHand();
    }

    @Override
    public void gainPoints(int gainedPoints) {
        super.gainPoints(gainedPoints);
    }

//    smart bot always declares uno correctly
    @Override
    public boolean unoDeclare() {
        System.out.println("If you have only one card left, please enter 'uno'! ");
        return true;
    }
//smart bot decides randomly if he would like to challenge
    @Override
    public String decisionToChallenge() {
        String[] challengeDecision = new String[]{"Y", "N"};
        String challengeChoice = challengeDecision[(int) Math.floor(Math.random() * 2)];
        System.out.println(challengeChoice);
        return challengeChoice;
    }

    @Override
    public void returnHand(Carddeck drawDeck) {
        while(hand.size()!=0){
            drawDeck.addCard(hand.remove(0));
        }
    }
}
