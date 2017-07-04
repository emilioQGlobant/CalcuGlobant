package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresenterTest {

    private CalculatorPresenter presenter;
    private CalculatorModel model;
    private CalculatorView view;

    @Before
    public void setup() {
        model = new CalculatorModel();
        view = mock(CalculatorView.class);
        presenter = new CalculatorPresenter(model, view);
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    /**
     * this should test 11+7 and the result spected is 18
     */
    @Test
    public void addition_works() {
        model.reset();
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("1"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("1"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("+"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("7"));

        assertEquals(model.resolveExpresion("11+7"), "18");

        verifyNoMoreInteractions(view);
    }

    /**
     * This test should test if invalid inputs aren't allowed
     */
    @Test
    public void invalid_input_deleted() {
        model.reset();

        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("a"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("%"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("z"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("?"));

        verify(view,times(4)).clearLast();
        verifyNoMoreInteractions(view);
    }

    /**
     * This test should pass if the result of the operation 11+7/18x2 is equals to 2
     */
    @Test
    public void complex_operation(){
        String expresion ="11+7/18x2";
        model.reset();

        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("1"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("1"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("+"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("7"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("/"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("1"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("8"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("x"));
        presenter.onValidateExpresionEvent(new CalculatorView.validateExpresionEvent("2"));
        presenter.resolve(new CalculatorView.OnButtonCalculateEvent(expresion));

        verify(view).clearAll();
        verify(view).updateUIResult("2");
        verifyNoMoreInteractions(view);
    }
}