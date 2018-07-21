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
 * Created by User on 4/16/2018.
 */

public class BloodFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<BloodDonor> name;
    private RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private BloodDonorAdapter adapter;
    protected View mView;
    LinearLayout donorLayout;
    SwipeRefreshLayout refreshdonor;
    String WEB_URL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_appoint_doc, container, false);
        this.mView = view;
        initCustomSpinner();
        return view;*/

        return inflater.inflate(R.layout.fragment_blood, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initCustomSpinner();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        donorLayout = (LinearLayout)getView().findViewById(R.id.donorLayout);
        refreshdonor = (SwipeRefreshLayout)getView().findViewById(R.id.donorRefresh);
        refreshdonor.setOnRefreshListener(this);
        initCustomSpinner();

    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) getView().findViewById(R.id.donorSpinner);
        // Spinner Drop down elements
        ArrayList<String> areas = new ArrayList<String>();
        areas.add("Select Blood Group");
        areas.add("A+");
        areas.add("A-");
        areas.add("AB+");
        areas.add("AB-");
        areas.add("B+");
        areas.add("B-");
        areas.add("O+");
        areas.add("O-");

        BloodFragment.CustomSpinnerAdapter customSpinnerAdapter=new BloodFragment.CustomSpinnerAdapter(getActivity(),areas);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerviewDonor);
                name = new ArrayList<>();
                gridLayout = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(gridLayout);

                adapter = new BloodDonorAdapter(getActivity(), name);
                recyclerView.setAdapter(adapter);

                if (adapter.getItemCount() > 0) {

                    name.clear();
                    adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
                }

                String item = parent.getItemAtPosition(position).toString();
                if (position == 0) {

                    WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/allDonor.php?id=";
                    loadAllData(0);

                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                            if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                loadAllData(name.get(name.size() - 1).getDonorId());
                            }

                        }
                    });
                } else if (position > 0) {

                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    if(item.equals("A+")) {

                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/A_positive.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });

                    } else if (item.equals("A-")){
                        //Toast.makeText(parent.getContext(), "World", Toast.LENGTH_SHORT).show();
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/A_negative.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("B+")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/B_positive.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("B-")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/B_negative.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("O+")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/O_positive.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("O-")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/O_negative.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("AB+")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/AB_positive.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else if (item.equals("AB-")) {
                        WEB_URL = "http://10.16.20.41/healthbd/bloodUserSearch/AB_negative.php?id=";

                        getDataFromDB(0);

                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                    getDataFromDB(name.get(name.size() - 1).getDonorId());
                                }
                            }
                        });
                    } else {
                        Toast.makeText(parent.getContext(), "Please Select a Blood Type", Toast.LENGTH_SHORT).show();
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
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
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

                        BloodDonor doc = new BloodDonor(object.getInt("id"), object.getString("name"),
                                object.getString("address"), object.getString("number"),
                                object.getString("email"), object.getString("blood_group"));

                        BloodFragment.this.name.add(doc);
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
                refreshdonor.setRefreshing(false);
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

                        BloodDonor doc = new BloodDonor(object.getInt("id"), object.getString("name"),
                                object.getString("address"), object.getString("number"),
                                object.getString("email"), object.getString("blood_group"));

                        BloodFragment.this.name.add(doc);
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
                refreshdonor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
    @Override
    public void onRefresh() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED){

                //clear data
                name.clear();
                recyclerView.setAdapter(null);
                //adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
                //load
                loadAllData(0);
            } else {

                refreshdonor.setRefreshing(false);
                Snackbar snackbar = Snackbar.make(donorLayout, "No internet Connection", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

    }

}
