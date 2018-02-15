package com.example.armstrong.college.transactions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.armstrong.college.R;
import com.example.armstrong.college.houses.HouseModel;

import java.util.List;

/**
 * Created by Franc on 12/3/2016.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private Context mContext;
    private List<TransactionModel> transactionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, amount;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            amount = (TextView) view.findViewById(R.id.amount);
        }
    }

    public TransactionAdapter(Context mContext, List<TransactionModel> transactionList) {
        this.mContext = mContext;
        this.transactionList = transactionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TransactionModel transaction = transactionList.get(position);
        holder.date.setText(transaction.getDate());
        holder.amount.setText(transaction.getAmount());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

}