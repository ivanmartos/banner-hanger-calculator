package com.martos.bannerhangercalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    @BindView(R.id.bannerWidthEditText) EditText widthEditText;
    @BindView(R.id.paddingEditText) EditText paddingEditText;
    @BindView(R.id.resultLayout) LinearLayout resultLayout;

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
    }


    @OnTextChanged({R.id.paddingEditText, R.id.bannerWidthEditText})
    public void onButtonClick() {
        String paddingText = paddingEditText.getText().toString();
        String widthText = widthEditText.getText().toString();

        if (!paddingText.isEmpty() && !widthText.isEmpty()) {
            Integer padding = Integer.valueOf(paddingText);
            Integer width = Integer.valueOf(widthText);

            List<CalculatedPadding> calculatedPaddings = calculator.calculatedPadding(width, padding);

            resultLayout.removeAllViewsInLayout();
            for (CalculatedPadding calculatedPadding : calculatedPaddings) {
                resultLayout.addView(createResultTextView(calculatedPadding));
            }
        }
    }

    private TextView createResultTextView(CalculatedPadding calculatedPadding) {
        TextView text = new TextView(getContext());
        String resultHtml = String.format("Pocet kusov <b>%s ks</b> Odsadenie <b>%s mm</b>",
                calculatedPadding.getPieces(),
                calculatedPadding.getPadding());
        text.setText(Html.fromHtml(resultHtml));
        return text;
    }
}