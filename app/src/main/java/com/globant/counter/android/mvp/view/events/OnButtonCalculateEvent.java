package com.globant.counter.android.mvp.view.events;

/**
 * Created by equattrocchio on 04/07/17.
 */

public class OnButtonCalculateEvent {
    public String expresion;

    public OnButtonCalculateEvent(String expresion) {
        this.expresion = expresion;
    }
}