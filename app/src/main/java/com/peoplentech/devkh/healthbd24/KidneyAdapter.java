package com.peoplentech.devkh.healthbd24;

/**
 * Created by User on 4/16/2018.
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
public class KidneyAdapter extends RecyclerView.Adapter<KidneyAdapter.ViewHolder> {

    private Context context;
    private List<Kidney> kidney;

    public KidneyAdapter(Context context, List<Kidney> kidney) {
        this.context = context;
        this.kidney = kidney;
    }

    @Override
    public KidneyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kidney,parent,false);

        return new KidneyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final KidneyAdapter.ViewHolder holder, int position) {

        holder.kidneyName.setText(kidney.get(position).getKidneyName());
        holder.kidneyAddress.setText(kidney.get(position).getKidneyAddress());
        //Glide.with(context).load(movies.get(position).getImageLink()).into(holder.email);
        holder.kidneyNumber.setText(kidney.get(position).getKidneyNumber().trim());
        holder.kidneyWebsite.setText(kidney.get(position).getKidneyWebsite());

    }



    @Override
    public int getItemCount() {
        return kidney.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView kidneyName;
        public TextView kidneyAddress;
        public TextView kidneyNumber;
        public TextView callkidneyBank;
        public TextView kidneyWebsite;




        public ViewHolder(View itemView) {
            super(itemView);
            kidneyWebsite = (TextView) itemView.findViewById(R.id.kidneyWebsite);
            kidneyNumber = (TextView) itemView.findViewById(R.id.kidneyNumber);
            kidneyName = (TextView) itemView.findViewById(R.id.kidneyName);
            kidneyAddress = (TextView) itemView.findViewById(R.id.kidneyAddress);
            callkidneyBank = (TextView) itemView.findViewById(R.id.kidneyCall);

            callkidneyBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = kidneyNumber.getText().toString();
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
