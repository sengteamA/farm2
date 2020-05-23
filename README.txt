Farm simulator
==============

By Nick Lee and Grant Wong.

(Last updated 22 May 2020.)

Welcome to the Farm Simulator!

Directory structure
-------------------

./farm/ contains the **source code**, **JUnit tests** as well as the
**compiled Javadoc documentation.**

./UML/ contains the UML class diagram and UML use case diagram as
PNG images.

The project report and the runnable .jar file are in the current directory.

How to run the JAR
------------------

1. Ensure Java 11 JDK is installed.

2. Retrieve the file called 33055308_56835905_FarmingSimulator.jar

3. Run this command to start the game:
java -jar 33055308_56835905_FarmingSimulator.jar

How to load into Eclipse
------------------------

1. Extract .zip archive to get the "farm" directory.

2. The "farm" directory is already an Eclipse project! Go to File > Import...
   then click Existing Projects into Workspace. Then select the "farm"
   directory as the root directory.

3. After pressing Finish, you should be able to see three folders: "src", which
   contains the source code; "test", which contains the JUnit tests; and
   "doc", which contains the compiled Javadoc documentation ready to view.

4. To run the game, run the class called "GameManager.java".

This has been tested with Java 11 JDK in the Erskine labs, with Eclipse 2019-09.

How to build the source code
----------------------------

Building the .jar file can be done with Eclipse: File > Export > Runnable JAR file.

Make sure to select "GameManager" as the launch configuration.
