package com.company;

/**
 * класс ActionPerform принимает выражение по частям, превое и второе числа и опреция с ними
 * а также отслеживание исключения классом MyException
 */
class ActionPerform {
    public  Integer action(int firstDigit, int secondDigit, String action) throws MyException {
        // check action and return perform this action
        if (action.equals("\\+")){
            return firstDigit + secondDigit;}
        else{
            if (action.equals("-")){
                return firstDigit - secondDigit;}
            else{
                if (action.equals("\\*")){
                    return firstDigit * secondDigit;}
                else {
                    if (action.equals("/")){
                        if (secondDigit == 0){
                            System.out.println("делить на ноль нельзя : ");
                            System.exit(44);
                        }
                        return firstDigit / secondDigit;
                    }else{
                        throw new MyException();
                    }
                }
            }
        }

    }

    public static class MyException extends Throwable {
        public String MyException(String message) {
            return message;
        }
    }
}
