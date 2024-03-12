package com.jiat.eshop.ui.product;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.jiat.eshop.MainActivity;
import com.jiat.eshop.R;
import com.jiat.eshop.adapter.SingleProductImageAdapter;
import com.jiat.eshop.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsFragment extends Fragment {

    private static final String TAG = "eshop";
    private ListView productImageListView;
    private ImageView productImage;
    private TextView nameText, priceText, descriptionText;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage storage;

    private List<String> imagesPathList;
    private Product product;


    public ProductDetailsFragment(Product product) {
        this.product = product;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigationView(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productImageListView = view.findViewById(R.id.single_product_image_list);
        productImage = view.findViewById(R.id.single_product_full_image);
        nameText = view.findViewById(R.id.single_product_name);
        priceText = view.findViewById(R.id.single_product_price);
        descriptionText = view.findViewById(R.id.single_product_description);

        nameText.setText(product.getName());
        priceText.setText("LKR "+String.valueOf(product.getPrice()));
        descriptionText.setText(product.getDescription());

        imagesPathList = new ArrayList<>();

        SingleProductImageAdapter adapter = new SingleProductImageAdapter(view.getContext(), imagesPathList);

        productImageListView.setAdapter(adapter);

        storage.getReference("product-images/"+product.getImagePath()).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference reference : listResult.getItems()) {
                    imagesPathList.add(reference.getPath());
                }

                if (imagesPathList.size() > 0){
                    storage.getReference(imagesPathList.get(0))
                            .getDownloadUrl()
                            .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(view.getContext()).load(uri)
                                            .into(productImage);
                                }
                            });
                }


                adapter.notifyDataSetChanged();
            }
        });


        productImageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                storage.getReference(imagesPathList.get(i))
                        .getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(view.getContext()).load(uri)
                                        .into(productImage);
                            }
                        });
            }
        });


    }
}