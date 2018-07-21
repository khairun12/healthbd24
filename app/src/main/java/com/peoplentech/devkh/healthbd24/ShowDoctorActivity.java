package com.peoplentech.devkh.healthbd24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor);

        //added
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView name = (TextView) findViewById(R.id.textdesignation);
        final TextView area = (TextView) findViewById(R.id.textarea);
        final TextView email = (TextView) findViewById(R.id.email);
        final TextView chamName = (TextView) findViewById(R.id.chamName);
        final TextView chamAddress = (TextView) findViewById(R.id.textAddress);
        final TextView chamNumber = (TextView) findViewById(R.id.chamNumber);
        final TextView eduInstitute = (TextView) findViewById(R.id.edu_inst);
        final TextView degree = (TextView) findViewById(R.id.degree);
        final TextView instituteName = (TextView) findViewById(R.id.job_inst);
        final TextView designation = (TextView) findViewById(R.id.job_deg);

        if (bundle != null) {
            String doc_name = (String) bundle.get("doc_name");
            String doc_area = (String) bundle.get("doc_area");
            String doc_email = (String) bundle.get("doc_email");
            String doc_phone = (String) bundle.get("doc_phone");
            String doc_deg = (String) bundle.get("doc_deg");
            String cham_name = (String) bundle.get("cham_name");
            String cham_address = (String) bundle.get("cham_address");
            String edu_inst = (String) bundle.get("edu_inst");
            String job_inst = (String) bundle.get("job_inst");
            String job_des = (String) bundle.get("job_des");

            //set text value
            name.setText(doc_name);
            area.setText(doc_area);
            email.setText(doc_email);
            chamNumber.setText(doc_phone);
            degree.setText(doc_deg);
            chamName.setText(cham_name);
            chamAddress.setText(cham_address);
            eduInstitute.setText(edu_inst);
            instituteName.setText(job_inst);
            designation.setText(job_des);

            /*if (dmp_email != null && !dmp_email.isEmpty()) {
                email.setVisibility(View.VISIBLE);
                emailIcon.setVisibility(View.VISIBLE);
                email.setText(dmp_email);
            }
            if (dmp_phone != null && !dmp_phone.isEmpty()) {
                phone.setVisibility(View.VISIBLE);
                phoneIcon.setVisibility(View.VISIBLE);
                phone.setText(dmp_phone);
            }
            if (dmp_mobile != null && !dmp_mobile.isEmpty()) {
                mobile.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                mobileIcon.setVisibility(View.VISIBLE);
                mobile.setText(dmp_mobile);
            }*/


        }

        /*mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mobile.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                if (!TextUtils.isEmpty(mail)){
                    mailIntent.setData(Uri.parse("mailto:" + mail));
                    startActivity(mailIntent);
                }
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mobile.getText().toString();
                if (!TextUtils.isEmpty(number)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
                    startActivity(smsIntent);
                }
            }
        });*/

        /*chamNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = chamNumber.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                if (!TextUtils.isEmpty(number)) {
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                if (!TextUtils.isEmpty(mail)){
                    mailIntent.setData(Uri.parse("mailto:" + mail));
                    startActivity(mailIntent);
                }
            }
        });*/

    }
}