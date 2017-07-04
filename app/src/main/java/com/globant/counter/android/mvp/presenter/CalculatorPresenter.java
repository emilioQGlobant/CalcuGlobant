package com.globant.counter.android.mvp.presenter;

import static com.globant.counter.android.mvp.view.CalculatorView.OnClearLastSymbolEvent;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.squareup.otto.Subscribe;

/**
 * Created by equattrocchio on 30/06/17.
 */


public class CalculatorPresenter {
    private CalculatorModel mModel;
    private CalculatorView mView;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        mModel = model;
        mView = view;
    }

    /**
     * Estos dos(clearLastSymbol y clearAll) podrían simplemente llamar al método del model y que el model haga los checks
     */
    @Subscribe
    public void clearLastSymbol(OnClearLastSymbolEvent event) {

        /**if (!mModel.isExpresionNull()){
         mView.updateUI(mModel.getExpresion());
         }*/
    }

    @Subscribe
    public void clearAll(CalculatorView.OnClearAllEvent event) {
        mModel.reset();
        mView.clearAll();
    }

    @Subscribe
    public void resolve(CalculatorView.OnButtonCalculateEvent event) {
        mView.updateUIResult(mModel.resolveExpresion(event.expresion));
        mView.clearAll();
    }

    @Subscribe
    public void onValidateExpresionEvent(CalculatorView.validateExpresionEvent event) {
        if (mModel.isExpresionValid(event.expresion)) {
        } else {
            mView.clearLast();
        }
    }
}