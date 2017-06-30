package com.globant.counter.android.mvp.view;

/**
 * Created by equattrocchio on 30/06/17.
 */

public interface IView {
    void updateUI(String expresion);

    void clearAll();

    void showExpresionError();
}
