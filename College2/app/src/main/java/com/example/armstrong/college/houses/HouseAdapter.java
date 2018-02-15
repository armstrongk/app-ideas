package com.example.armstrong.college.houses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.armstrong.college.apartments.ApartmentModel;
import com.example.armstrong.college.R;

import java.util.List;

/**
 * Created by Franc on 12/3/2016.
 */

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.MyViewHolder> {

    private Context mContext;
    private List<HouseModel> houseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView house, meterNum, status;

        public MyViewHolder(View view) {
            super(view);
            house = (TextView) view.findViewById(R.id.house);
            meterNum = (TextView) view.findViewById(R.id.meterNum);
            status = (TextView) view.findViewById(R.id.status);
        }
    }

    public HouseAdapter(Context mContext, List<HouseModel> houseList) {
        this.mContext = mContext;
        this.houseList = houseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HouseModel hse = houseList.get(position);
        holder.house.setText(hse.getHouseNum());
        holder.meterNum.setText(hse.getMeterNum());
        holder.status.setText("");
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

}