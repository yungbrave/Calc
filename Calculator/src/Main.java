import java.util.Objects;

public class Main {
    public static void main(String[] args) throws NoNegativeInArabic, InvalidOperandFormat, WrongNumbers, NotUpToTheTask, NotMathOperation, NoZeroInArabic {
        System.out.println(calc("X / III"));
    }
    public static String calc(String input) throws InvalidOperandFormat, NotMathOperation, WrongNumbers, NoNegativeInArabic, NotUpToTheTask, NoZeroInArabic {
        String term1 = "";
        String term2 = "";
        String[] arabic = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String result;
        String[] newString = input.split(" ");
        if (newString.length < 3 || (!Objects.equals(newString[1], "+") && !Objects.equals(newString[1], "-") && !Objects.equals(newString[1], "*") && !Objects.equals(newString[1], "/"))) {
            throw new NotMathOperation("т.к. не является математической операцией");
        }
        else if (newString.length > 3) {
            throw new NotUpToTheTask("т.к. формат не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        // поиск арабских чисел
        for (String value : arabic) {
            if (value.equals(newString[0])) {
                term1 = value;
            }
        }
        for (String s : arabic) {
            if (s.equals(newString[2])) {
                term2 = s;
            }
        }

        if (term1.equals("") && term2.equals("")) {
            if (((Integer.parseInt(newString[0]) < 11) && (Integer.parseInt(newString[0])>0)) && ((Integer.parseInt(newString[2]) < 11) && (Integer.parseInt(newString[2])>0))) {
                switch (newString[1]) {
                    case "+":
                        result = String.valueOf(Integer.parseInt(newString[0]) + Integer.parseInt(newString[2]));
                        break;
                    case "-":
                        result = String.valueOf(Integer.parseInt(newString[0]) - Integer.parseInt(newString[2]));
                        break;
                    case "*":
                        result = String.valueOf(Integer.parseInt(newString[0]) * Integer.parseInt(newString[2]));
                        break;
                    case "/":
                        result = String.valueOf(Integer.parseInt(newString[0]) / Integer.parseInt(newString[2]));
                        break;
                    default: throw new NotMathOperation ("т.к. формат не удовлетворяет заданию, неверный оператор");
                }
                return result;
            } else {
                throw new WrongNumbers("Введены числа, выходящие за пределы от 1 до 10");
            }
        } else if (term1.equals("") || term2.equals("")) {
            throw new InvalidOperandFormat ("т.к. используются одноверменно разные системы счисления");
        } else {
            switch (newString[1]) {
                case "+":
                    result = ArabicToRoman.getArabic(ArabicToRoman.getRoman(newString[0]) + ArabicToRoman.getRoman(newString[2]));
                    break;
                case "-":
                    if ((ArabicToRoman.getRoman(newString[0]) - ArabicToRoman.getRoman(newString[2]) < 1)) {
                        throw new NoZeroInArabic ("т.к. в римской системе нет нуля");
                    } else if ((ArabicToRoman.getRoman(newString[0]) - ArabicToRoman.getRoman(newString[2]) < 0)) {
                        throw new NoNegativeInArabic("т.к. в римской системе нет отрицательных чисел");
                    } else {
                        result = ArabicToRoman.getArabic(ArabicToRoman.getRoman(newString[0]) - ArabicToRoman.getRoman(newString[2]));
                    }
                    break;
                case "*":
                    result = ArabicToRoman.getArabic(ArabicToRoman.getRoman(newString[0]) * ArabicToRoman.getRoman(newString[2]));
                    break;
                case "/":
                    result = ArabicToRoman.getArabic(ArabicToRoman.getRoman(newString[0]) / ArabicToRoman.getRoman(newString[2]));
                    break;
                default: throw new NotMathOperation ("т.к. формат не удовлетворяет заданию, неверный оператор");
            }
            return result;
        }
        //добавить return
    }
}
//Exceptions
class NoZeroInArabic extends Exception {
    NoZeroInArabic(String description) {
        super(description);
    }
}
class NotUpToTheTask extends Exception{
    NotUpToTheTask (String description) {
        super(description);
    }
}
class InvalidOperandFormat extends Exception{
    public InvalidOperandFormat (String description) {
        super(description);
    }
}
class NoNegativeInArabic extends Exception{
    NoNegativeInArabic (String description) {
        super(description);
    }
}
class WrongNumbers extends Exception{
    public WrongNumbers (String description) {
        super(description);
    }
}
class NotMathOperation extends Exception{
    NotMathOperation (String description) {
        super(description);
    }
}
//Методы перевода чисел из римской в арабскую СИ и обратно
class ArabicToRoman {
    public static String[] arabic = new String[] {
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
    public static int getRoman (String arabic1) {
        int term = 0;
        for (int i = 0; i < arabic.length; i++) {
            if (arabic1.equals(arabic[i])) {
                term = i+1;
            }

        }
        return term;
    }
    public static String getArabic (int result) {
        String resultArabic = null;
        for (int i = 0; i < arabic.length; i++) {
            if (result == i+1) {
                resultArabic = arabic [i];
            }
        }
        return resultArabic;
    }
}