# DesignPatterns
Design Patterns in Java (Creational, Behavioral, Structural)

Creational :
=========================   Singleton =================================
1) First rule to make any class singleton is , Make the class final , so it can't be extendable
2) Don't provide public constructor to avoid constructor injection,create a private constructor to avoid java creating default constructor
3) Shouldn't provide any setter methods , so that object can't be changed via setters
4) Provide static getter method to return the object as constructor can't be accessible outside
5) create instance as static so that, it will be a shared object.
