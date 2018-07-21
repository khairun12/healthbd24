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

/**
 * Created by User on 3/22/2018.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        CardView doctorView = (CardView) getView().findViewById(R.id.cardview);
        doctorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DoctorFragment doc = new DoctorFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, doc).addToBackStack("fragment_doc").commit();
                        }
                    }
                }
        });

        CardView hospital = (CardView) getView().findViewById(R.id.cardviewHospital);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                HospitalFragment hos = new HospitalFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, hos).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });
        CardView ambulance = (CardView) getView().findViewById(R.id.cardview2ambulance);
        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                AmbulanceFragment amb = new AmbulanceFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, amb).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });
        CardView pharmacy = (CardView) getView().findViewById(R.id.cardview9pharmacy);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                PharmacyFragment amb = new PharmacyFragment() ;
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, amb).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });
        CardView eye = (CardView) getView().findViewById(R.id.cardview6);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                EyeFragment amb = new EyeFragment() ;
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, amb).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });

        CardView kidney = (CardView) getView().findViewById(R.id.cardview7);
        kidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                KidneyFragment amb = new KidneyFragment() ;
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, amb).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });

        CardView blood = (CardView) getView().findViewById(R.id.cardview3);
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                BloodFragment amb = new BloodFragment();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.root, amb).addToBackStack("fragment_doc").commit();
                    }
                }
            }
        });

    }
}
