package com.peoplentech.devkh.healthbd24;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by User on 4/16/2018.
 */

public class BloodDonorAdapter extends RecyclerView.Adapter<BloodDonorAdapter.ViewHolder> {

    private Context context;
    private List<BloodDonor> donor;
    SessionManager session;

    public BloodDonorAdapter(Context context, List<BloodDonor> donor) {
        this.context = context;
        this.donor = donor;
    }

    @Override
    public BloodDonorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_donor,parent,false);

        return new BloodDonorAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BloodDonorAdapter.ViewHolder holder, int position) {

        holder.name.setText(donor.get(position).getDonorName());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.email.setText(donor.get(position).getDonorEmail());
        holder.area.setText(donor.get(position).getDonorArea());
        holder.chamNumber.setText(donor.get(position).getDonorContact());
        holder.bloodType.setText(donor.get(position).getBloodType());
    }



    @Override
    public int getItemCount() {
        return donor.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView email;
        public TextView name;
        public TextView area;
        public TextView chamNumber;
        public TextView bloodType;
        public TextView callDonor;
        public TextView smsDonor;


        public ViewHolder(View itemView) {
            super(itemView);
            // Session manager
            session = new SessionManager(context.getApplicationContext());
            email = (TextView) itemView.findViewById(R.id.donorEmail);
            name = (TextView) itemView.findViewById(R.id.donorName);
            area = (TextView) itemView.findViewById(R.id.donorArea);
            chamNumber = (TextView) itemView.findViewById(R.id.donorNumber);
            bloodType = (TextView) itemView.findViewById(R.id.bloodType);
            callDonor = (TextView) itemView.findViewById(R.id.callDonor);
            smsDonor = (TextView) itemView.findViewById(R.id.smsDonor);

            name.setOnClickListener(this);
            callDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (session.isLoggedIn()) {

                        String phone = chamNumber.getText().toString();
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        if (!TextUtils.isEmpty(phone)) {
                            callIntent.setData(Uri.parse("tel:" + phone));
                            context.getApplicationContext().startActivity(callIntent);
                        }
                    } else {

                        FragmentManager fragmentManager = ((AppCompatActivity) context).getFragmentManager();
                        LoginFragment login = new LoginFragment();
                        if (fragmentManager != null) {
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            if (ft != null) {
                                ft.replace(R.id.root, login).addToBackStack("fragment_login").commit();
                                Toast.makeText(context.getApplicationContext(), "You need to Login for this service", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
            });

            smsDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (session.isLoggedIn()) {
                        String number = chamNumber.getText().toString();
                        if (!TextUtils.isEmpty(number)) {
                            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
                            smsIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            context.getApplicationContext().startActivity(smsIntent);
                        }
                    } else {

                        /*Fragment fragment;
                        fragment = new LoginFragment();
                        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.root, fragment).commit();*/
                        FragmentManager fragmentManager = ((AppCompatActivity) context).getFragmentManager();
                        LoginFragment login = new LoginFragment();
                        if (fragmentManager != null) {
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            if (ft != null) {
                                ft.replace(R.id.root, login).addToBackStack("fragment_login").commit();
                                Toast.makeText(context.getApplicationContext(), "You need to Login for this service", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);

        }
    }

    private void showPopupMenu(View view, int position) {

        /*Intent intent = new Intent(context.getApplicationContext(), ShowDoctorActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("doc_name", doctor.get(position).getName());
        intent.putExtra("doc_area", doctor.get(position).getArea());
        intent.putExtra("doc_email", doctor.get(position).getEmail());
        intent.putExtra("doc_phone", doctor.get(position).getChamberNumber());
        intent.putExtra("doc_deg", doctor.get(position).getEduDegree());
        intent.putExtra("cham_name", doctor.get(position).getChamberName());
        intent.putExtra("cham_address", doctor.get(position).getChamberAddress());
        intent.putExtra("edu_inst", doctor.get(position).getEduInstitute());
        intent.putExtra("job_inst", doctor.get(position).getJobInstitute());
        intent.putExtra("job_des", doctor.get(position).getJobDegree());
        context.getApplicationContext().startActivity(intent);*/
    }

}