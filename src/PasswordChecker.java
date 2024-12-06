import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) throws IOException {
        HashTableSeparateChaining hashTable = new HashTableSeparateChaining();

        BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
        int value = 1;
        String word;

        while ((word = br.readLine()) != null) {
            hashTable.insert(word, value);
            value++;
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Your Password:");
        String password = scanner.nextLine();

        if (isStrongPassword(password, hashTable)) {
            System.out.println("Password is strong.");
        } else {
            System.out.println("Password is not strong.");
        }

        scanner.close();
    }

    public static boolean isStrongPassword(String password, HashTableSeparateChaining hashTable) {
        if (password.length() < 8) {
            return false;
        }

        if (hashTable.search(password) != -1) {
            return false;
        }

        for (int i = 0; i < password.length() - 1; i++) {
            String word = password.substring(0, i + 1);
            String digit = password.substring(i + 1);
            if (hashTable.search(word) != -1 && digit.length() == 1 && digit.charAt(0) >= '0' && digit.charAt(0) <= '9') {
                return false;
            }
        }

        return true;
    }

}
