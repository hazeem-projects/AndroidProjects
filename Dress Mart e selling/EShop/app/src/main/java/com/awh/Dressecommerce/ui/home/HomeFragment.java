package com.awh.Dressecommerce.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.awh.Dressecommerce.MainActivity;
import com.awh.Dressecommerce.R;
import com.awh.Dressecommerce.adapter.ProductAdapter;
import com.awh.Dressecommerce.adapter.SliderAdapter;
import com.awh.Dressecommerce.model.Product;
import com.awh.Dressecommerce.model.SliderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    FirebaseStorage storage;
    FirebaseFirestore firebaseFirestore;
    List<Product> productList;
    GridView productView;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity =(MainActivity) getActivity();
        activity.showBottomNavigationView(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        productList= new ArrayList<>();
        productView=view.findViewById(R.id.home_product_list);
        ProductAdapter adapter=new ProductAdapter(view.getContext(),productList);
        productView.setAdapter(adapter);

        firebaseFirestore.collection("products").orderBy("created", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            productList.clear();
                            for(QueryDocumentSnapshot snapshot: task.getResult()){
                                Product product=  snapshot.toObject(Product.class);
                                productList.add(product);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });



        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(view.getContext());
        sliderAdapter.addItem(new SliderItem("", "https://d1hj68zhrbkzii.cloudfront.net/wp-content/uploads/2022/06/Gents-formal-Fashion-Bug-Sri-Lanka.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://icms-image.slatic.net/images/ims-web/716140ba-82c0-49f5-b398-09d714513eff.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://icms-image.slatic.net/images/ims-web/b9ed7e50-0c52-448e-86af-2b2d86731d85.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://d1hj68zhrbkzii.cloudfront.net/wp-content/uploads/2022/05/Web-Slider-Jobbs-Fashion-Bug-Sri-Lanka.jpg"));

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();

    }
}