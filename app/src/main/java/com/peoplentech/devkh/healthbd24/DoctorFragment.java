package com.peoplentech.devkh.healthbd24;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by User on 3/22/2018.
 */

public class DoctorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        CardView doctorView = (CardView) getView().findViewById(R.id.docappoint);
        CardView callHome = (CardView) getView().findViewById(R.id.homeCall);
        doctorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AppointDocFragment doc = new AppointDocFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, doc).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });

        callHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "This feature is under development", Toast.LENGTH_SHORT).show();
                /*FragmentManager fragmentManager = getFragmentManager();
                AppointDocFragment doc = new AppointDocFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, doc).addToBackStack("fragment_doc").commit();
                    }
                }*/
            }
        });

    }

}


