# Farm simulator

By Nick Lee and Grant Wong.

(Last updated 22 May 2020.)

Welcome to the Farm Simulator!

## How to load into Eclipse

1. Extract .zip archive to get the "farm" directory.

2. The "farm" directory is already an Eclipse project! Go to File > Import...
   then click Existing Projects into Workspace. Then select the "farm"
   directory as the root directory.

3. After pressing Finish, you should be able to see three folders: "src", which
   contains the source code; "test", which contains the JUnit tests; and
   "doc", which contains the Javadoc documentation.

This has been tested with Java 11 JDK in the Erskine labs, with Eclipse 2019-09.

## How to build the source code

Building the .jar file can be done with Eclipse: File > Export > Runnable JAR file.

Make sure to select "GameManager" as the launch configuration.

## How to run

1. Ensure Java 11 JDK or above is installed.

2. Extract .zip archive and retrieve the .jar file.

3. Run this command to start the game:
java -jar 33055308_56835905_FarmingSimulator.jar
