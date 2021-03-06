// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.mopub.mobileads;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.mobileads.resource.ProgressBarDrawable;

public class VastVideoProgressBarWidget extends ImageView {
    @NonNull private ProgressBarDrawable mProgressBarDrawable;
    private final int mProgressBarHeight;

    public VastVideoProgressBarWidget(@NonNull final Context context) {
        super(context);

        setId(View.generateViewId());

        mProgressBarDrawable = new ProgressBarDrawable(context);
        setImageDrawable(mProgressBarDrawable);

        mProgressBarHeight =
                Dips.dipsToIntPixels(DrawableConstants.ProgressBar.HEIGHT_DIPS, context);
    }

    public void setAnchorId(final int anchorId) {
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                mProgressBarHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, anchorId);
        setLayoutParams(layoutParams);

    }

    public void calibrateAndMakeVisible(final int duration, final int skipOffset) {
        mProgressBarDrawable.setDurationAndSkipOffset(duration, skipOffset);
        setVisibility(View.VISIBLE);
    }

    public void updateProgress(final int progress) {
        mProgressBarDrawable.setProgress(progress);
    }

    public void reset() {
        mProgressBarDrawable.reset();
        mProgressBarDrawable.setProgress(0);
    }

    // for testing
    @Deprecated
    @VisibleForTesting
    ProgressBarDrawable getImageViewDrawable() {
        return mProgressBarDrawable;
    }

    // for testing
    @Deprecated
    @VisibleForTesting
    void setImageViewDrawable(@NonNull ProgressBarDrawable drawable) {
        mProgressBarDrawable = drawable;
    }
}
