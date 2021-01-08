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
    // общий результат выражения
    String result;

    // выражеие от пользователя
    String expression;

    // первый символ арабское число
    Boolean firstSymbolRomain = false;

    // первый символ римское число
    Boolean firstSymbolArabic = false;

    // первое число римского выражения эквивалент римского числа в исчислении арабских чисел
    int firstArabicNumeral;

    // второе число римского выражения эквивалент римского числа в исчислении арабских чисел
    int secondArabicNumeral;

    // действие в выражении
    String action;

    // набор действий допустимых в выражении
    String[] actions = new String[]{"\\+", "-", "\\*", "/"};

    /** конструктор принимает выражение от пользователя, убирает пробелы,
     * возможный выход из приложения нажатием "Q" или "q",  метод exitManual()
     * проверка длины выражения checkLentgh(),
     * проверка первого символа выражения, для понимания системы исчесления арабских или римских чисел  firstSymbol().
     * Вычисление "арабского" выражения  calculate(),
     * Вычисление "римского" выражения создаем обьект типа  RomanNumerals, передаем возможно "римское" выражение
     * в метод romanNumerals(), в методе expressionValid разделяем выражение по "действию" и находим первое и второе числа,
     * а также оборачиваем в try catch если ожидаемые числа не являются арабскими числами, с информированием пользователя,
     * методом definedDigitsAndAction() обновляем состояние первого и второго чисел, а также действия между ними

     *
     * @throws ActionPerform.MyException
     */
    Expression() throws ActionPerform.MyException {
        // конструктор принимает выражение от пользователя,
        Scanner scanner = new Scanner(System.in);
        this.expression = scanner.nextLine();

        //  убираеm пробелы
        this.expression.replaceAll(" ", "");

        // возможный выход из приложения нажатием "Q" или "q",  метод exitManual()
        exitManual(this.expression.toLowerCase(), "выход из калькулятора, завершение пользователем.", 0);

        // проверка длины выражения checkLentgh()
        checkLentgh(this.expression);

        // проверка первого символа выражения, для понимания системы исчесления арабских или римских чисел  firstSymbol().
        firstSymbol(this.expression);

        // если похоже на арабские числа
        if (this.firstSymbolArabic){

            // вычисление "арабского" выражения  calculate()
            calculate();
        }

        // возможно первый символ это римский символ
        if (this.firstSymbolRomain){
            // создаем класс и вызываем метод для обработки выражения с римскими числами
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

            // ищем наше действие
            for (String action : actions) {

                // в метод romanNumerals(), в методе expressionValid разделяем выражение по "действию"
                // и находим первое и второе числа,
                // а также оборачиваем в try catch если ожидаемые числа не являются арабскими числами,
                // с информированием пользователя,
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

    // обновляем данные
    private void definedDigitsAndAction(int firstDigit, int secondDigit, String action) {
        this.firstArabicNumeral = firstDigit;
        this.secondArabicNumeral = secondDigit;
        this.action = action;
    }

    // определяем первый символ выражения и его принадлежность к возможно арабским или римским числам
    private Boolean firstSymbol(String expression) {

        String firstSymbol = expression.split("")[0];
        try{
            Integer.parseInt(firstSymbol);
            return this.firstSymbolArabic = true;
        }catch(NumberFormatException e){
            return firstSymbolRomain = true;
        }
    }

    // если вместо выражения ввели "Q" или "q" то выходим из приложения
    private void exitManual(String expression, String comment, int code) {
        // выход из калькулятора при нажатии "q" или "Q"
        if (expression.equals("q")) {
            System.out.println(comment);
            System.exit(code);
        }
    }

    // проверяем длину выражения отсекаем не нужные варианты
    private void checkLentgh(String expression) {
        // 12 только арабские числа
        if (expression.length() < 3){
            System.out.println("Выражение короче ожидаемого.");
            System.exit(11);
        }
        // 1234567890 римское выражение
        if (expression.length()>9){
            System.out.println("Выражение длинее ожидаемого.");
            System.exit(99);
        }
    }

}
