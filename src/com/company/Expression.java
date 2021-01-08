package com.company;

import java.util.Scanner;



/**
 * класс Expression получает вводимые данные от пользователя в переменную expression,
 * далее убирает лишние пробелы,
 * в методе exitManual отслеживает нажатие "q" или "Q" для выхода из программы с кодом  "0",
 * в методе checkLength проверка длины выражения, с завершением программы при длине меньше 3 символов с кодом ошибки "11"
 * и более 9 символов с кодом ошибки "99",
 * проверка первого символа выражения на арабский символ или римсский символ методом firstSimbol
 */

public class Expression {
    String result;
    String expression;
    Boolean firstSymbolArabic = false;
    Boolean firstSymbolRomain = false;
    int firstArabicNumeral;
    int secondArabicNumeral;
    String action;
    String[] actions = new String[]{"\\+", "-", "\\*", "/"};

    Expression() throws ActionPerform.MyException {
        Scanner scanner = new Scanner(System.in);
        this.expression = scanner.nextLine();
        this.expression.replaceAll(" ", "");
        exitManual(this.expression.toLowerCase(), "выход из калькулятора, завершение пользователем.", 0);
        checkLentgh(this.expression);
        firstSymbol(this.expression);
        if (this.firstSymbolArabic){
            calculate();
        }
        if (this.firstSymbolRomain){
            // вызываем метод для обработки выражения с римскими числами
//            for (String action : actions){
//                if (action == null){
//                    System.out.println("действие не обнаружено : ");
//                    System.exit(3);
//                }
//                String[] romanNumerals = this.expression.split(action);
//
//
//            }
            RomanNumerals romanNumeral = new RomanNumerals();
            romanNumeral.romanNumerals(this.expression);
            this.result = romanNumeral.result;

        }
    }

    private void calculate() throws ActionPerform.MyException {
        // проверяем выражение на валидность два числа(a, b) и одно из действий ( + - * / ) пример -> a+b;
        expressionValid(this.expression);

        // вызываем метод для обработки выражения с арабскими числами
        ActionPerform calculate = new ActionPerform();

        if (this.action == null){
            System.out.println("действие не указано : ");
            System.exit(3);
        }
        this.result = calculate.action(this.firstArabicNumeral, this.secondArabicNumeral, this.action).toString();

    }

    private void expressionValid(String expression) {

        // если выражение в три символа
        if (expression.length() == 3) {
            for (String action : actions) {

                try {
                    String[] expressionSplit = expression.split(action);
                    if (expressionSplit.length == 2) {
                        definedDigitsAndAction(
                                Integer.parseInt(expressionSplit[0]),
                                Integer.parseInt(expressionSplit[1]),
                                action);
                    }
                }catch(Exception exception){
                    System.out.println("Действие указано не верно : " + exception);
                }
            }
        }else{
            System.out.println("Выражение составлено не верно, числа от 1 до 9, попробуйте еще раз. ");
        }
    }

    private void definedDigitsAndAction(int firstDigit, int secondDigit, String action) {
        this.firstArabicNumeral = firstDigit;
        this.secondArabicNumeral = secondDigit;
        this.action = action;
    }

    private Boolean firstSymbol(String expression) {

        String firstSymbol = expression.split("")[0];
        try{
            Integer.parseInt(firstSymbol);
            return this.firstSymbolArabic = true;
        }catch(NumberFormatException e){
            return firstSymbolRomain = true;
        }
    }

    private void exitManual(String expression, String comment, int code) {
        // выход из калькулятора при нажатии "q" или "Q"
        if (expression.equals("q")) {
            System.out.println(comment);
            System.exit(code);
        }
    }

    private void checkLentgh(String expression) {
        // 12
        if (expression.length() < 3){
            System.out.println("Выражение короче ожидаемого.");
            System.exit(11);
        }
        // 1234567890
        if (expression.length()>9){
            System.out.println("Выражение длинее ожидаемого.");
            System.exit(99);
        }
    }

}
