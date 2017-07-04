package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import com.globant.counter.android.R;
import com.squareup.otto.Bus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by equattrocchio on 30/06/17.
 */

public class CalculatorView extends ActivityView {

    private final Bus bus;

    @BindView(R.id.editTextExpresion)
    EditText etDisplayText;

    @BindView(R.id.textViewResult)
    TextView tvResult;

    public CalculatorView(Activity activity, Bus bus) {
        super(activity);
        this.bus = bus;

        ButterKnife.bind(this, activity);
        etDisplayText.setText("");
    }

    @OnTextChanged(value = R.id.editTextExpresion,
            callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterExpresionInserted(Editable editable) {
        if (editable.length() > 0) {
            bus.post(new validateExpresionEvent(editable.toString()));
        }
    }

    public void clearLast() {
        String auxText = etDisplayText.getText().subSequence(0, etDisplayText.length() - 1).toString();
        etDisplayText.setText(auxText);
        etDisplayText.setSelection(etDisplayText.getText().length());
    }

    public void clearAll() {
        etDisplayText.setText("");
    }

    @OnClick(R.id.btnCalculate)
    public void onButtonCalculatePressed() {
        bus.post(new OnButtonCalculateEvent(etDisplayText.getText().toString()));
    }

    public void updateUIResult(String expresion) {
        tvResult.setText(expresion);
    }

    /**
     * Clases to be sent when events occur
     */

    public static class validateExpresionEvent {
        public String expresion;

        public validateExpresionEvent(String expresion) {
            this.expresion = expresion;
        }
    }

    public static class OnButtonCalculateEvent {
        public String expresion;

        public OnButtonCalculateEvent(String expresion) {
            this.expresion = expresion;
        }
    }

    public static class OnClearLastSymbolEvent {
    }

    public static class OnClearAllEvent {
    }

}
