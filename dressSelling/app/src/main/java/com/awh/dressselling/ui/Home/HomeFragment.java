package com.awh.dressselling.ui.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awh.dressselling.Adapter.SliderAdapter;
import com.awh.dressselling.R;
import com.awh.dressselling.model.SliderItem;
import com.smarteist.autoimageslider.SliderView;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        SliderView sliderView = view.findViewById(R.id.imageSlider);

        SliderAdapter sliderAdapter = new SliderAdapter(view.getContext());
        sliderAdapter.addItem(new SliderItem("", "https://icms-image.slatic.net/images/ims-web/5264c754-632f-47c8-8fdd-59eec28d61ad.jpg_1200x1200.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/14d5f677630559.5c8d3005a7c9c.png"));
        sliderAdapter.addItem(new SliderItem("", "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/da6c8077630559.5c8d3005a810f.png"));

        sliderView.setSliderAdapter(sliderAdapter);



    }
}