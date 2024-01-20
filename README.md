# DesignPatterns
Design Patterns in Java (Creational, Behavioral, Structural)

Creational :

=========================   Singleton =================================
1) First rule to make any class singleton is , Make the class final , so it can't be extendable
2) Don't provide public constructor to avoid constructor injection,create a private constructor to avoid java creating default constructor
3) Shouldn't provide any setter methods , so that object can't be changed via setters
4) Provide static getter method to return the object as constructor can't be accessible outside
5) create instance as static so that, it will be a shared object.

=========================  Factory =======================================
The Factory Design Pattern is a creational design pattern that provides an interface or method for creating objects in a super class,
but allows subclasses to alter the type of objects that will be created.

1) Create an interface / abstract class which has common functionality (Called as Product)
2) Create implementation classes which specifies specific implementation (Concrete product)
3) Create a Factory interface / abstract that declares factory method/s which helps create objects but leaves the choice of its type to
    subclasses
4) Create concrete classes to provide specific instances
