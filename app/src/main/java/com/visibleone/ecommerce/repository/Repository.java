package com.visibleone.ecommerce.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;
import com.visibleone.ecommerce.model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Repository {
    MutableLiveData<List<ProductModel>> productList = new MutableLiveData<>();
    public MutableLiveData<List<ProductModel>> getProductList(FirebaseFirestore db){
        try {
            productList = new FetchProduct(db).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return productList;
    }

    private static class FetchProduct extends AsyncTask<Void, Void, MutableLiveData<List<ProductModel>>> {
        MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();
        private List<ProductModel> productList = new ArrayList<>(0);
        FirebaseFirestore db;

        public FetchProduct(FirebaseFirestore db) {
            this.db = db;
        }

        @Override
        protected MutableLiveData<List<ProductModel>> doInBackground(Void... voids) {
            db.collection("product")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Map<String,ProductModel> bMap = new HashMap<>(0);
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Gson gson = new Gson();
                                String json = gson.toJson(document.getData());
                                ProductModel model = gson.fromJson(json,ProductModel.class);
                                productList.add(model);
                            }
                            productListLiveData.setValue(productList);
                        } else {
                            Log.w("TAG:::::", "Error getting documents.", task.getException());
                        }
                    });

            return productListLiveData;
        }
    }
}


