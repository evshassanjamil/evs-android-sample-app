package com.evs.android.mysampleapp.week10.drawer.lib;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;
import com.google.android.material.navigation.NavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class DrawerManager {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private final int GRAVITY_DRAWER_OPEN = GravityCompat.START;
    private Context mContext;

    private TextView tvHeaderText;
    private CircularImageView civHeaderImage;
    private ImageView ivHeaderMain;

    public DrawerManager(Context context, Toolbar toolbar, DrawerLayout drawerLayout,
                         NavigationView navigationView, @StringRes int open, @StringRes int close,
                         NavigationView.OnNavigationItemSelectedListener navListener,
                         View.OnClickListener headerClickListener) {
        mContext = context;
        mDrawerLayout = drawerLayout;
        mNavigationView = navigationView;

        tvHeaderText = mNavigationView.getHeaderView(0).findViewById(R.id.tv_drawer_header_user_name);
        civHeaderImage = mNavigationView.getHeaderView(0).findViewById(R.id.civ_drawer_header_user);
        ivHeaderMain = mNavigationView.getHeaderView(0).findViewById(R.id.iv_drawer_header_cover);

        setupDrawerAndListeners(context, toolbar, open, close, navListener, headerClickListener);
    }

    public void setHamburgerIconColor(@ColorRes int color) {
        if (mActionBarDrawerToggle == null)
            return;

        // Setting Hamburger icon color
        mActionBarDrawerToggle.getDrawerArrowDrawable()
                .setColor(ContextCompat.getColor(mContext, color));
    }

    private void setupDrawerAndListeners(final Context context, Toolbar toolbar,
                                         @StringRes int open, @StringRes int close,
                                         NavigationView.OnNavigationItemSelectedListener navListener,
                                         View.OnClickListener headerClickListener) {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // Registering Navigation View's Header click event
        mNavigationView.getHeaderView(0).setOnClickListener(headerClickListener);

        // Setup drawer view
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                (Activity) context, mDrawerLayout, toolbar, open, close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                ((Activity) context).invalidateOptionsMenu();
                syncState();

                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                ((Activity) context).invalidateOptionsMenu();
                syncState();

                AppUtils.hideShowSoftKeyboardFromFocusedView((Activity) context, true);
                //setDrawerViews(UserPreferences.getInstance(ActivityMain.this).makeLoginResponse());

                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        };

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mActionBarDrawerToggle.syncState();

        // Setting up listener
        mNavigationView.setNavigationItemSelectedListener(navListener);
    }

    public void toggleDrawer() {
        if (isDrawerOpen())
            mDrawerLayout.closeDrawers();
        else
            mDrawerLayout.openDrawer(GRAVITY_DRAWER_OPEN);
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return mActionBarDrawerToggle;
    }

    public NavigationView getNavigationView() {
        return mNavigationView;
    }

    public TextView getHeaderTextView() {
        return tvHeaderText;
    }

    public CircularImageView getHeaderImageView() {
        return civHeaderImage;
    }

    public ImageView getHeaderMainImageView() {
        return ivHeaderMain;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GRAVITY_DRAWER_OPEN);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    public void setDrawerMenuVisible(@IdRes int itemResId, boolean visible) {
        if (mNavigationView != null && mNavigationView.getMenu() != null)
            mNavigationView.getMenu().findItem(itemResId).setVisible(visible);
    }

    public MenuItem getMenuItem(@IdRes int resId) {
        if (mNavigationView != null && mNavigationView.getMenu() != null)
            return mNavigationView.getMenu().findItem(resId);
        else
            return null;
    }

    public void onBackPressed() {
        if (isDrawerOpen()) {
            toggleDrawer();
        }
    }


}
