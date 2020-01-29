package com.evs.android.mysampleapp.week10.drawer.lib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.IdRes;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week10.drawer.DrawerManager;

import java.util.Stack;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class ToolbarManager {

    private final Context mContext;
    private final androidx.appcompat.widget.Toolbar mToolbar;
    private Toolbar customToolBarObj;
    private Stack<Toolbar> mStack = new Stack<>();

    private DrawerManager mDrawerManager;
    private androidx.appcompat.widget.Toolbar.OnMenuItemClickListener mListener;

    private static final String TAG = ToolbarManager.class.getSimpleName();

    @SuppressWarnings("SameParameterValue")
    public ToolbarManager(Context context, androidx.appcompat.widget.Toolbar toolbar,
                          DrawerManager drawerManager, int resMenu,
                          androidx.appcompat.widget.Toolbar.OnMenuItemClickListener listener) {
        mContext = context;
        mToolbar = toolbar;
        mDrawerManager = drawerManager;

        setupToolBar(resMenu);
        setToolbarListeners(drawerManager, listener);

        mListener = listener;
    }

    public void pushAndUpdateToolbarItem(Toolbar toolbar) {
        if (toolbar == null)
            return;

        mStack.push(toolbar);

        updateToolbarItemsForObject(toolbar);
    }

    public void popAndUpdateToolbarItem() {

        popToolbarItem();

        if (mStack.size() > 0)
            updateToolbarItemsForObject(mStack.peek());
    }

    public void popToolbarItem() {
        if (mStack.size() > 0)
            mStack.pop();
    }

    public androidx.appcompat.widget.Toolbar getToolbar() {
        return mToolbar;
    }

    private void setupToolBar(int resMenu) {
        // Inflating menu to Toolbar
        if (mToolbar != null)
            mToolbar.inflateMenu(resMenu);

        if (mContext != null)
            ((Activity) mContext).invalidateOptionsMenu();
    }


    public void setToolbarListeners(final DrawerManager drawerManager,
                                    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener listener) {
        // Registering menu item click listener on toolbar
        mToolbar.setOnMenuItemClickListener(listener);

        // Registering drawer/back button listener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext != null) {
                    if (drawerManager.getActionBarDrawerToggle().isDrawerIndicatorEnabled())
                        drawerManager.toggleDrawer();
                    else {
                        ((Activity) mContext).onBackPressed();
                    }
                }
            }
        });
    }

    public enum ToolBarType {
        NONE, OVERFLOW_MENU
    }

    public static ToolBarType getToolbarTypeByOrdinal(int ordinal) {
        if (ToolBarType.NONE.ordinal() == ordinal)
            return ToolBarType.NONE;
        else if (ToolBarType.OVERFLOW_MENU.ordinal() == ordinal)
            return ToolBarType.OVERFLOW_MENU;

        return null;
    }

    @SuppressLint("PrivateResource")
    public void updateToolbarItemsForObject(Toolbar toolbar) {
        if (toolbar != null) {
            customToolBarObj = toolbar;
            // Setting drawer icon and back icon for toolbar
            if (mContext != null) {
                if (toolbar.isDrawerIcon()) {
                    mDrawerManager.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
                } else {
                    mDrawerManager.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
                    mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
                }
            }

            // Updating toolbar title
            setToolbarTitle(toolbar.getToolbarTitle());

            // Visibility of menu items
            if (toolbar.getToolbarType().equals(ToolBarType.NONE))
                setMenuVisible(R.id.mi_main_cart, false);
            else
                setMenuVisible(R.id.mi_main_cart, true);

            mToolbar.postInvalidate();
            ((Activity) mContext).onPrepareOptionsMenu(mToolbar.getMenu());

            /*if (getActivityMainFromContext() != null)
                getActivityMainFromContext().invalidateOptionsMenu();*/
        } else {
            Log.e(TAG, "updateToolbarItemsForObject(): Object is null");
        }
    }

    public void setToolbar(Toolbar toolbar) {
        updateToolbarItemsForObject(toolbar);
    }

    public Toolbar getCustomToolBarObj() {
        return customToolBarObj;
    }

    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);

        mToolbar.postInvalidate();

        /*if (getActivityMainFromContext() != null)
            getActivityMainFromContext().invalidateOptionsMenu();*/
    }

    public void setMenuVisible(@IdRes int itemResId, boolean visible) {
        if (mToolbar != null && mToolbar.getMenu() != null)
            mToolbar.getMenu().findItem(R.id.mi_main_cart).setVisible(visible);
    }


}
