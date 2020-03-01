package com.evs.android.mysampleapp.week10.drawer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;
import com.evs.android.mysampleapp.week10.drawer.lib.DrawerManager;
import com.evs.android.mysampleapp.week10.drawer.lib.FragmentNavigator;
import com.evs.android.mysampleapp.week10.drawer.lib.ToolbarManager;
import com.evs.android.mysampleapp.week9.preference.AppPreferences;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class DrawerActivity extends AppCompatActivity {

    private DrawerManager mDrawerManager;
    private ToolbarManager mTbManager;
    private FragmentNavigator mFragmentNavigator;
    private MenuItem menuItemChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);


        init();
        Toolbar toolbar = findViewById(R.id.tbMain);

        // Instantiating DrawerManager
        mDrawerManager = new DrawerManager(this, toolbar,
                findViewById(R.id.dlMain), findViewById(R.id.nvMain),
                R.string.drawer_activity, R.string.drawer_activity,
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawerItem(item);
                        return false;
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close the navigation drawer
                        if (mDrawerManager != null)
                            mDrawerManager.closeDrawer();

                        if (AppPreferences.getInstance(DrawerActivity.this).isUserLoggedIn())
                            AppUtils.showToastShort(DrawerActivity.this, "User Logged In");
                        else
                            AppUtils.showToastShort(DrawerActivity.this, "User not Logged In");
                    }
                });
        // Setting Hamburger color icon
        mDrawerManager.setHamburgerIconColor(R.color.white);
        setDrawerMenusVisibility();

        // Instantiating ToolbarManager
        mTbManager = new ToolbarManager(this, toolbar,
                mDrawerManager, R.menu.menu_main, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mi_cart:
                        onCartMenuClick();
                        break;
                    case R.id.mi_change_lang:
                        AppUtils.showToastShort(DrawerActivity.this, "Language Menu Clicked");
                        //onUpdateLanguageMenuClick();
                        break;
                }
                return false;
            }
        });


        menuItemChecked = mDrawerManager.getMenuItem(R.id.mi_drawer_item_home).setChecked(true);
        onHomeMenuClick();
    }

    private void init() {
        mFragmentNavigator = new FragmentNavigator(this, getSupportFragmentManager());
    }

    private void setDrawerMenusVisibility() {
        // Setting Drawer Menu Visibility based on user logged in status
        boolean loggedIn = AppPreferences.getInstance(this).isUserLoggedIn();
        mDrawerManager.setDrawerMenuVisible(R.id.mi_drawer_item_sign_in, !loggedIn);
        mDrawerManager.setDrawerMenuVisible(R.id.mi_drawer_item_logout, loggedIn);
    }

    private void selectDrawerItem(MenuItem item) {
        if (menuItemChecked != null) {
            menuItemChecked.setChecked(false);
        }

        switch (item.getItemId()) {

            case R.id.mi_drawer_item_home:
                //AppUtils.showToastShort(DrawerActivity.this, "Home Menu Clicked");
                closeDrawerIfOpen();
                menuItemChecked = mDrawerManager.getMenuItem(R.id.mi_drawer_item_home).setChecked(true);
                onHomeMenuClick();
                break;
            case R.id.mi_drawer_item_sign_in:
                //AppUtils.showToastShort(DrawerActivity.this, "Sign In Menu Clicked");
                closeDrawerIfOpen();
                menuItemChecked = mDrawerManager.getMenuItem(R.id.mi_drawer_item_sign_in).setChecked(true);
                onLoginMenuClick();
                break;
            case R.id.mi_drawer_item_logout:
                //AppPreferences.getInstance(DrawerActivity.this).logoutUser();
                menuItemChecked = item.setChecked(true);
                AppUtils.showToastShort(DrawerActivity.this, "Logout Menu Clicked");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (mDrawerManager.isDrawerOpen()) {
            mDrawerManager.closeDrawer();
            return;
        }

        if (mFragmentNavigator.getFragmentManager().getBackStackEntryCount() > 0) {
            mFragmentNavigator.popBackStack();
        } else {
            showDialogExit();
        }

        mTbManager.popAndUpdateToolbarItem();
    }

    private void onHomeMenuClick() {
        mFragmentNavigator.addRoot(new HomeFragment(), HomeFragment.class.getSimpleName());
    }

    private void onLoginMenuClick() {
        mFragmentNavigator.addFragment(new LoginFragment(), LoginFragment.class.getSimpleName());
    }

    private void onCartMenuClick() {
        mFragmentNavigator.addFragment(new CartFragment(), CartFragment.class.getSimpleName());
    }

    private void closeDrawerIfOpen() {
        if (mDrawerManager.isDrawerOpen())
            mDrawerManager.closeDrawer();
    }

    private void showDialogExit() {
        AppUtils.createAndShowAlertDialog(this, 0, getString(R.string.exit),
                getString(R.string.dialog_exit_message),
                true, true, new String[]{getString(R.string.yes),
                        getString(R.string.no)},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                /*moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);*/
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
    }
}
