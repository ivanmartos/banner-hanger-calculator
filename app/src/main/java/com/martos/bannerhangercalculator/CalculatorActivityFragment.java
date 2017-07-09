package com.martos.bannerhangercalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.martos.bannerhangercalculator.calculator.CalculatedPadding;
import com.martos.bannerhangercalculator.calculator.Calculator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CalculatorActivityFragment extends Fragment {

    private final Calculator calculator = new Calculator();
    private final CalculatorResultAdapter resultAdapter = new CalculatorResultAdapter();
    @BindView(R.id.bannerWidthEditText) EditText widthEditText;
    @BindView(R.id.paddingEditText) EditText paddingEditText;
    @BindView(R.id.resultRecyclerView) RecyclerView resultRecyclerView;

    public CalculatorActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        resultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        resultRecyclerView.setAdapter(resultAdapter);

        resultRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
    }

    @OnTextChanged({R.id.paddingEditText, R.id.bannerWidthEditText})
    public void onButtonClick() {
        String paddingText = paddingEditText.getText().toString();
        String widthText = widthEditText.getText().toString();

        if (!paddingText.isEmpty() && !widthText.isEmpty()) {
            Integer padding = Integer.valueOf(paddingText);
            Integer width = Integer.valueOf(widthText);

            List<CalculatedPadding> calculatedPaddings = calculator.calculatedPadding(width, padding);

            resultAdapter.setResultList(calculatedPaddings);
        }
    }
}