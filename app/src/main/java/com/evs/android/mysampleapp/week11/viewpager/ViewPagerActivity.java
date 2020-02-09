package com.evs.android.mysampleapp.week11.viewpager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    ArrayList<SliderItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        toolbar.setTitle(getTitle());
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listItems = new ArrayList<>();
        listItems.add(new SliderItem("Coca Cola", "https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-thumbnail/s3/0016/9323/brand.gif"));
        listItems.add(new SliderItem("Sprite", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN5LGU9oDDvPirToSmpJd_Eb8Ny7J5-M1yuMe4rOqORF0sWYoF&s"));
        listItems.add(new SliderItem("Milk Pak", "https://www.brandsaward.com/application/assets/images/boya-winners/milkpak-logo.jpg"));
        listItems.add(new SliderItem("Dalda", "https://www.careerz360.com/cdn.careerz360.com/Content/UserData/empr/e8ded2f5-28a4-4abc-a280-57a693cd0f40/profile_pics/thumbnail_b0bae04d-4ead-41fd-a0c6-af9472add3d5.png"));
        listItems.add(new SliderItem("Apple", "https://sc01.alicdn.com/kf/ULB8w6e6KOaMiuJk43PTq6ySmXXaR/Fresh-red-delicious-apple-products.jpg_350x350.jpg"));

        populatePager(0, listItems);
    }


    private MyFragmentStatePagerAdapter mAdapter;
    private ViewPager viewPager;

    private void populatePager(int position, @NonNull ArrayList<SliderItem> items) {
        viewPager = findViewById(R.id.vp_images);

        if (viewPager == null)
            return;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateUI(items.get(position));
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), items);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(position, true);

        //startSlideShow(2000);
    }


    /*int currentItemIndex = 0;
    public void startSlideShow(int durationMS) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(currentItemIndex < listItems.size()) {
                    viewPager.setCurrentItem(currentItemIndex++);
                    new Handler().postDelayed(this, durationMS);
                } else {
                    currentItemIndex = 0;
                    new Handler().post(this);
                }
            }
        });
    }*/

    private void updateUI(SliderItem item) {
        TextView tvCaption = findViewById(R.id.tvCaption);
        // CAPTION
        if (AppUtils.isValidString(item.getCaption())) {
            tvCaption.setVisibility(View.VISIBLE);
            tvCaption.setText(item.getCaption());
        } else {
            tvCaption.setVisibility(View.GONE);
        }
    }
}
