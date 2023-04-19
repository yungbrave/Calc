import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoNegativeInRomanExceprion, InvalidOperanFormatException, WrongNumbersException, NotUpToTheTaskException, NotMathOperationException, NoZeroInRomanException {
        System.out.println(calc());
    }
    public static String calc() throws InvalidOperanFormatException, NotMathOperationException, WrongNumbersException, NoNegativeInRomanExceprion, NotUpToTheTaskException, NoZeroInRomanException {
        String term1 = "";
        String term2 = "";
        String[] romanNumeric = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String result;
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] splitedExpression = input.split(" ");
        if (splitedExpression.length < 3 || (!Objects.equals(splitedExpression[1], "+") && !Objects.equals(splitedExpression[1], "-") && !Objects.equals(splitedExpression[1], "*") && !Objects.equals(splitedExpression[1], "/")))
            throw new NotMathOperationException("т.к. не является математической операцией");
        else if (splitedExpression.length > 3) {
            throw new NotUpToTheTaskException("т.к. формат не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        // поиск римских чисел
        for (String value : romanNumeric) {
            if (value.equals(splitedExpression[0])) {
                term1 = value;
            }
        }
        for (String value : romanNumeric) {
            if (value.equals(splitedExpression[2])) {
                term2 = value;
            }
        }

        if (term1.equals("") && term2.equals("")) {
            if (((Integer.parseInt(splitedExpression[0]) < 11) && (Integer.parseInt(splitedExpression[0])>0)) && ((Integer.parseInt(splitedExpression[2]) < 11) && (Integer.parseInt(splitedExpression[2])>0))) {
                switch (splitedExpression[1]) {
                    case "+":
                        result = String.valueOf(Integer.parseInt(splitedExpression[0]) + Integer.parseInt(splitedExpression[2]));
                        break;
                    case "-":
                        result = String.valueOf(Integer.parseInt(splitedExpression[0]) - Integer.parseInt(splitedExpression[2]));
                        break;
                    case "*":
                        result = String.valueOf(Integer.parseInt(splitedExpression[0]) * Integer.parseInt(splitedExpression[2]));
                        break;
                    case "/":
                        result = String.valueOf(Integer.parseInt(splitedExpression[0]) / Integer.parseInt(splitedExpression[2]));
                        break;
                    default: throw new NotMathOperationException("т.к. формат не удовлетворяет заданию, неверный оператор");
                }
                return result;
            } else {
                throw new WrongNumbersException("Введены числа, выходящие за пределы от 1 до 10");
            }
        } else if (term1.equals("") || term2.equals("")) {
            if (ChangeNumericSystem.isNumeric(splitedExpression[0]) || ChangeNumericSystem.isNumeric(splitedExpression[2])) {
                throw new InvalidOperanFormatException("т.к. используются одноверменно разные системы счисления");
            } else {
                throw new NotUpToTheTaskException("Некорректно введны операнды");
            }
        } else {
            switch (splitedExpression[1]) {
                case "+":
                    result = ChangeNumericSystem.getArabic(ChangeNumericSystem.getRoman(splitedExpression[0]) + ChangeNumericSystem.getRoman(splitedExpression[2]));
                    break;
                case "-":
                    if ((ChangeNumericSystem.getRoman(splitedExpression[0]) - ChangeNumericSystem.getRoman(splitedExpression[2]) < 1)) {
                        throw new NoZeroInRomanException("т.к. в римской системе нет нуля");
                    } else if ((ChangeNumericSystem.getRoman(splitedExpression[0]) - ChangeNumericSystem.getRoman(splitedExpression[2]) < 0)) {
                        throw new NoNegativeInRomanExceprion("т.к. в римской системе нет отрицательных чисел");
                    } else {
                        result = ChangeNumericSystem.getArabic(ChangeNumericSystem.getRoman(splitedExpression[0]) - ChangeNumericSystem.getRoman(splitedExpression[2]));
                    }
                    break;
                case "*":
                    result = ChangeNumericSystem.getArabic(ChangeNumericSystem.getRoman(splitedExpression[0]) * ChangeNumericSystem.getRoman(splitedExpression[2]));
                    break;
                case "/":
                    result = ChangeNumericSystem.getArabic(ChangeNumericSystem.getRoman(splitedExpression[0]) / ChangeNumericSystem.getRoman(splitedExpression[2]));
                    break;
                default: throw new NotMathOperationException("т.к. формат не удовлетворяет заданию, неверный оператор");
            }
            return result;
        }
    }
}
//Exceptions
class NoZeroInRomanException extends Exception {
    NoZeroInRomanException(String description) {
        super(description);
    }
}
class NotUpToTheTaskException extends Exception{
    NotUpToTheTaskException(String description) {
        super(description);
    }
}
class InvalidOperanFormatException extends Exception{
    public InvalidOperanFormatException(String description) {
        super(description);
    }
}
class NoNegativeInRomanExceprion extends Exception{
    NoNegativeInRomanExceprion(String description) {
        super(description);
    }
}
class WrongNumbersException extends Exception{
    public WrongNumbersException(String description) {
        super(description);
    }
}
class NotMathOperationException extends Exception{
    NotMathOperationException(String description) {
        super(description);
    }
}
//Методы перевода чисел из римской в арабскую СИ и обратно
class ChangeNumericSystem {
    public static String[] roman = new String[] {
            "I","II","III","IV","V","VI","VII","VIII","IX","X",
            "XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII","LVIII","LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV","LXV","LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV","LXXV", "LXXVI", "LXXVII","LXXVIII","LXXIX","LXXX",
            "LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC",
            "XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C"
    };
    public static int getRoman(String roman1) {
        int term = 0;
        for (int i = 0; i < roman.length; i++) {
            if (roman1.equals(roman[i])) {
                term = i+1;
            }

        }
        return term;
    }
    public static String getArabic(int result) {
        String resultRoman = null;
        for (int i = 0; i < roman.length; i++) {
            if (result == i+1) {
                resultRoman = roman[i];
            }
        }
        return resultRoman;
    }
    public static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
            } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}