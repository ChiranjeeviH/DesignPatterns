import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonPatternsDemo {

    public static void main(String[] args) {
        System.out.println("==================== Getting singleton object for MyClass =======================");
        MyClass myClassInstance = MyClass.getMyClassInstance();

        MyClass myClassInstance1 = MyClass.getMyClassInstance();

        if (myClassInstance.hashCode() == myClassInstance1.hashCode()) {
            System.out.println("Hashcode's are equal");
        }else{
            System.out.println("Hashcode's are not equal "+myClassInstance.hashCode() + "  "+myClassInstance1.hashCode());
        }

        System.out.println("==================== breaking singleton object for MyClass using reflection =======================");

        // Method -1 (Reflection)

        try {
            Constructor<MyClass> constructor = MyClass.class.getDeclaredConstructor();

            constructor.setAccessible(true);

            // Create two instances using reflection
            MyClass instance1 = constructor.newInstance();
            MyClass instance2 = constructor.newInstance();

            System.out.println("Instance 1 hashCode: " + instance1.hashCode());
            System.out.println("Instance 2 hashCode: " + instance2.hashCode());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Exception occurred "+e);
        }

        System.out.println("==================== Getting singleton object for LazyHolderSingleton class =======================");

        LazyHolderSingleton lazyHolderSingleton = LazyHolderSingleton.getLazyHolderSingletion();
        LazyHolderSingleton lazyHolderSingleton1 = LazyHolderSingleton.getLazyHolderSingletion();

        System.out.println("Instance 1 hashCode: " + lazyHolderSingleton.hashCode());
        System.out.println("Instance 2 hashCode: " + lazyHolderSingleton1.hashCode());

        if (lazyHolderSingleton.hashCode() == lazyHolderSingleton1.hashCode()) {
            System.out.println("Hashcode's are equal");
        }

        System.out.println("==================== breaking singleton object for LazyHolderSingleton using reflection =======================");

        // Method -1 (Reflection)

        try {
            Constructor<LazyHolderSingleton> constructor = LazyHolderSingleton.class.getDeclaredConstructor();

            constructor.setAccessible(true);

            // Create two instances using reflection
            LazyHolderSingleton instance1 = constructor.newInstance();
            LazyHolderSingleton instance2 = constructor.newInstance();

            System.out.println("Instance 1 hashCode: " + instance1.hashCode());
            System.out.println("Instance 2 hashCode: " + instance2.hashCode());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Exception occurred "+e);
        }


        System.out.println("==================== Getting singleton object for UnbreakableLazyHolderSingleton class =======================");

        UnbreakableLazyHolderSingleton unbreakableLazyHolderSingletion = UnbreakableLazyHolderSingleton.getUnbreakableLazyHolderSingletion();
        UnbreakableLazyHolderSingleton unbreakableLazyHolderSingletion1 = UnbreakableLazyHolderSingleton.getUnbreakableLazyHolderSingletion();

        System.out.println("Instance 1 hashCode: " + unbreakableLazyHolderSingletion.hashCode());
        System.out.println("Instance 2 hashCode: " + unbreakableLazyHolderSingletion1.hashCode());

        if (unbreakableLazyHolderSingletion.hashCode() == unbreakableLazyHolderSingletion1.hashCode()) {
            System.out.println("Hashcode's are equal");
        }

        System.out.println("==================== breaking singleton object for UnbreakableLazyHolderSingleton using reflection =======================");

        // Method -1 (Reflection)

        try {
            Constructor<UnbreakableLazyHolderSingleton> constructor = UnbreakableLazyHolderSingleton.class.getDeclaredConstructor();

            constructor.setAccessible(true);

            // Create two instances using reflection
            UnbreakableLazyHolderSingleton instance1 = constructor.newInstance();
            UnbreakableLazyHolderSingleton instance2 = constructor.newInstance();

            System.out.println("Instance 1 hashCode: " + instance1.hashCode());
            System.out.println("Instance 2 hashCode: " + instance2.hashCode());

        } catch (IllegalStateException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Exception occurred : "+e.getCause().getLocalizedMessage());
        }

    }
}

/*
    1) First rule to make any class singleton is , Make the class final , so it can't be extendable
    2) Don't provide public constructor to avoid constructor injection,
        create a private constructor to avoid java creating default constructor
    3) Shouldn't provide any setter methods , so that object can't be changed via setters
    4) Provide getter method to return the object as constructor can't be accessible outside
    5) create instance as static so that, it will be shared object
 */

final class MyClass {
    private static final MyClass myClassInstance = new MyClass();

    private MyClass() {
    }

    public static synchronized MyClass getMyClassInstance() {
        return myClassInstance;
    }

}
// the above implementation has flaws
// getMyClassInstance() is called by multiple threads concurrently when myClassInstance is null.
// In such cases, multiple instances of MyClass might get created due to the lack of synchronization at the instance creation point.

//let's follow  "Initialization-on-demand holder idiom" or "Lazy Holder" to overcome multithread issues.

// inner classes are not loaded until they are referenced. The LazyHolder inner class is loaded only when getLazyHolderSingletion()
// is called for the first time. It creates a single instance of MyClass in a thread-safe manner as class loading in Java is inherently thread-safe.
final class LazyHolderSingleton{

    private LazyHolderSingleton() {
    }

    private static class LazyHolder{
        private static final LazyHolderSingleton INSTANCE = new LazyHolderSingleton();
    }

    public static LazyHolderSingleton getLazyHolderSingletion(){

        return LazyHolder.INSTANCE;
    }
}

//Unbreakable singleton class - here the class is not implementing cloning and keep track of instantiated and
//Synchronize the instantiation and throw an runtime exception , in case its already created once.
final class UnbreakableLazyHolderSingleton{

    private static boolean instantiated = false;
    private UnbreakableLazyHolderSingleton() {

        synchronized (UnbreakableLazyHolderSingleton.class){
            if(instantiated){
                throw new IllegalStateException("Singleton object already created ");
            }
            instantiated = true;
        }
    }

    private static class LazyHolder{
        private static final UnbreakableLazyHolderSingleton INSTANCE = new UnbreakableLazyHolderSingleton();
    }

    public static UnbreakableLazyHolderSingleton getUnbreakableLazyHolderSingletion(){

        return LazyHolder.INSTANCE;
    }
}

