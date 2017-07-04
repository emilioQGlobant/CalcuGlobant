package com.globant.counter.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.utils.BusProvider;


public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_calculator);
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this, BusProvider.getInstance()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }
}
