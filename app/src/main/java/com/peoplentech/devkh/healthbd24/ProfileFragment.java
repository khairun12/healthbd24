package com.peoplentech.devkh.healthbd24;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by User on 3/24/2018.
 */

public class ProfileFragment extends Fragment {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = (TextView) getView().findViewById(R.id.myName);
        txtEmail = (TextView) getView().findViewById(R.id.myEmail);
        btnLogout = (Button) getView().findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // session manager
        session = new SessionManager(getActivity().getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        FragmentManager fragmentManager = getFragmentManager();
        LoginFragment doc = new LoginFragment();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.root, doc).addToBackStack("fragment_main").commit();
            }
        }
    }
}
