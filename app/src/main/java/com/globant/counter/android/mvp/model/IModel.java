package com.globant.counter.android.mvp.model;

/**
 * Created by equattrocchio on 30/06/17.
 */

public interface IModel {
    boolean isExpresionValid(String expresion);

    String clearLast();

    boolean isExpresionNull();

    void clearAll();

    String getResult();
}
