package com.globant.counter.android.mvp.model;

/**
 * Created by equattrocchio on 30/06/17.
 */

public class CalculatorModel implements IModel{

    private String expresion="";


    public void clearAll(){
        expresion = "";
    }

    @Override
    public boolean isExpresionValid(String expresion) {
        return false;
    }

    public String clearLast(){
        expresion = expresion.substring(0, expresion.length() - 1);
        return expresion;
    }

    @Override
    public boolean isExpresionNull() {
        return false;
    }

    //todo: tengo q ver si le paso la expresión por parámetro y devuelvo el resultado o voy almacenando
    //todo: la expresión cada vez que checkeo si es válida en el isExpresionValid
    public String getResult(){ // supongo que no se puede empezar con - para simplificar

        return expresion;
    }


}
