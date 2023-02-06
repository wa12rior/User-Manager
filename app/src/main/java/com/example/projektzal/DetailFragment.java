package com.example.projektzal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.projektzal.databinding.FragmentDetailsBinding;
import com.example.projektzal.model.AppDatabase;
import com.example.projektzal.model.User;
import com.example.projektzal.model.UserDao;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    private FragmentDetailsBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "users").allowMainThreadQueries().build();

        UserDao userDao = db.userDao();

        int userId = getArguments().getInt("userId");

        User user = userDao.loadAllById(userId);

        binding.detailsName.setText(user.firstName);
        binding.detailsSurname.setText(user.lastName);

        binding.removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.delete(userId);

                NavHostFragment.findNavController(DetailFragment.this)
                        .navigate(R.id.action_detailFragment_to_FirstFragment);

                Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}