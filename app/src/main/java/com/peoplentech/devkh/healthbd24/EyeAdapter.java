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
 * Created by User on 4/15/2018.
 */

public class EyeAdapter extends RecyclerView.Adapter<EyeAdapter.ViewHolder> {

    private Context context;
    private List<Eye> eye;

    public EyeAdapter(Context context, List<Eye> eye) {
        this.context = context;
        this.eye = eye;
    }

    @Override
    public EyeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_eye,parent,false);

        return new EyeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EyeAdapter.ViewHolder holder, int position) {

        holder.eyeName.setText(eye.get(position).getEyeName());
        holder.eyeAddress.setText(eye.get(position).getEyeAddress());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.eyeNumber.setText(eye.get(position).getEyeNumber().trim());
        //holder.eyeWebsite.setText(eye.get(position).getEyeWebsite());

    }



    @Override
    public int getItemCount() {
        return eye.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView eyeName;
        public TextView eyeAddress;
        public TextView eyeNumber;
        public TextView callEyeBank;
        public TextView eyeWebsite;




        public ViewHolder(View itemView) {
            super(itemView);
            // hospitalWebsite = (TextView) itemView.findViewById(R.id.Hospitalwebsite);
            eyeNumber = (TextView) itemView.findViewById(R.id.eyeNumber);
            eyeName = (TextView) itemView.findViewById(R.id.eyeName);
            eyeAddress = (TextView) itemView.findViewById(R.id.eyeAddress);
            callEyeBank = (TextView) itemView.findViewById(R.id.eyeCall);
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
            callEyeBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = eyeNumber.getText().toString();
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