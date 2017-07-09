package com.martos.bannerhangercalculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.ImmutableList;
import com.martos.bannerhangercalculator.calculator.CalculatedPadding;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

/**
 * @author Ivan Martos <ivan.martos@cleverlance.com>
 */

public class CalculatorResultAdapter extends RecyclerView.Adapter<CalculatorResultAdapter.ResultViewHolder> {
    private List<CalculatedPadding> resultList = ImmutableList.of();

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calculated_result_holder_layout, parent, false));
    }

    public void setResultList(List<CalculatedPadding> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        CalculatedPadding calculatedPadding = resultList.get(position);
        holder.paddingTextView.setText(String.valueOf(calculatedPadding.getPadding()));
        holder.piecesTextView.setText(String.valueOf(calculatedPadding.getPieces()));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.piecesTextView) TextView piecesTextView;
        @BindView(R.id.paddingTextView) TextView paddingTextView;

        ResultViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
