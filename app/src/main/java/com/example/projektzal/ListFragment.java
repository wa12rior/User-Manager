package com.example.projektzal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.projektzal.adapter.UserArrayAdapter;
import com.example.projektzal.databinding.FragmentListBinding;
import com.example.projektzal.model.AppDatabase;
import com.example.projektzal.model.User;
import com.example.projektzal.model.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    public static List<User> users;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentListBinding.inflate(inflater, container, false);

        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "users").allowMainThreadQueries().build();

        UserDao userDao = db.userDao();
        users = new ArrayList<>();
        for (User user : userDao.getAll()) {
            users.add(user);
        }

        ListView usersView = binding.users;
        UserArrayAdapter<User> arr = new UserArrayAdapter<>(getActivity().getApplicationContext(), users);
        usersView.setAdapter(arr);

        usersView.setOnItemClickListener((adapterView, view, i, l) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("userId", arr.getItem(i).uid);
            NavHostFragment.findNavController(ListFragment.this)
                    .navigate(R.id.action_FirstFragment_to_detailFragment, bundle);
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}