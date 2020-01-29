package com.evs.android.mysampleapp.week9.animate.bitmaps;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class AnimateBitmapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate_bitmaps);

        ImageView ivBitmapAnimation = findViewById(R.id.ivBitmapAnimation);
        ivBitmapAnimation.setBackgroundResource(R.drawable.animation_back);
        ivBitmapAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AnimationDrawable) ivBitmapAnimation.getBackground()).start();
            }
        });
    }
}
