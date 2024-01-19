package com.visibleone.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.databinding.BrandItemViewBinding;
import com.visibleone.ecommerce.databinding.ShoeItemViewBinding;
import com.visibleone.ecommerce.model.ProductModel;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.PlaceHolder> {
    private Context con;
    private List<ProductModel> productList;
    private CVItemClick cvItemClick;


    public BrandAdapter(Context con, List<ProductModel> productList) {
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
        return new PlaceHolder(LayoutInflater.from(con).inflate(R.layout.brand_item_view,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaceHolder h, final int i) {
        final ProductModel model=productList.get(i);

        Glide.with(con)
                .load(model.getBrand_logo())
                .centerCrop()
                .placeholder(R.drawable.ic_add_cart)
                .into(h.ivBrand)
        ;

        h.ivBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvItemClick.onClick(productList.get(h.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {

        ImageView ivBrand;

        public PlaceHolder(@NonNull final View itemView) {
            super(itemView);
            ivBrand = itemView.findViewById(R.id.ivBrand);
        }
    }
    public interface CVItemClick{
        public void onClick(ProductModel productModel);
    }
    public void setItemClickListener(CVItemClick cvItemClick){
        this.cvItemClick=cvItemClick;
    }

}
