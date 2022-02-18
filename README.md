



Endterm Project Report submission
Mordvintsev Nikita
Astana IT University
PhD. Aitmukhanbetova Elvira
18 February 2022
























Outline:
	This document contains: the proofs for Endterm project’s criteria following, screenshots how it is working, and schema of project.
// I don't have a lot of comments in my code. In requirements written, paste the class code as text.
•	Title
•	Outline
•	Idea (Introduction)
•	Following criteria
•	Structure
•	Classes
•	Screenshots
•	Conclusion

PrSc – Project screen (Screen from project application)

Idea:
	Score  –  gaming convention, quantitative privilege. 
	Grid    –  two-dimensional array of squares. Every square have a type which affects on addition to score.
	Square behaviors:
Square	Behavior
Red	Increases score more if have a chain of squares to right and left.
Yellow	Increases score more if have a chain of squares to up and down.
Cyan	Increases score more if have a chain of squares to diagonals.

 

Client application that sends every time gap user’s grid and score data.
Data that active sends to SQL database every time gap and when window closes: Text (Grid of characters), score, count of squares that could be placed.


Following criteria:
	“Use the capabilities of OOP”:
•	Class and objects:
 
•	Encapsulation 


•	Inheritance:
 
•	Polymorphism:
o	Abstract class:
 
o	Interface:
 
o	Overriding:
 





o	Overloading:
 
o	Parent reference to Child:
 
o	JDBC:
 
 
o	Exception handling: 

o	GUI:
 
  
o	DBMS: SQL postgres
 



Structure:
	ContactSQL(Interface) setLogin, Password, Id with handling exceptions and output messages
	(abstract) User -> Input user (With interface) -> Checkable user(Usable class)

	CheckException(extends Exception)
	CheckFields(class which checks password and login field and send comments)

	Canvas(extends JPanel) drawable part, grid
	ContentPanel(extends JPanel) UI with JLabels, JButtons

	Main: Runnable(Window(extends JFrame)) and Timer(For accept commands from console)

Window creates object from:
Database(Class with database manipulations) 
CheckableUser(Active user)

SQL:
Finalproject(table):
Id	Integer
Login	Varchar(32)
Password	Varchar(64)
Score	Bigint
Map	Text
Width	Integer
Height	Integer
Count	Integer

Finalprojectops(table):
Id	Integer
oplogin	Varchar(32)

Screenshots:
 
 
Conclusion:
	In this project and in good Java practice course I learned a lot about OOP concepts. Got clear explanations from professor. Tried to practice my knowledge in this project; I found some improvements and flaws in my work during this development time: In OOP code should always be structured, up to the order of the methods inside classes; If this long project, I need to leave comments not under the announcement of function, but under the declaration; User interfaces and abstract classes more often when I don’t want to declare many objects inside. Use annotations more often and learn how to do it; It's better to practice with the new library in a new project; Do not create function on the task, but create class for all varieties and everything connected with this task. I want to express my gratitude for PhD. Aitmukhanbetova Elvira for competent and qualified instructions. I can confidently say that I have improved my knowledge in OOP, Java, and JDBC.
