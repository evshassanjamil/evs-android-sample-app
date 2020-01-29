package com.evs.android.mysampleapp.week10.drawer.lib;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class Toolbar {

    private ToolbarManager.ToolBarType mToolbarType;
    private String mToolbarTitle;
    private boolean isDrawerIcon;

    public ToolbarManager.ToolBarType getToolbarType() {
        return mToolbarType;
    }

    public Toolbar setToolbarType(ToolbarManager.ToolBarType toolbarType) {
        this.mToolbarType = toolbarType;
        return this;
    }

    public String getToolbarTitle() {
        return mToolbarTitle;
    }

    public Toolbar setToolbarTitle(String toolbarTitle) {
        this.mToolbarTitle = toolbarTitle;
        return this;
    }

    public boolean isDrawerIcon() {
        return isDrawerIcon;
    }

    public Toolbar setDrawerIcon(boolean isDrawerIcon) {
        this.isDrawerIcon = isDrawerIcon;
        return this;
    }
}
