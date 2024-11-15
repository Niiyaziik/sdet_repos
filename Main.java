import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("№1");
        GenericClass<String> genericClass = new GenericClass<String>("строка");
        System.out.println("Пример c generic\n" + 
        genericClass.getValue());

        System.out.println("№2");
        int number = in.nextInt();
        IBool bool = num -> num % 13 == 0;
        System.out.println(bool.check(number));

        System.out.println("№3");
        float a = in.nextFloat();
        float b = in.nextFloat();
        float c = in.nextFloat();
        IDiscriminant discriminant = (A,B,C) -> B*B - 4*A*C;
        System.out.println(discriminant.discrim(a, b, c));
        in.close();
    }
}
