package com.peoplentech.devkh.healthbd24;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

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
 * Created by User on 3/27/2018.
 */

public class PharmacyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private List<Pharmacy> name;
    private RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private PharmacyAdapter adapter;
    protected View mView;
    LinearLayout PharmaLayout;
    SwipeRefreshLayout refreshpharma;
    String WEB_URL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_appoint_doc, container, false);
        this.mView = view;
        initCustomSpinner();
        return view;*/

        return inflater.inflate(R.layout.fragment_pharmacy, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PharmaLayout = (LinearLayout)getView().findViewById(R.id.pharmaLayout);
        refreshpharma = (SwipeRefreshLayout)getView().findViewById(R.id.pharmaRefresh);
        refreshpharma.setOnRefreshListener(this);
        //initCustomSpinner();
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerviewPharmacy);
        name = new ArrayList<>();
        gridLayout = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(gridLayout);

        adapter = new PharmacyAdapter(getActivity().getApplicationContext(), name);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() > 0) {

            name.clear();
            adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
        }

        WEB_URL = "http://10.16.20.41/healthbd/pharmacy/pharmacy.php?id=";

        loadAllData(0);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                    loadAllData(name.get(name.size() - 1).getPharmacyId());
                }

            }
        });
        EditText search = (EditText) getView().findViewById(R.id.search_pharmacy);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchPharmacyActivity.class);
                startActivity(intent);
            }
        });

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

                        Pharmacy pharmacy = new Pharmacy(object.getInt("id"), object.getString("name"),
                                object.getString("number"), object.getString("address"));

                        PharmacyFragment.this.name.add(pharmacy);
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
                refreshpharma.setRefreshing(false);
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

            refreshpharma.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(PharmaLayout, "No internet Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }


    }
}
