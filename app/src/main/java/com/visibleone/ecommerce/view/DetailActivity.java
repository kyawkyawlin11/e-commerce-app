package com.visibleone.ecommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.adapter.ImageAdapter;
import com.visibleone.ecommerce.adapter.ItemColorAdapter;
import com.visibleone.ecommerce.adapter.ItemSizeAdapter;
import com.visibleone.ecommerce.databinding.ActivityDetailBinding;
import com.visibleone.ecommerce.model.ItemColorModel;
import com.visibleone.ecommerce.model.ItemSizeModel;
import com.visibleone.ecommerce.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    Integer quantity = 1;
    String type = "us";
    String size = "0.0";
    String color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProductModel productModel = (ProductModel) getIntent().getExtras().getSerializable("detail");
        ImageAdapter adapter = new ImageAdapter(this, productModel.getImages());
        binding.rvCarousel.setAdapter(adapter);

        binding.tvRating.setText(productModel.getRating());
        binding.tvName.setText(productModel.getName());
        binding.tvPrice.setText(productModel.getPrice());
        binding.tvDescription.setText(productModel.getDescription());
        binding.tvQuantity.setText(quantity.toString());

        List<ItemSizeModel> usSizeList = new ArrayList<>(0);
        List<ItemSizeModel> ukSizeList = new ArrayList<>(0);
        List<ItemSizeModel> euSizeList = new ArrayList<>(0);
        List<ItemColorModel> colorList = new ArrayList<>(0);
        productModel.getUsSize().forEach((e)->{
            usSizeList.add(new ItemSizeModel(false,e));
        });
        productModel.getUkSize().forEach((e)->{
            ukSizeList.add(new ItemSizeModel(false,e));
        });
        productModel.getEuSize().forEach((e)->{
            euSizeList.add(new ItemSizeModel(false,e));
        });

        productModel.getColorList().forEach((e)->{
            colorList.add(new ItemColorModel(false,e));
        });
        binding.rvSize.setHasFixedSize(true);
        binding.rvSize.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ItemSizeAdapter itemSizeAdapter = new ItemSizeAdapter(this,usSizeList);
        binding.rvSize.setAdapter(itemSizeAdapter);

        binding.rvColor.setHasFixedSize(true);
        binding.rvColor.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ItemColorAdapter colorAdapter = new ItemColorAdapter(this,colorList);
        binding.rvColor.setAdapter(colorAdapter);

        itemSizeAdapter.setItemClickListener((List<ItemSizeModel> list,int position)->{
            if(!list.get(position).isStatus()){
                for(int i =0; i<list.size();i++){
                    list.get(i).setStatus(false);
                }
                list.get(position).setStatus(true);
                size = list.get(position).getItemSize();
            }
            itemSizeAdapter.setUpdateList(list);
        });

        colorAdapter.setItemClickListener((List<ItemColorModel> list, int position)->{
            if(!list.get(position).isStatus()){
                for(int i =0; i<list.size();i++){
                    list.get(i).setStatus(false);
                }
                list.get(position).setStatus(true);
                color = list.get(position).getItemColor();
            }
            colorAdapter.setUpdateList(list);
        });
        binding.tvUsSize.setTextColor(getColor(R.color.orange));
        binding.tvUsSize.setOnClickListener((view -> {
            type = "us";
            binding.tvUsSize.setTextColor(getColor(R.color.orange));
            binding.tvUkSize.setTextColor(getColor(R.color.black));
            binding.tvEuSize.setTextColor(getColor(R.color.black));
            itemSizeAdapter.setUpdateList(usSizeList);
        }));

        binding.tvUkSize.setOnClickListener((view -> {
            type = "uk";
            binding.tvUkSize.setTextColor(getColor(R.color.orange));
            binding.tvUsSize.setTextColor(getColor(R.color.black));
            binding.tvEuSize.setTextColor(getColor(R.color.black));
            itemSizeAdapter.setUpdateList(ukSizeList);
        }));

        binding.tvEuSize.setOnClickListener((view -> {
            type = "eu";
            binding.tvEuSize.setTextColor(getColor(R.color.orange));
            binding.tvUkSize.setTextColor(getColor(R.color.black));
            binding.tvUsSize.setTextColor(getColor(R.color.black));
            itemSizeAdapter.setUpdateList(euSizeList);
        }));
        binding.btnBack.setOnClickListener((view -> {
            finish();
        }));

        binding.ivDesExpand.setOnClickListener((view -> {
            if(binding.tvDescription.getVisibility()==View.VISIBLE){
                binding.tvDescription.setVisibility(View.GONE);
            }else {
                binding.tvDescription.setVisibility(View.VISIBLE);
            }

        }));
        binding.ivDeliExpand.setOnClickListener((view -> {
            if(binding.tvdeli.getVisibility()==View.VISIBLE){
                binding.tvdeli.setVisibility(View.GONE);
            }else {
                binding.tvdeli.setVisibility(View.VISIBLE);
            }
        }));

        binding.ivAdd.setOnClickListener((view)->{
            if(quantity>=productModel.getStock()){
                quantity=productModel.getStock();
                return;
            }
            ++quantity;
            binding.tvQuantity.setText(quantity.toString());
        });
        binding.ivRemove.setOnClickListener((view)->{
            if(quantity==1)return;
            --quantity;
            binding.tvQuantity.setText(quantity.toString());
        });
    }
}