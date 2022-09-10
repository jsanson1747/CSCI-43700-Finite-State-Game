import java.util.Optional;
import java.util.Scanner;

//CollectionNode.java

public class CollectionNode extends Node {

    private String item;
    private Character player;

    public CollectionNode() {
    }

    public String getItem() {
        return this.item;
    }

    public CollectionNode item(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Character getPlayer() {
        return this.player;
    }

    public CollectionNode player(Character player) {
        this.setPlayer(player);
        return this;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    @Override
    public Node process() {
        Optional<Node> chosenNode = Optional.of(getNext());
        Scanner inputCollector = new Scanner(System.in); // Create a Scanner object

        getPlayer().getInventory().add(getItem());
        Game.putText(this.getDescription());
        System.out.print("\t" + Game.ANSI_RED + "[Press Enter to continue]: " + Game.ANSI_RESET);
        String userInput = inputCollector.nextLine(); // Read user input

        this.next.previous = this;

        return chosenNode.get();
    }
}