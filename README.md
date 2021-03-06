# Battleship by Group 12 

Java version of the game Battleship.

## Contributors
Kaylee Novakovski

Athena McNeil-Roberts

Liam Langill

Matt Black

## Game Description

Two player game played using two 10x10 game boards. Initially players will place their ships on their respective board. Players cannot see where the other player has placed their ships. Once all ships have been placed, players will take turns guessing coordinates on the other player's board where they think a ship has been placed. If the guessed coordinates have a ship placed on it, the player guessing will be notified of a hit. If the guessed coordinates do not have a ship placed on it, the player guessing will be notified of a miss. Players will take turns guessing until all of one player's ships are sunk. The player to sink all of the other player's ships first wins.

## Installation and Running 
In Terminal or Command Prompt
1. Clone entire directory to a new desktop folder.
2. In the console, direct to the desktop folder using cd < filepath >
3. Use command --> javac Console/*.java
4. Use command --> javac -classpath . GUI/*.java
5. To play Console version, use command --> java Console.Game
6. To play GUI version, use command --> java GUI.GUI

In Eclipse
1. Clone entire directory to a new desktop folder.
2. Create a new Jave Project in Eclipse.
3. Click File -> Import.
4. Import the Console and GUI folders from the cloned desktop folder to the src folder.
5. Click Project -> Properties.
6. Click the ‘Add External JAR’ button on the right of the window.
7. From the cloned desktop folder, select ‘jfxrt.jar’ and click Open.
8. Click Apply and Close.
9. To play the console version: Open the Console window under Window -> Show View. 
10. Right click the Game class and click Run as Java Application. 
11. To play the graphical version: Right click the GUI class and click Run as Java Application.
  
## Requirements

JDK

javafx

## Class Diagram

<img width="610" alt="Screen Shot 2019-04-12 at 7 21 43 PM" src="https://user-images.githubusercontent.com/47372331/56072925-91bbfe80-5d59-11e9-8b0f-9ae65755c210.png">

## References

Game, Player, Board, Site, Ship < https://github.com/ymarcus93/Java-Battleship?fbclid=IwAR2Bkk9JSBfDBB2jlLA-jv-1deUzVDVkgWXgwTG-3cQ1xV9oqE98hFXmqhA >

GUI, GameInitialization, AIPlayer < private dropbox:  Examples> Lecture16_OODesign by Nathaly Verwaal >

#### Other:

UC Teaching Assistant: Kanishqk Singh

stackoverflow < https://stackoverflow.com >

class notes by Nathaly Verwaal

Battleship game used for reference (by AlmasB) < https://github.com/AlmasB/Battleship >

Oracle javafx info < https://docs.oracle.com/javase/8/javase-clienttechnologies.htm >

Graphics by Kaylee Novakovski

Explosion Sound Effect <https://www.youtube.com/watch?v=nRwM7UEQ8Q0&>
