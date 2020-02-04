package com.evs.android.mysampleapp.week11.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by hassanjamil on 04-02-2020.
 *
 * @author hassanjamil
 */
class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_ITEMS;
    private final ArrayList<Integer> page_indexes;
    private ArrayList<SliderItem> mItems = new ArrayList<>();

    MyFragmentStatePagerAdapter(@NonNull FragmentManager fm, @NonNull ArrayList<SliderItem> items) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mItems.clear();
        mItems.addAll((ArrayList<SliderItem>) items.clone());
        NUM_ITEMS = items.size();

        page_indexes = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS; i++) {
            page_indexes.add(i);
        }
    }

    @Override
    public int getCount() {
        return page_indexes.size();
    }

    @Override
    public Fragment getItem(int position) {
        Integer index = page_indexes.get(position);
        return ImageFragment.newInstance(index, new Gson().toJson(mItems.get(position)));
    }

    void deletePage(int position) {
        page_indexes.remove(position);
        notifyDataSetChanged();
    }

    // This is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(@NonNull Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }
}
