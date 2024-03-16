package com.example.callapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.callapi.R;
import com.example.callapi.data.model.User;

import java.util.List;

public class CustomeAdapterUser extends ArrayAdapter {
    ImageView imvAvatar;
    TextView tvLogin, tvUrl;
    Context context;
    int resource;
    List<User> data;
    View view;
    User currentUser;
    public CustomeAdapterUser(@NonNull Context context, int resource, @NonNull List<User> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(resource, null);
        currentUser = data.get(position);
        setControl();
        setEvent();
        return view;
    }

    private void setEvent() {
        tvLogin.setText(currentUser.getLogin());
        tvUrl.setText(currentUser.getUrl());
    }

    private void setControl() {
        imvAvatar = view.findViewById(R.id.imvAvatar);
        tvLogin = view.findViewById(R.id.tvLogin);
        tvUrl = view.findViewById(R.id.tvUrl);
    }
}
