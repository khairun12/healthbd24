package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 3/22/2018.
 */
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
 * Created by User on 3/12/2018.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context context;
    private List<Doctor> doctor;

    public DoctorAdapter(Context context, List<Doctor> doctor) {
        this.context = context;
        this.doctor = doctor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.name.setText(doctor.get(position).getName());
        holder.chamName.setText(doctor.get(position).getChamberName());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.email.setText(doctor.get(position).getEmail());
        holder.area.setText(doctor.get(position).getArea());
        holder.chamNumber.setText(doctor.get(position).getChamberNumber());
    }



    @Override
    public int getItemCount() {
        return doctor.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView email;
        public TextView name;
        public TextView chamName;
        public TextView area;
        public TextView chamNumber;
        public TextView details;
        public TextView apponit_doc;


        public ViewHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.uEmail);
            name = (TextView) itemView.findViewById(R.id.name);
            chamName = (TextView) itemView.findViewById(R.id.chamName);
            area = (TextView) itemView.findViewById(R.id.area);
            chamNumber = (TextView) itemView.findViewById(R.id.uChamNumber);
            details = (TextView) itemView.findViewById(R.id.details);
            apponit_doc = (TextView) itemView.findViewById(R.id.appointment);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context.getApplicationContext(), ShowDoctorActivity.class);
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
                    context.getApplicationContext().startActivity(intent);
                }
            });
            name.setOnClickListener(this);
            apponit_doc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail = email.getText().toString();
                    Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                    mailIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    if (!TextUtils.isEmpty(mail)){
                        mailIntent.setData(Uri.parse("mailto:" + mail));
                        context.getApplicationContext().startActivity(mailIntent);
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