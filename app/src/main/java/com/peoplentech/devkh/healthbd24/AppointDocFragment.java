package com.peoplentech.devkh.healthbd24;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 3/22/2018.
 */

public class AppointDocFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<Doctor> name;
    private RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private DoctorAdapter adapter;
    protected View mView;
    LinearLayout DoctorAppointLayout;
    SwipeRefreshLayout refreshdoctor;
    String WEB_URL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_appoint_doc, container, false);
        this.mView = view;
        initCustomSpinner();
        return view;*/

        return inflater.inflate(R.layout.fragment_appoint_doc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DoctorAppointLayout = (LinearLayout)getView().findViewById(R.id.doctorappointLayout);
        refreshdoctor = (SwipeRefreshLayout)getView().findViewById(R.id.doctorRefresh);
        refreshdoctor.setOnRefreshListener(this);
        initCustomSpinner();

    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) getView().findViewById(R.id.doctorSpinner);
        // Spinner Drop down elements
        ArrayList<String> areas = new ArrayList<String>();
        areas.add("Select Your Specialist");
        areas.add("Audiologist");
        areas.add("Allergist");
        areas.add("Anesthesiologist");
        areas.add("Cardiologist");
        areas.add("Dentist");
        areas.add("Darmatologist");
        areas.add("Endocrinologist");
        areas.add("Epidemiologist");
        areas.add("Gynecologist");
        areas.add("Immunologist");
        areas.add("Infectious Disease Specialist");
        areas.add("Internal Medicine Specialist");
        areas.add("Medical Geneticist");
        areas.add("Microbiologist");
        areas.add("Neurologist");
        areas.add("Neurosurgeon");
        areas.add("Obstetrician");
        areas.add("Oncologist");
        areas.add("Orthopedic Surgeon");
        areas.add("ENT Specialist");
        areas.add("Pediatrician");
        areas.add("Physiologist");
        areas.add("Plastic Surgeon");
        areas.add("Podiatrist");
        areas.add("Psychiatrist");
        areas.add("Radiologist");
        areas.add("Rheumatologist");
        areas.add("Surgeon");
        areas.add("Urologist");
        areas.add("Cancer Specialist");
        areas.add("Laparoscopic");
        areas.add("Diabetes Specialist");
        areas.add("Endodontics");
        areas.add("Cosmetic Dentistry");
        areas.add("Root Canal Specialist");
        areas.add("Eye (Ophthalmology");
        areas.add("Eye Surgeon - Contact Lens & Phaco");
        areas.add("Eye Cancer Specialist");
        areas.add("Eye Plastic Surgery");
        areas.add("Eye Specialist & Surgeon");
        areas.add("Micro Surgery Expert");
        areas.add("Child Health Specialist");
        areas.add("Pulmonology");
        areas.add("Kidney Disease Specialist");
        areas.add("Sonologist");
        areas.add("Endoscopic");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),areas);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
                name = new ArrayList<>();
                gridLayout = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(gridLayout);

                adapter = new DoctorAdapter(getActivity().getApplicationContext(), name);
                recyclerView.setAdapter(adapter);

                if (adapter.getItemCount() > 0) {

                    name.clear();
                    adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
                }

                String item = parent.getItemAtPosition(position).toString();
                if (position == 0) {

                    WEB_URL = "http://10.16.20.41/healthbd/data/all.php?id=";
                    loadAllData(0);

                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                            if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                loadAllData(name.get(name.size() - 1).getId());
                            }

                        }
                    });
                } else if (position > 0) {

                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    if(item.equals("Anesthesiologist")) {

                        WEB_URL = "http://10.16.20.41/healthbd/data/anesthesiologist.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getId());
                                }
                            }
                        });

                    } else if (item.equals("Internal Medicine Specialist")){
                        //Toast.makeText(parent.getContext(), "World", Toast.LENGTH_SHORT).show();
                        WEB_URL = "http://10.16.20.41/healthbd/data/internalmedicine.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getId());
                                }
                            }
                        });
                    } else if (item.equals("Physiologist")) {
                        WEB_URL = "http://10.16.20.41/healthbd/data/physiologist.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getId());
                                }
                            }
                        });
                    } else {
                        Toast.makeText(parent.getContext(), "World", Toast.LENGTH_SHORT).show();
                    }

                }
                int size = name.size();
                if (size > 0){
                    for (int i = 0; i < size; i++){
                        name.remove(0);
                    }
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, name.size());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(getContext().getApplicationContext());
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            if (position > 0) {
                txt.setTextColor(Color.parseColor("#000000"));
            } else {
                txt.setTextColor(Color.parseColor("#808080"));
            }
            return txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(getContext().getApplicationContext());
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(22);
            //txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop, 0);
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_person, 0, R.drawable.ic_dropdown, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#ffffff"));
            return txt;
        }

    }

    //Recyclerview method
    private void getDataFromDB(int id) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... designationIds) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(WEB_URL + designationIds[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Doctor doc = new Doctor(object.getInt("id"), object.getString("name"),
                                object.getString("email"), object.getString("contact"), object.getString("area_expert"), object.getString("job_institution") ,object.getString("job_designation"),object.getString("edu_institution"),object.getString("edu_degree"),object.getString("edu_country"),object.getString("chamber_name"),object.getString("chamber_address"),object.getString("appoint_number"));

                        AppointDocFragment.this.name.add(doc);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                refreshdoctor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }

    //Recyclerview method
    private void loadAllData(int id) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... designationIds) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(WEB_URL + designationIds[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        Doctor doc = new Doctor(object.getInt("id"), object.getString("name"),
                                object.getString("email"), object.getString("contact"), object.getString("area_expert"), object.getString("job_institution") ,object.getString("job_designation"),object.getString("edu_institution"),object.getString("edu_degree"),object.getString("edu_country"),object.getString("chamber_name"),object.getString("chamber_address"),object.getString("appoint_number"));

                        AppointDocFragment.this.name.add(doc);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                refreshdoctor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
    @Override
    public void onRefresh() {
        ConnectivityManager conntivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED ||
                conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED){

            //clear data
            name.clear();
            recyclerView.setAdapter(null);
            //adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
            //load
            loadAllData(0);
        } else {

            refreshdoctor.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(DoctorAppointLayout, "No internet Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

}
