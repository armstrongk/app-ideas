package com.example.armstrong.college.apartments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.armstrong.college.R;
import java.util.List;


public class ApartmentsAdapter extends RecyclerView.Adapter<ApartmentsAdapter.MyViewHolder> {

    private Context mContext;
    private List<ApartmentModel> apartmentList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView apartName, location, numOfHouses;
        public ImageView postIcon;

        public MyViewHolder(View view) {
            super(view);
            apartName = (TextView) view.findViewById(R.id.apartName);
            location = (TextView) view.findViewById(R.id.apartLocation);
            numOfHouses = (TextView) view.findViewById(R.id.hNum);
            postIcon = (ImageView) view.findViewById(R.id.imge);
        }
    }

    public ApartmentsAdapter(Context mContext, List<ApartmentModel> apartmentList) {
        this.mContext = mContext;
        this.apartmentList = apartmentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.apartment_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ApartmentModel apartment = apartmentList.get(position);
        holder.apartName.setText(apartment.getApartmentName());
        holder.location.setText(apartment.getLocation());
        if (apartment.getNo_Of_Houses() == 0)
            holder.numOfHouses.setText("No House or Room Attached");
        else if (apartment.getNo_Of_Houses() == 1)
            holder.numOfHouses.setText("1 House Attached");
        else
            holder.numOfHouses.setText(apartment.getNo_Of_Houses()+" Houses Attached");

        if(apartment.getApartmentName().startsWith("a") || apartment.getApartmentName().startsWith("A"))
            holder.postIcon.setImageResource(R.mipmap.a);
        else if (apartment.getApartmentName().startsWith("b") || apartment.getApartmentName().startsWith("B"))
            holder.postIcon.setImageResource(R.mipmap.b);
        else if (apartment.getApartmentName().startsWith("c") || apartment.getApartmentName().startsWith("C"))
            holder.postIcon.setImageResource(R.mipmap.c);
        else if (apartment.getApartmentName().startsWith("d") || apartment.getApartmentName().startsWith("D"))
            holder.postIcon.setImageResource(R.mipmap.d);
        else if (apartment.getApartmentName().startsWith("e") || apartment.getApartmentName().startsWith("E"))
            holder.postIcon.setImageResource(R.mipmap.e);
        else if (apartment.getApartmentName().startsWith("f") || apartment.getApartmentName().startsWith("F"))
            holder.postIcon.setImageResource(R.mipmap.f);
        else if (apartment.getApartmentName().startsWith("g") || apartment.getApartmentName().startsWith("G"))
            holder.postIcon.setImageResource(R.mipmap.g);
        else if (apartment.getApartmentName().startsWith("h") || apartment.getApartmentName().startsWith("H"))
            holder.postIcon.setImageResource(R.mipmap.h);
        else if (apartment.getApartmentName().startsWith("i") || apartment.getApartmentName().startsWith("I"))
            holder.postIcon.setImageResource(R.mipmap.i);
        else if (apartment.getApartmentName().startsWith("j") || apartment.getApartmentName().startsWith("J"))
            holder.postIcon.setImageResource(R.mipmap.j);
        else if (apartment.getApartmentName().startsWith("k") || apartment.getApartmentName().startsWith("K"))
            holder.postIcon.setImageResource(R.mipmap.k);
        else if (apartment.getApartmentName().startsWith("l") || apartment.getApartmentName().startsWith("L"))
            holder.postIcon.setImageResource(R.mipmap.l);
        else if (apartment.getApartmentName().startsWith("m") || apartment.getApartmentName().startsWith("M"))
            holder.postIcon.setImageResource(R.mipmap.m);
        else if (apartment.getApartmentName().startsWith("n") || apartment.getApartmentName().startsWith("N"))
            holder.postIcon.setImageResource(R.mipmap.n);
        else if (apartment.getApartmentName().startsWith("o") || apartment.getApartmentName().startsWith("O"))
            holder.postIcon.setImageResource(R.mipmap.o);
        else if (apartment.getApartmentName().startsWith("p") || apartment.getApartmentName().startsWith("P"))
            holder.postIcon.setImageResource(R.mipmap.p);
        else if (apartment.getApartmentName().startsWith("q") || apartment.getApartmentName().startsWith("Q"))
            holder.postIcon.setImageResource(R.mipmap.q);
        else if (apartment.getApartmentName().startsWith("r") || apartment.getApartmentName().startsWith("R"))
            holder.postIcon.setImageResource(R.mipmap.r);
        else if (apartment.getApartmentName().startsWith("s") || apartment.getApartmentName().startsWith("S"))
            holder.postIcon.setImageResource(R.mipmap.s);
        else if (apartment.getApartmentName().startsWith("t") || apartment.getApartmentName().startsWith("T"))
            holder.postIcon.setImageResource(R.mipmap.t);
        else if (apartment.getApartmentName().startsWith("u") || apartment.getApartmentName().startsWith("U"))
            holder.postIcon.setImageResource(R.mipmap.u);
        else if (apartment.getApartmentName().startsWith("v") || apartment.getApartmentName().startsWith("V"))
            holder.postIcon.setImageResource(R.mipmap.v);
        else if (apartment.getApartmentName().startsWith("w") || apartment.getApartmentName().startsWith("W"))
            holder.postIcon.setImageResource(R.mipmap.w);
        else if (apartment.getApartmentName().startsWith("x") || apartment.getApartmentName().startsWith("X"))
            holder.postIcon.setImageResource(R.mipmap.x);
        else if (apartment.getApartmentName().startsWith("y") || apartment.getApartmentName().startsWith("Y"))
            holder.postIcon.setImageResource(R.mipmap.y);
        else if (apartment.getApartmentName().startsWith("z") || apartment.getApartmentName().startsWith("Z"))
            holder.postIcon.setImageResource(R.mipmap.z);
        else
            holder.postIcon.setImageResource(R.mipmap.a);
    }

    @Override
    public int getItemCount() {
        return apartmentList.size();
    }

}