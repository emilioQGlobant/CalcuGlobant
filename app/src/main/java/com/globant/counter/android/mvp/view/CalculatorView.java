package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.widget.Toast;

import com.squareup.otto.Bus;

import butterknife.ButterKnife;

/**
 * Created by equattrocchio on 30/06/17.
 */

public class CalculatorView extends ActivityView implements IView {

    private final Bus bus;

    public CalculatorView(Activity activity, Bus bus) {
        super(activity);
        this.bus = bus;
        ButterKnife.bind(this, activity);
    }

    @Override
    public void updateUI(String expresion) {

    }

    @Override
    public void clearAll() {

    }

    @Override
    public void showExpresionError() {
        Toast.makeText(getContext(),"Invalid Expresion",Toast.LENGTH_SHORT).show();
    }

    public static class OnClearLastSymbolEvent{
    }
    public static class OnButtonClickedEvent{
        public String expresion;

    }
    public static class OnClearAllEvent{

    }
    public static class OnREsolveEvent{
        public String expresion;
    }

}
