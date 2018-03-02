import java.util.Scanner;

public class ControlPolynomial {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.print("Enter the first polynomial: ");
            String poly1 = scanner.nextLine();

            System.out.print("Enter the second polynomial: ");
            String poly2 = scanner.nextLine();

            System.out.println("poly1: " + poly1);
            System.out.println("poly2: " + poly2);

            polynomial p = new polynomial(poly1);
            polynomial p2 = new polynomial(poly2);


            System.out.println("Enter 1 for addtion");
            System.out.println("Enter 2 for subtraction");
            System.out.print("Enter 3 for multiplication \t:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                polynomial p3 = p.add(p2);
                p3.to_string();
            }else if(choice == 2){
                polynomial p3 = p.sub(p2);
                p3.to_string();
            }else if(choice == 3){
                polynomial p3 = p.mult(p2);
                p3.to_string();
            }else{
                System.out.println("Invalid Input!");
            }

            System.out.print("Enter 1 to continue and 0 to exit: ");
            int ext = scanner.nextInt();
            scanner.nextLine();
            if(ext == 0) {
                exit = true;
            }

        }
    }
}
