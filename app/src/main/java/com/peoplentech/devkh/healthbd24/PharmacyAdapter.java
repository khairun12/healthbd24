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
 * Created by User on 3/27/2018.
 */

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

    private Context context;
    private List<Pharmacy> pharmacy;

    public PharmacyAdapter(Context context, List<Pharmacy> pharmacy) {
        this.context = context;
        this.pharmacy = pharmacy;
    }

    @Override
    public PharmacyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pharmacy,parent,false);

        return new PharmacyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PharmacyAdapter.ViewHolder holder, int position) {

        holder.pharmacyName.setText(pharmacy.get(position).getPharmacyName());
        holder.pharmacyAddress.setText(pharmacy.get(position).getPharmacyAddress());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.pharmacyNumber.setText(pharmacy.get(position).getPharmacyNumber().trim());
        //if (hospital.get(position).getHospitalWebsite() != null) {
        //holder.hospitalWebsite.setText(hospital.get(position).getHospitalWebsite());
        //}
    }



    @Override
    public int getItemCount() {
        return pharmacy.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView pharmacyName;
        public TextView pharmacyAddress;
        public TextView pharmacyNumber;
        public TextView callPharmacy;



        public ViewHolder(View itemView) {
            super(itemView);
            // hospitalWebsite = (TextView) itemView.findViewById(R.id.Hospitalwebsite);
            pharmacyNumber = (TextView) itemView.findViewById(R.id.pharmacynumber);
            pharmacyName = (TextView) itemView.findViewById(R.id.pharmacyName);
            pharmacyAddress = (TextView) itemView.findViewById(R.id.pharmacyarea);
            callPharmacy = (TextView) itemView.findViewById(R.id.pharmacyCall);
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
            callPharmacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = pharmacyAddress.getText().toString();
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