package com.example.contact.ui.main.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.contact.R;
import com.example.contact.ui.main.fragment.RegisterContactFragment;
import com.example.contact.ui.main.fragment.ContactListFragment;
import com.example.contact.ui.main.fragment.FavouriteListFragment;
import com.example.contact.ui.main.fragment.RecentListFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_contact, R.string.tab_call,
            R.string.tab_fav, R.string.tab_new_content};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        //Log.d(TAG, "getItem: ");
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            return ContactListFragment.newInstance(0);
        } else if(position == 1){
            return RecentListFragment.newInstance(1);
        }
        else if(position == 2){
            return FavouriteListFragment.newInstance(2);
        }
        else {
            return RegisterContactFragment.newInstance(3);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}