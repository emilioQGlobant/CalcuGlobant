package com.globant.counter.android.mvp.presenter;

import static com.globant.counter.android.mvp.view.CalculatorView.OnButtonClickedEvent;
import static com.globant.counter.android.mvp.view.CalculatorView.OnClearLastSymbolEvent;
import com.globant.counter.android.mvp.model.IModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.mvp.view.IView;
import com.squareup.otto.Subscribe;

/**
 * Created by equattrocchio on 30/06/17.
 */


public class CalculatorPresenter implements IPresenter{
    private IModel mModel;
    private IView mView;

    public CalculatorPresenter(IModel model, IView view){
        mModel = model;
        mView = view;
    }

    @Subscribe
    public void validateExpresion(OnButtonClickedEvent event){

        if (mModel.isExpresionValid(event.expresion)){
            mView.updateUI(event.expresion);
        }
    }

    /**
     * Estos dos(clearLastSymbol y clearAll) podrían simplemente llamar al método del model y que el model haga los checks
     */
    @Subscribe
    public void clearLastSymbol(OnClearLastSymbolEvent event){

        if (!mModel.isExpresionNull()){

            mView.updateUI(mModel.clearLast());
        }
    }

    @Subscribe
    public void clearAll(CalculatorView.OnClearAllEvent event){

        if(!mModel.isExpresionNull()){
            mModel.clearAll();
            mView.clearAll();
        }
    }

    @Subscribe
    public void resolve(CalculatorView.OnREsolveEvent event){

        if(mModel.isExpresionValid(event.expresion)){
            mView.updateUI(mModel.getResult());
        } else{
            mView.showExpresionError();
        }
    }

}