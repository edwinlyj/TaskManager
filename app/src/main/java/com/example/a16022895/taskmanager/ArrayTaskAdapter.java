package com.example.a16022895.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayTaskAdapter extends ArrayAdapter<Task> {
    // Create ArrayList of objects
    private ArrayList<Task> task;
    // To hold the context object
    private Context context;
    // Create the UI objects to hold the UI elements in row layout
    private TextView tvID, tvTaskName, tvTaskDetail;

    public ArrayTaskAdapter(Context context, int resource,
                                 ArrayList<Task> task ) {
            super(context, resource, task );
        this.task = task ;
        this.context = context;
    }
    // getView() is called every time for every row
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent,
                false);

        tvID = (TextView) rowView.findViewById(R.id.tvID);
        tvTaskDetail = (TextView) rowView.findViewById(R.id.tvTaskDetail);
        tvTaskName = (TextView) rowView.findViewById(R.id.tvTaskName);
        Task object = task.get(pos);
        // Set the TextView to show the object info

        tvID.setText(""+object.getId()+"");
        tvTaskDetail.setText(object.getTaskDetail());
        tvTaskName.setText(object.getTaskName());
        // Return this row that is being populated.
        return rowView;
    }

}
