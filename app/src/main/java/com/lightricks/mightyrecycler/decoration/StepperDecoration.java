package com.lightricks.mightyrecycler.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Item Decoration drawing a stepper (line with dots) along the left edge of the list
 */
public class StepperDecoration extends RecyclerView.ItemDecoration {
    private Paint sidelinePaint;
    private int decorationWidth, circleRadius;

    StepperDecoration(int width, int color) {
        decorationWidth = width;

        sidelinePaint = new Paint();
        sidelinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        sidelinePaint.setStrokeWidth(width / 8);
        sidelinePaint.setAntiAlias(true);
        sidelinePaint.setColor(color);

        circleRadius = width / 4;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.left = decorationWidth;
        outRect.right = decorationWidth;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent,
                       @NonNull RecyclerView.State state) {
        for (int i=0; i<parent.getChildCount(); ++i) {
            View view = parent.getChildAt(i);

            final boolean isFirst = i == 0;
            final boolean isLast = i == parent.getChildCount() - 1;

            final int left = parent.getLeft();
            final int top = view.getTop();
            final int bottom = view.getBottom();
            final int middle = top + (bottom - top) / 2;

            final float startX;
            final float stopX;
            startX = stopX = left + decorationWidth / 2;

            final float startY;
            final float stopY;
            if (isFirst) {
                startY = middle;
                stopY = bottom;
                canvas.drawCircle(startX, startY, circleRadius, sidelinePaint);
            } else if (isLast) {
                startY = top;
                stopY = middle;
                canvas.drawCircle(stopX, stopY, circleRadius, sidelinePaint);
            } else {
                startY = top;
                stopY = bottom;
                canvas.drawCircle(startX, middle, circleRadius, sidelinePaint);
            }

            canvas.drawLine(startX, startY, stopX, stopY, sidelinePaint);
        }
    }
}
