package com.atta.weswap.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.atta.weswap.R;
import com.atta.weswap.model.SessionManager;
import com.atta.weswap.model.User;
import com.atta.weswap.presenters.ProfileContract;
import com.atta.weswap.presenters.ProfilePresenter;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileContract.View {

    ProfilePresenter profilePresenter;

    TextInputEditText nameText, emailText, mobileText;

    String phone;

    Button saveProfile;

    View root;

    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        initiateViews();


        profilePresenter = new ProfilePresenter(this, getContext());

        profilePresenter.getProfile(SessionManager.getInstance(getContext()).getUserId());

        return root;
    }


    private void initiateViews() {


        nameText = root.findViewById(R.id.profile_name);
        emailText = root.findViewById(R.id.profile_email);

        mobileText = root.findViewById(R.id.profile_phone);
        progressBar = root.findViewById(R.id.progress_bar);

        saveProfile = root.findViewById(R.id.save_profile);
        saveProfile.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == saveProfile){
            progressBar.setVisibility(View.VISIBLE);
            User user = new User(SessionManager.getInstance(getContext()).getUserId(), nameText.getText().toString().trim(), mobileText.getText().toString().trim());

            profilePresenter.updateProfile(user);


        }
    }

    @Override
    public void showProfile(User user) {
        nameText.setText(user.getName());
        emailText.setText(user.getEmail());

        mobileText.setText(user.getPhone());
    }


    @Override
    public void moveToMain() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                .navigate(ProfileFragmentDirections.actionNavProfileToNavHome());
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}