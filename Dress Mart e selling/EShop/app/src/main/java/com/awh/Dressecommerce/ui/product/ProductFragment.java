package com.awh.Dressecommerce.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.awh.Dressecommerce.MainActivity;
import com.awh.Dressecommerce.R;
import com.awh.Dressecommerce.adapter.ProductAdapter;
import com.awh.Dressecommerce.model.Category;
import com.awh.Dressecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {
    private static final String TAG = "DressMart";
    private FirebaseFirestore firebaseFirestore;
    private GridView productView;
    private Category category;
    private List<Product> products;

    public ProductFragment(Category category) {
        this.category = category;
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        products = new ArrayList<>();


        //insertSampleData();

    }


    private void insertSampleData() {
        CollectionReference products = firebaseFirestore.collection("products");

        List<Product> productsList = new ArrayList<>();
        productsList.add(new Product("P1", "Crew Neck T Shirts 2 x Value Pack for Men", "Crew Neck T Shirts 2 x Value Pack for Men - Black & White", "P1", 3500, "1"));
        productsList.add(new Product("P2", "Crew Neck T Shirts 3 x Value Pack for Men", "Crew Neck T Shirts 3 x Value Pack for Men - Maroon, White & Dark Grey", "P2", 4000, "1"));
        productsList.add(new Product("P3", "Crew Neck Long Sleeve T Shirts", "Crew Neck Long Sleeve T Shirts 3 x Value Pack for Men - Navy Blue, Maroon & Black", "P3", 3200, "1"));
        productsList.add(new Product("P4", "Long Sleeve Men's T Shirt", "Black Color Long Sleeve Men's T Shirt New Style Material 160GSM", "P4", 2500, "1"));
        productsList.add(new Product("P5", "Men's Plain T Shirt", "Pure White / Dark Red / Lemon Yellow Color Men's Plain T Shirt New Style Comfortable, Durable & Soft Material.", "P5", 2200, "1"));
        productsList.add(new Product("P15", "Blue Crew Neck T-Shirt", "Bear Appeal Navy Blue Crew Neck T-Shirt", "P15", 1400, "1"));
        productsList.add(new Product("P16", "Tommy-Hilfiger Long Sleeve", "Tommy-Hilfiger Yellow Color Long Sleeve Men's T Shirt New Style Material 160GSM", "P16", 1290, "1"));
        productsList.add(new Product("P17", "Premium Long Sleeve T shirt", "T-shirt Republic Maroon - Men's Premium Long Sleeve T shirt", "P17", 1350, "1"));

        productsList.add(new Product("P6", "Ma&Baby Baby Summer Slip Dress", "Ma&Baby Baby Summer Slip Dress, Floral Lacing Spaghetti Strap Sleeveless Layered Braces Skirt for Girls, 0-3 Years, 4 Colors", "P6", 3100, "6"));
        productsList.add(new Product("P7", "Newborn Baby Hearts Pattern Bodysuit", "Crew Neck T Shirts 3 x Value Pack for Men - Maroon, White & Dark Grey", "P7", 2800, "6"));
        productsList.add(new Product("P8", "Baby Girl Three Piece Suit", "Baby Girl Three Piece Suit Fashion Pitted Long Sleeve Romper and Pocket Suspender Skirt & Headband", "P8", 3200, "6"));

        productsList.add(new Product("P9", "Mens 511 Slim Fit Denim", "\n" +
                "Mens 511 Slim Fit Denim Trousers for Men Other brands - CK Denim Branded Premium Denim", "P9", 3100, "2"));
        productsList.add(new Product("P10", "Tommy Hilfegr denim", "Tommy Hilfegr denim Jeans Men Straight-out slim fit", "P10", 3450, "2"));
        productsList.add(new Product("P11", "US POLO Denim Jeans", "US POLO Denim Jeans For Men", "P11", 3250, "2"));


        productsList.add(new Product("P12", "Sun Protection Coat", "Yfashion ldren Sun Protection Coat Cartoon Breathable Light For 1-5 Years Old color", "P12", 1860, "4"));
        productsList.add(new Product("P13", "contrast color jacket", "Young men's casual sports fake 2-piece sweater stitching contrast color jacket", "P13", 2800, "4"));
        productsList.add(new Product("P14", "Baby Girl Three Piece Suit", "Baby Girl Three Piece Suit Fashion Pitted Long Sleeve Romper and Pocket Suspender Skirt & Headband", "P14", 3200, "4"));

        productsList.add(new Product("P18", "Casual Trouser Man Pant", "Men's Trousers Straight High Waist Cotton Men Trousers Pants for Male Casual Trouser Man Pant", "P18", 2960, "3"));
        productsList.add(new Product("P19", "Slim Fit Fashion", "Autumn Winter Casual Pants Men Straight Black Grey Pants Cotton Business Slim Fit Fashion Brand Trousers For Male Plus Size28-38", "P19", 13500, "3"));
       // productsList.add(new Product("P20", "Baby Girl Three Piece Suit", "Baby Girl Three Piece Suit Fashion Pitted Long Sleeve Romper and Pocket Suspender Skirt & Headband", "P20", 3200, "3"));


        for (Product p : productsList) {
            products.add(p);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigationView(false);

        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productView = view.findViewById(R.id.product_product_list);

        ProductAdapter adapter = new ProductAdapter(view.getContext(), products);
        productView.setAdapter(adapter);


        if (this.category != null) {
            firebaseFirestore.collection("products").whereEqualTo("category", this.category.getId()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                products.clear();
                                for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                    Product product = snapshot.toObject(Product.class);
                                    products.add(product);
                                }
                                adapter.notifyDataSetChanged();

                                if (task.getResult().isEmpty()){
                                    productView.setVisibility(View.GONE);
                                    view.findViewById(R.id.product_no_product_text).setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }


        productView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Product product = products.get(i);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, new ProductDetailsFragment(product));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        productView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {

//                    // load more products like pagination

//                    view.findViewById(R.id.product_message).setVisibility(View.VISIBLE);
//
//                    products.add(new Product("P1", "T Shirt", "asfadasd", "", 2200, "1"));
//                    products.add(new Product("P2", "Trouser", "asfadasd", "", 4000, "1"));
//                    products.add(new Product("P3", "Jeans", "asfadasd", "", 3200, "1"));
//                    products.add(new Product("P4", "Belt", "asfadasd", "", 1500, "1"));
//                    products.add(new Product("P5", "Short", "asfadasd", "", 2200, "1"));
//                    adapter.notifyDataSetChanged();
//
//
//                    view.findViewById(R.id.product_message).setVisibility(View.INVISIBLE);
                }

            }
        });


    }
}