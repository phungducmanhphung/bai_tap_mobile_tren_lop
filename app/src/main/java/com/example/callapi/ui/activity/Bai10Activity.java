package com.example.callapi.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.callapi.R;
import com.example.callapi.adapter.CustomeAdapterUser;
import com.example.callapi.data.model.User;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bai10Activity extends AppCompatActivity {
    ListView lvDanhSachUser;
    SearchView svTimKiem;
    List<User> lvData = new ArrayList<>();
    CustomeAdapterUser lvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai10);
        
        setControl();
        setEvent();
    }
    private void setEvent() {
        docDuLieu();

        lvAdapter = new CustomeAdapterUser(Bai10Activity.this, R.layout.layout_item_user, lvData);
        lvDanhSachUser.setAdapter(lvAdapter);
    }
    private void setControl() {
        lvDanhSachUser = findViewById(R.id.lvDanhSachUser);
        svTimKiem = findViewById(R.id.svTimKiem);
    }
    private void docDuLieu(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/users";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null
                ,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject item = response.getJSONObject(i);
                                User user = new User();
                                user.setLogin(item.getString("login"));
                                user.setUrl(item.getString("url"));
                                user.setAvatar_url(item.getString("avatar_url"));
                                lvData.add(user);
                                Log.d("MY TAG", "onResponse: " + lvData.size());
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        lvAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Bai10Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(request);
    }
}