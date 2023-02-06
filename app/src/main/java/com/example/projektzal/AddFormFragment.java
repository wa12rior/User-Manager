package com.example.projektzal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.projektzal.databinding.FragmentAddBinding;
import com.example.projektzal.model.AppDatabase;
import com.example.projektzal.model.User;

public class AddFormFragment extends Fragment {

    private FragmentAddBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "users").allowMainThreadQueries().build();

        binding = FragmentAddBinding.inflate(inflater, container, false);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.formName.getText().toString();
                String surname = binding.formSurname.getText().toString();

                User user = new User();
                user.firstName = name;
                user.lastName = surname;

                db.userDao().insertAll(user);

                NavHostFragment.findNavController(AddFormFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

                Toast.makeText(getContext(), "New user added", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddFormFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}