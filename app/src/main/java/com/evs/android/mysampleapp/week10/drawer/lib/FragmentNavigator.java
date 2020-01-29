package com.evs.android.mysampleapp.week10.drawer.lib;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.evs.android.mysampleapp.R;

import java.util.ArrayList;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class FragmentNavigator implements FragmentManager.OnBackStackChangedListener {

    private final FragmentManager mFragmentManager;
    private final Context mContext;
    private static final String TAG = FragmentNavigator.class.getSimpleName();
    private final ArrayList<FragmentState> mListFragmentStates = new ArrayList<>();

    public class FragmentState {
        public String tag;
        public boolean isHidden = false;
    }

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
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
        // android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame_layout_main, fragment, tag);
        fragmentTransaction.commit();

        // Managing fragments hidden state
        addFragmentState(tag, false);
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down);
        fragmentTransaction.add(R.id.frame_layout_main, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();


        addFragmentState(tag, false);
    }


    ////////////////////////// Fragment States ////////////////////////////

    @SuppressWarnings("SameParameterValue")
    private void addFragmentState(String tag, boolean isHidden) {
        FragmentState state = new FragmentState();
        state.tag = tag;
        state.isHidden = isHidden;
        mListFragmentStates.add(state);
    }

    private void updateFragmentStateForTag(String tag, boolean isHidden) {
        for (FragmentState state : mListFragmentStates) {
            if (state.tag.equals(tag)) {
                state.isHidden = isHidden;
            }
        }
    }

    private void updateAllFragmentStateAsHidden() {
        for (int i = 0; i < mListFragmentStates.size(); i++) {
            mListFragmentStates.get(i).isHidden = true;
        }
    }

    /**
     * @return 1 = hidden, 0 = shown, -1 = unknown
     */
    public int isFragmentHiddenForTag(String tag) {
        for (FragmentState state : mListFragmentStates) {
            if (state.tag.equals(tag)) {
                if (state.isHidden)
                    return 1;
                else
                    return 0;
            }
        }
        return -1;
    }

    @SuppressWarnings("unused")
    private void removeLastFragmentState() {
        mListFragmentStates.remove(mListFragmentStates.size() - 1);
    }

    @SuppressWarnings("unused")
    private void removeFragmentStateForPosition(int position) {
        mListFragmentStates.remove(position);
    }

    private void removeFragmentStateForTag(String tag) {
        for (int i = 0; i < mListFragmentStates.size(); i++) {
            if (mListFragmentStates.get(i).tag.equals(tag)) {
                mListFragmentStates.remove(i);
            }
        }
    }

    private void clearAllFragmentStateList() {
        mListFragmentStates.clear();
    }

    private void clearFragmentStateListExceptFirstElement() {
        for (int i = mListFragmentStates.size(); i > 0; i--) {
            mListFragmentStates.remove(i);
        }
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

            updateFragmentStateForTag(tag, !show);
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
        updateAllFragmentStateAsHidden();


        showHideFragmentForTag(tag, true);
        updateFragmentStateForTag(tag, false);
    }

    @SuppressWarnings("unused")
    public void removeFragmentForTag(String tag) {
        Fragment fragment = getFragmentForTag(tag);
        if (fragment != null)
            removeFragment(fragment);
        else
            Log.e(TAG, "removeFragmentForTag(): " + mContext.getString(R.string.fragment_not_exists_in_backstack));

        removeFragmentStateForTag(tag);
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

        clearAllFragmentStateList();
    }

    public void popBackStackExceptRoot() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount() - 1; i++) {
                getFragmentManager().popBackStack();
            }
        }

        clearFragmentStateListExceptFirstElement();
    }

    @Override
    public void onBackStackChanged() {
        /*if (getFragmentManager().getBackStackEntryCount() == 0) {
            // Roll backing TTS Voice state when only BaseFragmentMap is remaining in back stack
            //MyApplication.getInstance().getAppPreferences().setNavigationTBTVoiceMute(userPrefMuteState);

            // Changing location request priority to handle the flow:
            // If user opened navigation screen priority changed to HIGH if he didn't do anything and
            // Navigates back to the previous screen then priority should be changed back to the BALANCED
            //changeLocationRequestPriorityBackIfUserDoNothing();
        }*/
    }
}
