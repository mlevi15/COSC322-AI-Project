# COSC322-AI-Project
## Overview
A Java-based game client with a graphical user interface for the Game of the Amazons. A Monte Carlo tree search is used to find a winning strategy.
## API for Player-Server Communication
The communications between a game player and the game server are supported by a cosc322-dedicated Java library [ygraph-ai-smartfox-client](https://people.ok.ubc.ca/yongg/teaching/cosc322/project-and-assignments/cosc322-game-client-api/) (the library is a customized extension of the SmartFox game server).
The server has a set of "game rooms" that two players can log into in order to play. The server also provides functionalities to relay game-related messages from one player to other players in the same game room. The server-side support is (always) under development.
## Tasks
The goal of this project is to implement a Java-based game client with a graphical user interface that can play with the game clients developed by other teams.
All of the teams are required to implement a game client for the Game of the Amazons so that we can compete with each other. A description of the game can be found on [Wikipedia](https://en.wikipedia.org/wiki/Game_of_the_Amazons).
## Heuristic Evaluation Functions
As expected, the game tree of the Game of the Amazons is too big for a computer program to search. To play the game well, heuristic evaluation functions are needed. One of the heuristic evaluation functions for the game is the min-distance evaluation function studied by Dr. Martin Muller. For the details of this evaluation function, see Section 6 of his paper "[Experiments in Computer Amazons](http://library.msri.org/books/Book42/files/muller.pdf)".
## Team Members
* Karanmeet Khatra
* Kevin Feddema
* Levi Magnus
