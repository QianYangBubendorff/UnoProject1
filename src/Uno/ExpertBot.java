package Uno;

//this is the advanced level intelligent bot that we have not yet finished coding
public class ExpertBot extends Player {

    public ExpertBot(String name) {
        super();
    }

    @Override
    public int getPoints() {
        return super.getPoints();
    }

    @Override
    public void showHand() {
        System.out.println("Player " + name + " : Here are the cards in hand: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("Card " + (i + 1) + ": " + hand.get(i));
        }
    }

    @Override
    public void resetBotHasDrawn() {

    }

    @Override
    public String toString() {
        return "ExpertBot " + name;
    }

    @Override
    public Card play(String s) {
        return null;
    }

    @Override
    public String inputAction(Card topCard, Color currentColor) {
        return null;
    }

    @Override
    public int cardValueinHand() {
        return super.cardValueinHand();
    }

    @Override
    public void gainPoints(int gainedPoints) {
        super.gainPoints(gainedPoints);
    }

    @Override
    public boolean unoDeclare() {
        return true;
    }

    @Override
    public String decisionToChallenge() {
        return "N";
    }

    @Override
    public String chooseColor() {
        System.out.println(name + " --> You may select a different color to play: 'R' for red, 'B' for blue, 'G' for green and 'Y' for yellow");
        String[] colorStrings = {"R", "Y", "G", "B"};
        return colorStrings[(int) Math.floor(Math.random() * 4)];
    }

    @Override
    public void returnHand(Carddeck drawDeck) {
        while(hand.size()!=0){
            drawDeck.addCard(hand.remove(0));
        }
    }
}
