package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.mvp.view.events.validateExpresionEvent;
import com.squareup.otto.Subscribe;
import com.globant.counter.android.mvp.view.events.OnButtonCalculateEvent;
/**
 * Created by equattrocchio on 30/06/17.
 */


public class CalculatorPresenter {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    @Subscribe
    public void resolve(OnButtonCalculateEvent event) {
        view.updateUIResult(model.resolveExpresion(event.expresion));
        view.clearAll();
    }

    @Subscribe
    public void onValidateExpresionEvent(validateExpresionEvent event) {
        if (model.isExpresionValid(event.expresion)) {
        } else {
            view.clearLast();
        }
    }
}