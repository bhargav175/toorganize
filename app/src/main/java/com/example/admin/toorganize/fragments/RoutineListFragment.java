package com.example.admin.toorganize.fragments;

import android.app.Activity;
import android.app.Fragment;
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

/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.admin.toorganize.fragments.RoutineListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.admin.toorganize.fragments.RoutineListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RoutineListFragment extends Fragment {

    private DBAdapter dbAdapter;
    public static final String Tag="TaskManager";
    public final static String EXTRA_MESSAGE = "com.example.admin.toorganize.TaskManager";
    public final static String TASK_TEXT = "task_text";
    public final static String TASK_ID = "task_id";


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoutineListFragment newInstance(String param1, String param2) {
        RoutineListFragment fragment = new RoutineListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public RoutineListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private List<Task> getAllTasks(List<Task> taskList) {
        dbAdapter.open();
        Cursor cursor= dbAdapter.fetchAllTasks();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = dbAdapter.cursorToTask(cursor);
            taskList.add(task);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        dbAdapter.close();
        return taskList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Task> taskList = new ArrayList<Task>();
        dbAdapter= new DBAdapter(getActivity());
        View view=inflater.inflate(R.layout.fragment_task_list, container, false);
        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(getActivity(),
                android.R.layout.simple_list_item_1, getAllTasks(taskList));
        ListView list = (ListView) view.findViewById(R.id.taskListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Task task= (Task) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getActivity(), ViewTaskActivity.class);
                intent.putExtra(TASK_TEXT, task.getText());
                intent.putExtra(TASK_ID,(int) task.getId());
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
