package com.peoplentech.devkh.healthbd24;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by User on 3/25/2018.
 */

public class HospitalAdapter  extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private Context context;
    private List<Hospital> hospital;

    public HospitalAdapter(Context context, List<Hospital> hospital) {
        this.context = context;
        this.hospital = hospital;
    }

    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hospital,parent,false);

        return new HospitalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final HospitalAdapter.ViewHolder holder, int position) {

        holder.hospitalName.setText(hospital.get(position).getHospitalName());
        holder.hospitalAddress.setText(hospital.get(position).getHospitalAddress());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.hospitalNumber.setText(hospital.get(position).getHospitalNumber().trim());
        //if (hospital.get(position).getHospitalWebsite() != null) {
        holder.hospitalWebsite.setText(hospital.get(position).getHospitalWebsite());
        //}
    }



    @Override
    public int getItemCount() {
        return hospital.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView hospitalName;
        public TextView hospitalNumber;
        public TextView hospitalAddress;
        public TextView hospitalWebsite;
        public TextView callhospital;



        public ViewHolder(View itemView) {
            super(itemView);
            hospitalWebsite = (TextView) itemView.findViewById(R.id.Hospitalwebsite);
            hospitalNumber = (TextView) itemView.findViewById(R.id.Hospitalnumber);
            hospitalName = (TextView) itemView.findViewById(R.id.hospitalName);
            hospitalAddress = (TextView) itemView.findViewById(R.id.Hospitalarea);
            callhospital = (TextView) itemView.findViewById(R.id.hospitalCall);
            /*callhospital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context.getApplicationContext(), ShowDoctorActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("doc_name", hospital.get(position).getName());
                    intent.putExtra("doc_area", doctor.get(position).getArea());
                    intent.putExtra("doc_email", doctor.get(position).getEmail());
                    intent.putExtra("doc_phone", doctor.get(position).getChamberNumber());
                    intent.putExtra("doc_deg", doctor.get(position).getEduDegree());
                    intent.putExtra("cham_name", doctor.get(position).getChamberName());
                    intent.putExtra("cham_address", doctor.get(position).getChamberAddress());
                    intent.putExtra("edu_inst", doctor.get(position).getEduInstitute());
                    intent.putExtra("job_inst", doctor.get(position).getJobInstitute());
                    intent.putExtra("job_des", doctor.get(position).getJobDegree());
                    context.getApplicationContext().startActivity(intent);
                }
            });*/
            callhospital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = hospitalNumber.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    if (!TextUtils.isEmpty(phone)) {
                        callIntent.setData(Uri.parse("tel:" + phone));
                        context.getApplicationContext().startActivity(callIntent);
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


    }

}