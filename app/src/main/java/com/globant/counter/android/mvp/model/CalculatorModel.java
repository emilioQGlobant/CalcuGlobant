package com.globant.counter.android.mvp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by equattrocchio on 30/06/17.
 */

public class CalculatorModel {

    private List<String> expresionList;

    private boolean lastWasOperator;
    /**
     * @auxOperand concatena los números hasta que venga un operador(+,-,*,/,=)
     */
    private String auxOperand;

    public CalculatorModel() {
        expresionList = new ArrayList<>();
        auxOperand = "";
        lastWasOperator = true;
    }

    public void clearAll() {
        expresionList.clear();
    }

    public boolean isExpresionValid(String expresion) {
        return validateExpresion(expresion);
    }

    private boolean validateExpresion(String expresion) {//TODO: implementar validador
        if (expresion != null && expresion.isEmpty()) {
            return false;
        }
        String lastElement = expresion.substring(expresion.length() - 1);
        if (isNumeric(lastElement)) {
            lastWasOperator = false;
            auxOperand += lastElement; // concateno el número que se está escribiendo
            return true;
        } else {
            if (!lastWasOperator) {
                if (isAnOperator(lastElement)) { //como vino un operador se terminó de ingresar el numero
                    if (expresionList.isEmpty() && auxOperand.length() < 1) {
                        return false;
                    }
                    expresionList.add(auxOperand);
                    expresionList.add(lastElement);
                    auxOperand = "";
                    lastWasOperator = true;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private boolean isAnOperator(String lastElement) {

        switch (lastElement) {
            case "/":
                return true;
            case "x":
                return true;
            case "+":
                return true;
            case "-":
                return true;
            default:
                return false;
        }
    }

    public void clearLast() {
        expresionList.remove(expresionList.size() - 1);
    }

    public String resolveExpresion(String expresion) {//TODO: resolver expresion
        if (expresion.isEmpty()) {
            return "Not Operation";
        }

        addLastNumber(expresion);

        if (!isExpresionValid(expresion)) {
            return "WRONG EXPRESION";
        } else {
            return calculate(expresion);
        }
    }

    private void addLastNumber(String expresion) {

        String auxLast = expresion.substring(expresion.length() - 1);
        String result = "";
        int i = 1;
        while (isNumeric(auxLast)) {
            auxLast = Character.toString(expresion.charAt(expresion.length() - i));
            if (isAnOperator(auxLast)) {
                break;
            }
            result = auxLast + result;
            i++;
        }
        expresionList.add(result);
        auxLast = "";
    }

    private String calculate(String expresion) {
        if (expresionList.size() < 3) {
            return "Not Operation";
        }

        String result = "";
        String op1 = expresionList.get(0);
        String operand = expresionList.get(1);
        String op2 = expresionList.get(2);
        for (int i = 3; i <= expresionList.size(); i += 2) {
            op1 = simpleCalculation(op1, operand, op2);
            if (i != expresionList.size()) {
                operand = expresionList.get(i);
                op2 = expresionList.get(i + 1);
            }
        }
        expresionList.clear();
        return op1;
    }

    private String simpleCalculation(String op1, String operand, String op2) {

        switch (operand) {
            case "+":
                return (Integer.parseInt(op1) + Integer.parseInt(op2)) + "";
            case "-":
                return (Integer.parseInt(op1) - Integer.parseInt(op2)) + "";
            case "x":
                return (Integer.parseInt(op1) * Integer.parseInt(op2)) + "";
            case "/":
                return (Integer.parseInt(op1) / Integer.parseInt(op2)) + "";
            default:
                return "";
        }
    }

    public void reset() {
        expresionList.clear();
        lastWasOperator = true;
        auxOperand = "";
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
