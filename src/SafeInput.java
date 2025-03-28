import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int result = 0;
        boolean done = false;

        do {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                result = console.nextInt();
                console.nextLine();
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("Error: input must be between " + low + " and " + high);
                }
            } else {
                System.out.println("Error: invalid input. Enter an integer.");
                console.nextLine();
            }
        } while (!done);

        return result;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String response = "";
        boolean valid = false;

        do {
            System.out.print(prompt + " [Y/N]: ");
            response = console.nextLine().trim().toUpperCase();
            if (response.equals("Y") || response.equals("N")) {
                valid = true;
            } else {
                System.out.println("Error: Please enter Y or N.");
            }
        } while (!valid);

        return response.equals("Y");
    }
}
