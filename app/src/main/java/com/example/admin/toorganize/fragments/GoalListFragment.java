package com.example.admin.toorganize.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.toorganize.R;
import com.example.admin.toorganize.activities.ViewTaskActivity;
import com.example.admin.toorganize.database.DBAdapter;
import com.example.admin.toorganize.models.Task;

import java.util.ArrayList;
import java.util.List;


public class GoalListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_goals_list, container, false);

        return rootView;
    }
}
