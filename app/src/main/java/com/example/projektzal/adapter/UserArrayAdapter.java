package com.example.projektzal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projektzal.R;
import com.example.projektzal.model.User;

import java.util.List;

public class UserArrayAdapter<T> extends ArrayAdapter<User> {
    private final Context context;
    private final List<User> values;

    public UserArrayAdapter(Context context, List<User> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        name.setText(values.get(position).firstName);

        TextView surname = (TextView) rowView.findViewById(R.id.surname);
        surname.setText(values.get(position).lastName);

        return rowView;
    }
}
