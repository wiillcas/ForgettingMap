# ForgettingMap
## Setup
I have saved this project as a Java Application (Version 8) with the command line arguments as "5" "Apple,Pear,Orange,Peach,Water,Door,Sofa". To run this application either open it up in an IDE and run the application or run the jar file on the commandline passing the command line arguments to it:
`java -jar .\target\origamitest-1.0-SNAPSHOT.jar "5" "Apple,Pear,Orange,Peach,Water,Door,Sofa"`


## Decisions
- I decided to create producers and consumers for this application to test the concurrency of the forgettingmap.
- The underlining data structure that forgetting map uses is the HashMap so that I can read/write key/value pairs into memory with no duplicates.
- I created dummy data using a bag of words so that I could test out replacing key/value pairs with a new pair.
- For deciding the key/value pair to replace when the forgettingmap is full and a new pair is to be added I find the first pair with the lowest finds as that would have been added earlier and should be more likely to have been used more by now.