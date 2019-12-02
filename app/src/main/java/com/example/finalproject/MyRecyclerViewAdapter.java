package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Flight> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private boolean isOffline = false;

    MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Flight> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void isOffline(boolean isOffline)
    {
        this.isOffline = isOffline;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_flight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(mData.get(position).hour);
        holder.tvNumber.setText(mData.get(position).flynumber);
        holder.tvAirport.setText(mData.get(position).airport);
        holder.tvCity.setText(mData.get(position).city);
        if (isLanded(mData.get(position).hour)) {
            //holder.isLanded.setBackgroundResource(R.color.colorAccent);
            holder.ifLanded.setText("Landed");
        } else {
            holder.ifLanded.setText("");
        }

        switch (mData.get(position).logo) {
            case "BGA":
                holder.imageView.setImageResource(R.drawable.elal);
                break;
            case "BA":
                holder.imageView.setImageResource(R.drawable.british);
                break;
            case "TTI":
                holder.imageView.setImageResource(R.drawable.turkish);
                break;
            case "SAB":
                holder.imageView.setImageResource(R.drawable.swiss);
                break;
        }

        if (isOffline) {
            holder.itemView.setAlpha(0.75f);
            holder.itemView.setEnabled(false);
        }
        else
        {
            holder.itemView.setAlpha(1f);
            holder.itemView.setEnabled(true);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private boolean isLanded(String hour){
        String currentHour = new SimpleDateFormat("hh:mm")
                                .format(Calendar.getInstance().getTime());
        try{
            if(getNumber(currentHour.substring(0,1))>getNumber(hour.substring(0,1)))
                return true;
            else if(getNumber(currentHour.substring(0,1))<getNumber(hour.substring(0,1)))
                return false;
            else {
                if(getNumber(currentHour.substring(3,4))>=getNumber(hour.substring(3,4)))
                    return true;
                else if(getNumber(currentHour.substring(3,4))<getNumber(hour.substring(3,4)))
                    return false;
            }


        }
        catch (Exception e){

        }

        return true;
    }

    private int getNumber(String number){
        if(Integer.parseInt(""+number.charAt(0)) == 0) {
            return Integer.parseInt(""+number.charAt(1));
        }
        else {
            return Integer.parseInt(number);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView tvNumber;
        TextView tvAirport;
        TextView tvCity;
        ImageView imageView;
        TextView ifLanded;

        ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.hourTV);
            tvNumber = itemView.findViewById(R.id.numberTV);
            tvAirport = itemView.findViewById(R.id.airportTV);
            tvCity = itemView.findViewById(R.id.cityTV);
            imageView = itemView.findViewById(R.id.imageIV);
            ifLanded = itemView.findViewById(R.id.ifLand);
        }

    }


}

