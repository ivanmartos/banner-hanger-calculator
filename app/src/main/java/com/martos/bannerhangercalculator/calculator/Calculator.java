package com.martos.bannerhangercalculator.calculator;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import java.util.List;

/**
 * @author Ivan Martos <ivan.martos@cleverlance.com>
 */

public class Calculator {
    private static final int LIMIT = 15;

    public List<CalculatedPadding> calculatedPadding(int width, int requestedPadding) {

        if (width < requestedPadding || width == 0 || requestedPadding == 0) {
            return ImmutableList.of();
        }

        int initialPieces = width / requestedPadding;

        Builder<CalculatedPadding> builder = ImmutableList.builder();

        for (int pieces = initialPieces - LIMIT; pieces < (initialPieces + LIMIT); pieces++) {
            double padding = (double) width / (double) pieces;

            if (padding % 1 == 0 && padding < width && padding > 0) {
                CalculatedPadding calculated = new CalculatedPadding((int) padding, pieces - 1);
                builder.add(calculated);
            }
        }

        return builder.build();
    }

}
