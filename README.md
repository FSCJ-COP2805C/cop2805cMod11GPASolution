Blackjack is a card game which involves dealing an initial hand of 2 cards, followed by a sequence of single-card deals, or "hits", until the player either "holds", earns a score of 21 points (using rules described below), or "busts" (earns a score over 21). There are more rules for the actual game, but they are kept simple for this project.

This project is a Java FX application which repeatedly performs an initial 2-card Blackjack deal using a set of 52 card images.

To execute the project using IntelliJ, open the project at the top folder level and open the BlackJackGame class file. When IntelliJ prompts for the Project SDK, you must select a 1.8 version, Java 17 will not work. Then right click in the edit window of the source code and select "Run BlackJackGame.main()".

The required png image files are organized in an images folder and attached to this project. To execute the project, the file path prefix for the images is set.

A "deal" button is provided which, when pressed, causes the deck to be randomized, or "shuffled" using the Collections "shuffle" method, then displaying two cards.

Once the cards are dealt, the current total point value is displayed. The point value is determined as follows:

    Ace: 11 points (unless 2 Aces are dealt, in which case one of those Aces counts as 1 point, for a total of 12)
    Face value of 2-10: 2-10 points based on face value
    Jack, Queen, or King: 10 points
    When the score is 21 points (blackjack) the score is highlighted in red.

The files in the 52card folder are organized based on filename ("1.png" is the Ace of Spades, "13.png" is the King of Spades,  "14.png" is the Ace of Hearts, etc). 
