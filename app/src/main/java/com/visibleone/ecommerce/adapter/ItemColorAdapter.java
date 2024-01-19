package com.visibleone.ecommerce.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.model.ItemColorModel;

import java.util.List;

public class ItemColorAdapter extends RecyclerView.Adapter<ItemColorAdapter.PlaceHolder> {
    private Context con;
    private List<ItemColorModel> colorList;
    private CVItemClick cvItemClick;


    public ItemColorAdapter(Context con, List<ItemColorModel> colorList) {
        this.con = con;
        this.colorList = colorList;
    }

    public void setUpdateList(List<ItemColorModel> colorList) {
        this.colorList = colorList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlaceHolder(LayoutInflater.from(con).inflate(R.layout.custum_color_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceHolder h, final int i) {

        h.colorLayout.setCardBackgroundColor(Color.parseColor(colorList.get(i).getItemColor()));
        if(colorList.get(i).isStatus()){
            h.ivSelect.setVisibility(View.VISIBLE);
        }else {
            h.ivSelect.setVisibility(View.INVISIBLE);
        }
        h.colorLayout.setOnClickListener(v -> cvItemClick.onClick(colorList,h.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {

        CardView colorLayout;
        ImageView ivSelect;

        public PlaceHolder(@NonNull final View itemView) {
            super(itemView);
            colorLayout = itemView.findViewById(R.id.colorLayout);
            ivSelect = itemView.findViewById(R.id.ivSelect);
        }
    }
    public interface CVItemClick{
        public void onClick(List<ItemColorModel> list, int position);
    }
    public void setItemClickListener(CVItemClick cvItemClick){
        this.cvItemClick=cvItemClick;
    }

}
