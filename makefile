Character.class: Character.java
	javac -g Character.java
Node.class: Node.java
	javac -g Node.java
CollectionNode.class: Node.class CollectionNode.java
	javac -g CollectionNode.java
UseItemNode.class: CollectionNode.class UseItemNode.java
	javac -g UseItemNode.java
Game.class: Game.java
	javac -g Game.java
run: Node.class CollectionNode.class UseItemNode.class Character.class Game.class
	java Game
clean: 
	rm *.class