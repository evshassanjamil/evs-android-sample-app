package com.evs.android.mysampleapp.week10.drawer.lib;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.evs.android.mysampleapp.R;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class FragmentNavigator implements FragmentManager.OnBackStackChangedListener {

    private final FragmentManager mFragmentManager;
    private final Context mContext;
    private static final String TAG = FragmentNavigator.class.getSimpleName();

    public FragmentNavigator(Context context, final FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;

        mFragmentManager.addOnBackStackChangedListener(this);
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void addRoot(Fragment fragment, String tag) {
        popBackStackFull();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        /*fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);*/
        fragmentTransaction.replace(R.id.frame_layout_main, fragment, tag);
        fragmentTransaction.commit();
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down);
        fragmentTransaction.add(R.id.frame_layout_main, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    ////////////////////////////// UTILITY FUNCTIONS ///////////////////////////////

    public Fragment getLastFragment() {
        return getFragmentManager().getFragments().get(getFragmentManager().getFragments().size() - 1);
    }

    public Fragment getFragmentForTag(String tag) {
        return getFragmentManager().findFragmentByTag(tag);
    }

    @SuppressWarnings("SameParameterValue")
    private void showHideFragmentForTag(String tag, boolean show) {
        Fragment fragment = getFragmentForTag(tag);
        if (fragment != null) {
            showHideFragment(fragment, show);
        } else
            Log.e(TAG, mContext.getString(R.string.fragment_not_exists_in_backstack));
    }

    private void showHideFragment(Fragment fragment, boolean show) {
        if (fragment != null) {
            if (show)
                getFragmentManager().beginTransaction().show(fragment).commit();
            else
                getFragmentManager().beginTransaction().hide(fragment).commit();
        } else
            Log.e(TAG, mContext.getString(R.string.fragment_not_exists_in_backstack));
    }

    public void hideAllFragmentsExceptTag(String tag) {
        for (Fragment fragmentToHide : getFragmentManager().getFragments()) {
            showHideFragment(fragmentToHide, false);
        }


        showHideFragmentForTag(tag, true);
    }

    @SuppressWarnings("unused")
    public void removeFragmentForTag(String tag) {
        Fragment fragment = getFragmentForTag(tag);
        if (fragment != null)
            removeFragment(fragment);
        else
            Log.e(TAG, "removeFragmentForTag(): " + mContext.getString(R.string.fragment_not_exists_in_backstack));
    }

    private void removeFragment(Fragment fragment) {
        if (fragment != null)
            getFragmentManager().beginTransaction().remove(fragment).commit();
    }


    public void popBackStack() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

            /*if (mContext instanceof Activity)
                ((Activity) mContext).onBackPressed();*/
        }
    }

    public void popBackStackFull() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                getFragmentManager().popBackStack();
            }
        }
    }

    public void popBackStackExceptRoot() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount() - 1; i++) {
                getFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public void onBackStackChanged() {
    }
}
