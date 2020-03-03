package com.evs.android.mysampleapp.week11.viewpager;


import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;
import com.google.gson.Gson;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by hassanjamil on 04-02-2020.
 *
 * @author hassanjamil
 */
public class ImageFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private static final String KEY_DATA = "data";

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    static Fragment newInstance(int position, String data) {
        ImageFragment f = new ImageFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(KEY_DATA, data);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    private SliderItem mSliderItem;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(KEY_DATA)) {
            String data = getArguments().getString(KEY_DATA);
            mSliderItem = new Gson().fromJson(data, SliderItem.class);
        }

        // INITIATING PROGRESS BAR AND SETTING COLOR
        ProgressBar pbLoading = view.findViewById(R.id.pb_image);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawableProgress = DrawableCompat.wrap(pbLoading.getIndeterminateDrawable());
            DrawableCompat.setTint(drawableProgress, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            pbLoading.setIndeterminateDrawable(DrawableCompat.unwrap(drawableProgress));
        } else {
            pbLoading.getIndeterminateDrawable().setColorFilter(
                    ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
            pbLoading.setIndeterminateTintList(ColorStateList.valueOf(color));
        } else {
            Drawable progressDrawable = pbLoading.getProgressDrawable().mutate();
            int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
            progressDrawable.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
            pbLoading.setIndeterminateDrawable(progressDrawable);
            pbLoading.getIndeterminateDrawable().setColorFilter(color,
                    android.graphics.PorterDuff.Mode.SRC_IN);
        }*/

        if (getContext() != null && mSliderItem != null
                && mSliderItem.getImageUrl() != null) {
            loadFullScreenImage(view, mSliderItem.getImageUrl());
        }
    }

    // Helper Methods
    private final int paletteColorType = PaletteType.DARK_MUTED.ordinal();

    private void loadFullScreenImage(@NonNull View view, String imageUrl) {
        ProgressBar progressBar = view.findViewById(R.id.pb_image);
        ZoomageView zoomageView = view.findViewById(R.id.zv_image);
        RelativeLayout root = view.findViewById(R.id.rl_item_root);


        // CAPTION
        /*if(AppUtils.isValidString(caption)) {
            tvCaption.setVisibility(View.VISIBLE);
            tvCaption.setText(caption);
        } else {
            tvCaption.setVisibility(View.GONE);
        }*/

        // PROGRESS BAR VISIBILITY
        progressBar.setVisibility(View.VISIBLE);

        // IMAGE LOADING
        if (AppUtils.isValidWebURL(imageUrl)) {
            Picasso.with(getContext())
                    .load(imageUrl)
                    .centerInside()
                    .resize(500, 500)
                    .error(R.drawable.ic_broken_image)
                    .into(zoomageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);

                            Bitmap bitmap = ((BitmapDrawable) zoomageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    applyPalette(palette, root);
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });
        } else {
            zoomageView.setImageDrawable(null);
        }
    }

    private void applyPalette(Palette palette, RelativeLayout bgLinearLayout) {
        int bgColor = getBackgroundColor(palette);
        if (bgColor != -1)
            bgLinearLayout.setBackgroundColor(bgColor);
    }

    private int getBackgroundColor(Palette palette) {
        int bgColor = -1;

        int vibrantColor = palette.getVibrantColor(0x000000);
        int lightVibrantColor = palette.getLightVibrantColor(0x000000);
        int darkVibrantColor = palette.getDarkVibrantColor(0x000000);

        int mutedColor = palette.getMutedColor(0x000000);
        int lightMutedColor = palette.getLightMutedColor(0x000000);
        int darkMutedColor = palette.getDarkMutedColor(0x000000);

        if (paletteColorType == PaletteType.VIBRANT.ordinal()) {
            if (vibrantColor != 0) { // primary option
                bgColor = vibrantColor;
            } else if (lightVibrantColor != 0) { // fallback options
                bgColor = lightVibrantColor;
            } else if (darkVibrantColor != 0) {
                bgColor = darkVibrantColor;
            } else if (mutedColor != 0) {
                bgColor = mutedColor;
            } else if (lightMutedColor != 0) {
                bgColor = lightMutedColor;
            } else if (darkMutedColor != 0) {
                bgColor = darkMutedColor;
            }
        } else if (paletteColorType == PaletteType.LIGHT_VIBRANT.ordinal()) {
            if (lightVibrantColor != 0) { // primary option
                bgColor = lightVibrantColor;
            } else if (vibrantColor != 0) { // fallback options
                bgColor = vibrantColor;
            } else if (darkVibrantColor != 0) {
                bgColor = darkVibrantColor;
            } else if (mutedColor != 0) {
                bgColor = mutedColor;
            } else if (lightMutedColor != 0) {
                bgColor = lightMutedColor;
            } else if (darkMutedColor != 0) {
                bgColor = darkMutedColor;
            }
        } else if (paletteColorType == PaletteType.DARK_VIBRANT.ordinal()) {
            if (darkVibrantColor != 0) { // primary option
                bgColor = darkVibrantColor;
            } else if (vibrantColor != 0) { // fallback options
                bgColor = vibrantColor;
            } else if (lightVibrantColor != 0) {
                bgColor = lightVibrantColor;
            } else if (mutedColor != 0) {
                bgColor = mutedColor;
            } else if (lightMutedColor != 0) {
                bgColor = lightMutedColor;
            } else if (darkMutedColor != 0) {
                bgColor = darkMutedColor;
            }
        } else if (paletteColorType == PaletteType.MUTED.ordinal()) {
            if (mutedColor != 0) { // primary option
                bgColor = mutedColor;
            } else if (lightMutedColor != 0) { // fallback options
                bgColor = lightMutedColor;
            } else if (darkMutedColor != 0) {
                bgColor = darkMutedColor;
            } else if (vibrantColor != 0) {
                bgColor = vibrantColor;
            } else if (lightVibrantColor != 0) {
                bgColor = lightVibrantColor;
            } else if (darkVibrantColor != 0) {
                bgColor = darkVibrantColor;
            }
        } else if (paletteColorType == PaletteType.LIGHT_MUTED.ordinal()) {

            if (lightMutedColor != 0) { // primary option
                bgColor = lightMutedColor;
            } else if (mutedColor != 0) { // fallback options
                bgColor = mutedColor;
            } else if (darkMutedColor != 0) {
                bgColor = darkMutedColor;
            } else if (vibrantColor != 0) {
                bgColor = vibrantColor;
            } else if (lightVibrantColor != 0) {
                bgColor = lightVibrantColor;
            } else if (darkVibrantColor != 0) {
                bgColor = darkVibrantColor;
            }
        } else if (paletteColorType == PaletteType.DARK_MUTED.ordinal()) {
            if (darkMutedColor != 0) { // primary option
                bgColor = darkMutedColor;
            } else if (mutedColor != 0) { // fallback options
                bgColor = mutedColor;
            } else if (lightMutedColor != 0) {
                bgColor = lightMutedColor;
            } else if (vibrantColor != 0) {
                bgColor = vibrantColor;
            } else if (lightVibrantColor != 0) {
                bgColor = lightVibrantColor;
            } else if (darkVibrantColor != 0) {
                bgColor = darkVibrantColor;
            }
        }

        return bgColor;
    }
}
