package com.beecoder.madesubmission3.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beecoder.madesubmission3.R;
import com.beecoder.madesubmission3.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvViewModel extends AndroidViewModel {

    RequestQueue mRequestQueue;
    private MutableLiveData<ArrayList<Item>> item = new MutableLiveData<>();
    String url;
    public ArrayList<Item> itemArrayList = new ArrayList<>();
    public TvViewModel(@NonNull Application application) {
        super(application);
        mRequestQueue = Volley.newRequestQueue(application);
        url = application.getResources().getString(R.string.api_tv);
    }

    public void getData(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("name");
                        String photo = result.getString("poster_path");
                        String description = result.getString("overview");
                        Item mItem = new Item(title,photo,description);

                        itemArrayList.add(mItem);
                    }

                    item.postValue(itemArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
    public LiveData<ArrayList<Item>> tv(){
        return item;
    }
}
