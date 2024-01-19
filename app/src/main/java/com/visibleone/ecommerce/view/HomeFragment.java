package com.visibleone.ecommerce.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.visibleone.ecommerce.adapter.BrandAdapter;
import com.visibleone.ecommerce.adapter.MenShoeAdapter;
import com.visibleone.ecommerce.adapter.WoMenShoeAdapter;
import com.visibleone.ecommerce.databinding.FragmentHomeBinding;
import com.visibleone.ecommerce.model.ProductModel;
import com.visibleone.ecommerce.viewmodel.HomeViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    List<ProductModel> menShoeList;
    List<ProductModel> womenShoeList;
    List<ProductModel> brandList;

    MenShoeAdapter menShoeAdapter;
    WoMenShoeAdapter womenShoeAdapter;
    BrandAdapter brandAdapter;

    HomeViewModel homeViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menShoeList = new ArrayList<>(0);
        womenShoeList = new ArrayList<>(0);
        brandList = new ArrayList<>(0);

        initViewModel();

        binding.rvBrand.setHasFixedSize(true);
        binding.rvBrand.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        binding.rvMenShoes.setHasFixedSize(true);
        binding.rvMenShoes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        binding.rvWomenShoes.setHasFixedSize(true);
        binding.rvWomenShoes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

         menShoeAdapter = new MenShoeAdapter(getContext(),menShoeList);
        binding.rvMenShoes.setAdapter(menShoeAdapter);

         womenShoeAdapter = new WoMenShoeAdapter(getContext(),womenShoeList);
        binding.rvWomenShoes.setAdapter(womenShoeAdapter);

         brandAdapter = new BrandAdapter(getContext(),brandList);
        binding.rvBrand.setAdapter(brandAdapter);

        menShoeAdapter.setItemClickListener(productModel -> {
            Intent intent = new Intent(getContext(),DetailActivity.class);
            intent.putExtra("detail",productModel);
            startActivity(intent);
        });

        brandAdapter.setItemClickListener(productModel -> {

        });

        menShoeAdapter.setOnCartClickListener(productModel -> {

        });

        womenShoeAdapter.setItemClickListener(productModel -> {
            Intent intent = new Intent(getContext(),DetailActivity.class);
            intent.putExtra("detail",productModel);
            startActivity(intent);
        });

        menShoeAdapter.setOnCartClickListener(productModel -> {

        });

    }

    private void initViewModel() {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        homeViewModel.getProductList().observe(requireActivity(), changeValue -> {
            System.out.println(":::::here success "+changeValue.size());
            Map<String,ProductModel> bMap = new HashMap<>(0);
            for(int i=0;i<changeValue.size();i++){
                ProductModel model = changeValue.get(i);
                bMap.put(model.getBrand(),model);
                if(model.getpCategory().equals("men_shoe")){
                    menShoeList.add(model);
                } else if (model.getpCategory().equals("women_shoe")) {
                    womenShoeList.add(model);
                }
            }
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    brandList =new ArrayList<>(bMap.values());
                    menShoeAdapter.setUpdateList(menShoeList);
                    womenShoeAdapter.setUpdateList(womenShoeList);
                    brandAdapter.setUpdateList(brandList);
                }
            });
            if(isAdded()){
                requireActivity().runOnUiThread(()->{
                    binding.shimmerViewContainer.stopShimmer();
                    binding.shimmerViewContainer.setVisibility(View.GONE);
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        binding.shimmerViewContainer.stopShimmer();
        super.onPause();
    }
}