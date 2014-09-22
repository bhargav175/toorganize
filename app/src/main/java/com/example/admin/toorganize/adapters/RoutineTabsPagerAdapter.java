package com.example.admin.toorganize.adapters;

/**
 * Created by Admin on 22-09-2014.
 */


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.toorganize.fragments.GoalListFragment;
import com.example.admin.toorganize.fragments.NoteListFragment;
import com.example.admin.toorganize.fragments.RoutineListFragment;
import com.example.admin.toorganize.fragments.TaskListFragment;

public class RoutineTabsPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;

    public RoutineTabsPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    public RoutineTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new TaskListFragment();
            case 1:
                return new RoutineListFragment();
            case 2:
                return new GoalListFragment();
            case 3:
                return new NoteListFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }

}