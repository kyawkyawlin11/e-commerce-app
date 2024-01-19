package com.visibleone.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.model.ItemSizeModel;

import java.util.List;

public class ItemSizeAdapter extends RecyclerView.Adapter<ItemSizeAdapter.PlaceHolder> {
    private Context con;
    private List<ItemSizeModel> sizeList;
    private CVItemClick cvItemClick;


    public ItemSizeAdapter(Context con, List<ItemSizeModel> sizeList) {
        this.con = con;
        this.sizeList = sizeList;
    }

    public void setUpdateList(List<ItemSizeModel> sizeList) {
        this.sizeList = sizeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlaceHolder(LayoutInflater.from(con).inflate(R.layout.custum_size_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceHolder h, final int i) {
        h.tvSize.setText(sizeList.get(i).getItemSize());
        if(sizeList.get(i).isStatus()){
            h.sizeLayout.setCardBackgroundColor(con.getColor(R.color.orange));
            h.tvSize.setTextColor(con.getColor(R.color.white));
        }else {
            h.sizeLayout.setCardBackgroundColor(con.getColor(R.color.card_color));
            h.tvSize.setTextColor(con.getColor(R.color.black));
        }
        h.sizeLayout.setOnClickListener(v -> cvItemClick.onClick(sizeList,h.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {

        TextView tvSize;
        CardView sizeLayout;

        public PlaceHolder(@NonNull final View itemView) {
            super(itemView);
            tvSize = itemView.findViewById(R.id.tvSize);
            sizeLayout = itemView.findViewById(R.id.sizeLayout);
        }
    }
    public interface CVItemClick{
        public void onClick(List<ItemSizeModel> list, int position);
    }
    public void setItemClickListener(CVItemClick cvItemClick){
        this.cvItemClick=cvItemClick;
    }

}
