package com.lightricks.mightyrecycler.home;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SidelineDecoration extends RecyclerView.ItemDecoration {
    private Paint sidelinePaint;
    private Path sidelinePath;
    private int decorationWidth;

    SidelineDecoration(int width, int color) {
        decorationWidth = width;

        sidelinePaint = new Paint();
        sidelinePaint.setStyle(Paint.Style.STROKE);
        sidelinePaint.setStrokeWidth(width);
        sidelinePaint.setStrokeJoin(Paint.Join.ROUND);
        sidelinePaint.setStrokeCap(Paint.Cap.ROUND);
        sidelinePaint.setAntiAlias(true);
        sidelinePaint.setColor(color);
        float[] intervals = new float[]{1, width * 4 / 3};
        sidelinePaint.setPathEffect(new DashPathEffect(intervals, 0));

        sidelinePath = new Path();
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.left = decorationWidth;
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

            final int offset = decorationWidth / 2;

            final float startY;
            final float stopY;
            if (isFirst) {
                startY = middle;
                stopY = bottom;
            } else if (isLast) {
                startY = top + offset;
                stopY = middle;
            } else {
                startY = top + offset;
                stopY = bottom;
            }

            sidelinePath.reset();
            sidelinePath.moveTo(startX, startY);
            sidelinePath.lineTo(stopX, stopY);
            canvas.drawPath(sidelinePath, sidelinePaint);
        }
    }
}
