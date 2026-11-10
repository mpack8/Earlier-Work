import java.util.Random;
import java.util.Scanner;
/* Class: CSE 1322L
Section: J04
Term: Full Term
Instructor: Gar Lock
Name: Michael Pack
Assignment#: 4*/
abstract class Language{
    public abstract String make_guess();
    public abstract String too_low();
    public abstract String too_high();
    public abstract String correct();
}
class English extends Language{
@Override
public String make_guess(){
    return "Guess a number";
}
@Override
    public String too_low(){
    return "Too Low";
}
@Override
    public String too_high(){
    return "Too High";
}
    @Override
    public String correct(){
        return "Correct";
    }
}
class Spanish extends Language{
    @Override
    public String make_guess(){
        return "Adivina un numero";
    }
    @Override
    public String too_low(){
        return "Demasiado bajo";
    }
    @Override
    public String too_high(){
        return "Demasiado alto";
    }
    @Override
    public String correct(){
        return "Correcto";
    }
}
class French extends Language{
    @Override
    public String make_guess(){
        return "Devinez un nombre";
    }
    @Override
    public String too_low(){
        return "Trop bas";
    }
    @Override
    public String too_high(){
        return "Trop haut";
    }
    @Override
    public String correct(){
        return "Correct";
    }
}
class SimplifiedChinese extends Language{
    @Override
    public String make_guess(){
        return "猜一个数字";
    }
    @Override
    public String too_low(){
        return "太低";
    }
    @Override
    public String too_high(){
        return "太高";
    }
    @Override
    public String correct(){
        return "正确";
    }
}
class Guess_Number{
    public void play_game() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        Scanner scan = new Scanner(System.in);
        System.out.println("""
                Choose your language
                1. English
                2. Español
                3. Français
                4. 简体中文""");
        int myLang = scan.nextInt();
        Language language;
        switch (myLang) {
            case 1:
                language = new English();
                break;
            case 2:
                language = new Spanish();
                break;
            case 3:
                language = new French();
                break;
            case 4:
                language = new SimplifiedChinese();
                break;
            default:
                System.out.println("invalid choice. Defaulting to English.");
                language = new English();
        }
        int userGuess = 0;
        while (userGuess != randomNumber) {
            System.out.println(language.make_guess());
            userGuess = scan.nextInt();
            if (randomNumber > userGuess) {
                System.out.println(language.too_low());
            } else if (randomNumber < userGuess) {
                System.out.println(language.too_high());
            }
        }
         System.out.println(language.correct());
    }
}

public class Assignment4 {
    public static void main(String[] args) {
        Guess_Number guess = new Guess_Number();
        guess.play_game();


    }
}