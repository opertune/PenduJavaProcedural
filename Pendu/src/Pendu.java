import java.io.IOException;
import java.util.Scanner;

public class Pendu {
    public static boolean error = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] pendu = {"  |", "\n  o", "\n \\", "|", "/", "\n /", " \\"};
        Scanner input = new Scanner(System.in);
        String word;
        StringBuilder hiddenWord = new StringBuilder("");
        char letter;
        int nbError = 0;

        // word input
        do {
            System.out.println("Saisir un mot (entre 3 et 8 caractères): ");
            word = input.nextLine();
        }while (word.length() < 3 | word.length() > 8);

        // for each char in word replace with dot
        hiddenWord.append(".".repeat(word.length()));
        clearConsole(20);


        do {
            System.out.println("\n"+hiddenWord);
            // letter input
            System.out.println("\nSaisir une lettre: ");
            letter = input.next().charAt(0);

            // Check if letter is in word
            inWord(letter, word, hiddenWord);
            if (error){
                nbError++;
                for (int i = 0; i < nbError; i++){
                    System.out.print(pendu[i]);
                }
            }
            error = false;
        }while (nbError < 7 && !hiddenWord.toString().equals(word));

        if (nbError == 7){
            System.out.println("\nLOSE");
        }else{
            System.out.println(hiddenWord);
            System.out.println("\nWIN");
        }
    }

    public static void clearConsole(int row){
        for (int i = 1; i < row; i++){
            System.out.println("\b");
        }
    }

    /* Check if input letter is in word
       if letter is in word bool erreur = false and return hiddenword with letter instead dot
       else bool erreur = true and return hiddenWord with dot
       @Return hiddenWord
     */

    public static StringBuilder inWord(char letter, String word, StringBuilder hiddenWord){
        for (int i = 0; i < word.length(); i++){
            // Check every letter in word
            if (letter == word.charAt(i)){
                error = false;
                int nb = 0;
                do {
                    // Duplicate letter check
                    if(word.indexOf(letter, nb) != -1){
                        int pos = word.indexOf(letter, nb);
                        hiddenWord.replace(pos, pos+1, String.valueOf(letter));
                    }
                    nb++;
                }while(nb < word.length());
                return hiddenWord;
            }
        }
        error = true;
        return hiddenWord;
    }
}
