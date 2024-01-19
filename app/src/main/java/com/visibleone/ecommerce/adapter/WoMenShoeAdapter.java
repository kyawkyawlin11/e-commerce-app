package com.visibleone.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.model.ProductModel;

import java.util.List;

public class WoMenShoeAdapter extends RecyclerView.Adapter<WoMenShoeAdapter.PlaceHolder> {
    private Context con;
    private List<ProductModel> productList;
    private CVItemClick cvItemClick;
    private MenShoeAdapter.OnCartClick onCartClick;


    public WoMenShoeAdapter(Context con, List<ProductModel> productList) {
        this.con = con;
        this.productList = productList;
    }

    public void setUpdateList(List<ProductModel> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PlaceHolder( LayoutInflater.from(con).inflate( R.layout.shoe_item_view,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceHolder h, final int i) {
        final ProductModel model=productList.get(i);

        Glide.with(con)
                .load(model.getThumbnail())
                .centerCrop()
                .placeholder(R.drawable.ic_add_cart)
                .into(h.ivThumbnail);
        h.tvName.setText(model.getName());
        h.tvPrice.setText(model.getPrice());
        h.ivThumbnail.setOnClickListener(v -> cvItemClick.onClick(productList.get(h.getAdapterPosition())));
        h.btnAddCart.setOnClickListener(v -> onCartClick.onClick(productList.get(h.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName,tvPrice;
        ImageButton btnAddCart;
        public PlaceHolder(@NonNull final View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnAddCart = itemView.findViewById(R.id.btnAddCart);
        }
    }
    public interface CVItemClick{
        public void onClick(ProductModel productModel);
    }
    public interface OnCartClick{
        public void onClick(ProductModel productModel);
    }
    public void setItemClickListener(CVItemClick cvItemClick){
        this.cvItemClick=cvItemClick;
    }
    public void setOnCartClickListener(MenShoeAdapter.OnCartClick onCartClick){
        this.onCartClick=onCartClick;
    }
}
