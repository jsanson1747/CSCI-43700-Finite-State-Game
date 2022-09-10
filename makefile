Character.class: Character.java
	javac -g Character.java
Node.class: Node.java
	javac -g Node.java
Game.class: Game.java
	javac -g Game.java
run: Node.class Character.class Game.class
	java Game
clean: 
	rm *.class