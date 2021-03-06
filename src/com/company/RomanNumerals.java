package com.company;

/**
 * Класс предназначен для работы с римскими выражениями,
 * для перевода римских чисел в арабские и обратно для вывода ответа в римском исчислении
 * метод romanNumeralToArabic() римские числа в арабские,
 * метод arabicNumeralToRomanNumeral() арабские числа в римские,
 * в свойствах класса RomanNumerals есть массив actions с содержанием возможных действий в выражении,
 * и массив romanNumerals с содержанием римских чисел,
 * в методе romanNumerals() передаем выражение, ищем действие в выражении делим на две части проверяем их длину
 * и ищем их значения в соответствии с арабскими числами для совершения операции, затем ответ переводим в римское число,
 * метод expressionWrong() вызываем при обнаружения ошибки "Выражение не верно составлено!"
 */

public class RomanNumerals {
    String action;
    String result;

    public void romanNumerals(String expression) throws ActionPerform.MyException {
        String[] actions = new String[]{"\\+", "-", "\\*", "/"};
        String[] romanNumerals = new String[]{
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"
        };
        int[] firstSecondNumbers = new int[]{0, 0};
        int index = 0;
        for (String action : actions) {
            String[] expressionSplits = expression.split(action);
            if (expressionSplits.length > 2) expressionWrong();
            if (expressionSplits.length < 2) continue;
            this.action = action;
            for (String value : expressionSplits) {
                for (String romanNumeral : romanNumerals) {
                    if (romanNumeral.equals(value)) {
                        firstSecondNumbers[index] = romanNumeralToArabic(romanNumeral);
                        index = 1;
                    }
                }
            }
        }
        if (firstSecondNumbers[0] == 0 || firstSecondNumbers[1] == 0) {
            expressionWrong();
        }
        ActionPerform expressionResult = new ActionPerform();
        this.result = arabicNumeralToRomanNumeral(expressionResult.action(firstSecondNumbers[0], firstSecondNumbers[1], this.action));
    }

    private static void expressionWrong() {
        System.out.println("Выражение не верно составлено!");
        System.exit(1234232);
    }

    public static int romanNumeralToArabic(String romanNumeral) {
        if (romanNumeral.equals("I")) {
            return 1;
        }
        if (romanNumeral.equals("II")) {
            return 2;
        }
        if (romanNumeral.equals("III")) {
            return 3;
        }
        if (romanNumeral.equals("IV")) {
            return 4;
        }
        if (romanNumeral.equals("V")) {
            return 5;
        }
        if (romanNumeral.equals("VI")) {
            return 6;
        }
        if (romanNumeral.equals("VII")) {
            return 7;
        }
        if (romanNumeral.equals("VIII")) {
            return 8;
        }
        if (romanNumeral.equals("IX")) {
            return 9;
        }
        if (romanNumeral.equals("X")) {
            return 10;
        }

        return 0;
    }

    public static String arabicNumeralToRomanNumeral(int arabicNumeral) {
        if (arabicNumeral == 0) {
            return "N";
        }
        if (arabicNumeral == 1) {
            return "I";
        }
        if (arabicNumeral == 2) {
            return "II";
        }
        if (arabicNumeral == 3) {
            return "III";
        }
        if (arabicNumeral == 4) {
            return "IV";
        }
        if (arabicNumeral == 5) {
            return "V";
        }
        if (arabicNumeral == 6) {
            return "VI";
        }
        if (arabicNumeral == 7) {
            return "VII";
        }
        if (arabicNumeral == 8) {
            return "VIII";
        }
        if (arabicNumeral == 9) {
            return "IX";
        }
        if (arabicNumeral == 10) {
            return "X";
        }
        if (arabicNumeral == 11) {
            return "XI";
        }
        if (arabicNumeral == 12) {
            return "XII";
        }
        if (arabicNumeral == 13) {
            return "XIII";
        }
        if (arabicNumeral == 14) {
            return "XIV";
        }
        if (arabicNumeral == 15) {
            return "XV";
        }
        if (arabicNumeral == 16) {
            return "XVI";
        }
        if (arabicNumeral == 17) {
            return "XVII";
        }
        if (arabicNumeral == 18) {
            return "XVIII";
        }
        if (arabicNumeral == 19) {
            return "XIX";
        }
        if (arabicNumeral == 20) {
            return "XX";
        }
        if (arabicNumeral == 50) {
            return "L";
        }
        if (arabicNumeral == 60)
            return "LX";
        if (arabicNumeral == 70) return "LXX";
        if (arabicNumeral == 80) return "LXXX";
        if (arabicNumeral == 90) return "XC";

        if (arabicNumeral == 100) {
            return "C";
        }
        if (arabicNumeral > 20) {
            if (arabicNumeral < 50) {
                return toParseSymbol(arabicNumeral);
            }
            return "L" + toParseSymbol(arabicNumeral);
        }
        return "Такого значения нет в римских цифрах";
    }

    private static String toParseSymbol(int arabicNumeral) {
        String[] numbers = Integer.toString(arabicNumeral).split("");
        String firstSymbol = "";

        if (Integer.parseInt(numbers[0]) < 4) {
            for (int i = 0; i < Integer.parseInt(numbers[0]); i++) {
                firstSymbol = "X";
            }
        }

        if (Integer.parseInt(numbers[0]) == 4) {
            firstSymbol = "XL";
        }

        if (Integer.parseInt(numbers[0]) == 6) {
            firstSymbol = "X";
        }

        if (Integer.parseInt(numbers[0]) == 7) {
            firstSymbol = "XX";
        }

        if (Integer.parseInt(numbers[0]) == 8) {
            firstSymbol = "XXX";
        }

        if (numbers[1].equals("0")) {
            return firstSymbol;
        }

        String secondSymbol = arabicNumeralToRomanNumeral(Integer.parseInt(numbers[1]));
        return firstSymbol + secondSymbol;
    }

}
