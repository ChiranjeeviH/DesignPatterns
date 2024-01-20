/*
The Factory Design Pattern is a creational design pattern that provides an interface or method for creating objects in a super class,
but allows subclasses to alter the type of objects that will be created.

1) Create an interface / abstract class which has common functionality (Called as Product)
2) Create implementation classes which specifies specific implementation (Concrete product)
3) Create a Factory interface / abstract that declares factory method/s which helps create objects but leaves the choice of its type to
    subclasses
4) Create concrete classes to provide specific instances
 */
public class FactoryPatternDemo {
    // product interface

    interface Payment{
        void processPayment();
    }

    //Concrete Product
     class CreditCardPayment implements Payment{
        @Override
        public void processPayment(){
            System.out.println("Credit card payment");
        }
    }

     class DebitCardPayment implements Payment{
        @Override
        public void processPayment(){
            System.out.println("Debit card payment");
        }
    }

    //Factory Interface

     interface PaymentFactory{

        Payment createPaymentProcess();
    }

    // Concrete implementation
    class CreaditCardPaymentFactory implements PaymentFactory{

        @Override
        public Payment createPaymentProcess(){
            return new CreditCardPayment();
        }
    }

     class DebitCardPaymentFactory implements PaymentFactory{

        @Override
        public Payment createPaymentProcess(){
            return new DebitCardPayment();
        }
    }

    public void main() {
        PaymentFactory creditPaymentFactory = new CreaditCardPaymentFactory();
        PaymentFactory debitPaymentFactory = new DebitCardPaymentFactory();

        Payment creditCardPayment = creditPaymentFactory.createPaymentProcess();
        Payment debitCardPayment = debitPaymentFactory.createPaymentProcess();

        creditCardPayment.processPayment();
        debitCardPayment.processPayment();
    }

}
