package com.isseiaoki.simplecropview.util;

import android.graphics.Rect;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class GestureNavigationExclusionModel {
    private enum Position {TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT}

    private final Rect mExclusionRectTopLeft = new Rect();
    private final Rect mExclusionRectTopRight = new Rect();
    private final Rect mExclusionRectBottomRight = new Rect();
    private final Rect mExclusionRectBottomLeft = new Rect();
    private List<Rect> mExcludedRects;

    private final RectF mFrameRect;
    private final int mTouchAreaSize;

    public GestureNavigationExclusionModel(@NonNull RectF frameRect, int touchAreaSize) {
        mFrameRect = frameRect;
        mTouchAreaSize = touchAreaSize;
        refreshExclusionRects();
        mExcludedRects = Arrays.asList(mExclusionRectTopLeft,
                mExclusionRectTopRight,
                mExclusionRectBottomRight,
                mExclusionRectBottomLeft);
    }

    public List<Rect> getExcludedRects() {
        refreshExclusionRects();
        return mExcludedRects;
    }

    private void refreshExclusionRects() {
        for (Position position : Position.values()) {
            refreshExclusionRectForPosition(position);
        }
    }

    private void refreshExclusionRectForPosition(Position position) {
        switch (position) {
            case TOP_LEFT:
                mExclusionRectTopLeft.set((int) mFrameRect.left - mTouchAreaSize,
                        (int) mFrameRect.top - mTouchAreaSize,
                        (int) mFrameRect.left + mTouchAreaSize,
                        (int) mFrameRect.top + mTouchAreaSize);
            case TOP_RIGHT:
                mExclusionRectTopRight.set((int) mFrameRect.right - mTouchAreaSize,
                        (int) mFrameRect.top - mTouchAreaSize,
                        (int) mFrameRect.right + mTouchAreaSize,
                        (int) mFrameRect.top + mTouchAreaSize);
            case BOTTOM_RIGHT:
                mExclusionRectBottomRight.set((int) mFrameRect.right - mTouchAreaSize,
                        (int) mFrameRect.bottom - mTouchAreaSize,
                        (int) mFrameRect.right + mTouchAreaSize,
                        (int) mFrameRect.bottom + mTouchAreaSize);
            case BOTTOM_LEFT:
                mExclusionRectBottomLeft.set((int) mFrameRect.left - mTouchAreaSize,
                        (int) mFrameRect.bottom - mTouchAreaSize,
                        (int) mFrameRect.left + mTouchAreaSize,
                        (int) mFrameRect.bottom + mTouchAreaSize);
        }
    }
}
