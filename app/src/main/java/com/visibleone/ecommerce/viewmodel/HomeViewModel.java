package com.visibleone.ecommerce.viewmodel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.visibleone.ecommerce.model.ProductModel;
import com.visibleone.ecommerce.repository.Repository;
import java.util.List;

public class HomeViewModel extends ViewModel {
    FirebaseFirestore db;
    private Repository myRepository;

    private MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();


    public HomeViewModel() {
        myRepository = new Repository();
        db = FirebaseFirestore.getInstance();

    }


    public MutableLiveData<List<ProductModel>> getProductList(){
        productListLiveData = myRepository.getProductList(db);
        return productListLiveData;
    }
}
