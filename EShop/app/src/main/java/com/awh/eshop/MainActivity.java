package com.awh.eshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.awh.eshop.ui.cart.CartFragment;
import com.awh.eshop.ui.categories.CategoriesFragment;
import com.awh.eshop.ui.home.HomeFragment;
import com.awh.eshop.ui.message.MessageFragment;
import com.awh.eshop.ui.orders.OrdersFragment;
import com.awh.eshop.ui.profile.ProfileFragment;
import com.awh.eshop.ui.wishlist.WishlistFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener
{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        loadFragment(new HomeFragment());
        View headerView = navigationView.getHeaderView(0);
        ImageView imageView = headerView.findViewById(R.id.profilePicture);

        Glide.with(this)
                .load(R.drawable.splash_app_logo)
                .circleCrop()
                .override(100, 100)
                .into(imageView);


    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

    Toast.makeText(this,"clicked", Toast.LENGTH_SHORT).show();
//        switch ( item.getItemId()){
//
//            case R.id.side_nav_home :
//                loadFragment(new HomeFragment());
//
//            case R.id.bottm_nav_home:
//                loadFragment(new HomeFragment());
//                break;
//            case R.id.side_nav_messages:
//                loadFragment(new MessageFragment());
//                break;
//
//            case R.id.side_nav_orders:
// loadFragment(new OrdersFragment());
//                break;
//            case R.id.side_nav_login:
//                break;
//            case R.id.side_nav_logout:
//                break;
//            case R.id.side_nav_wishlist:
//                loadFragment(new WishlistFragment());
//                break;
//            case R.id.side_nav_profile :
//                loadFragment(new ProfileFragment());
//
//            case R.id.bottm_nav_profile:
//                loadFragment(new ProfileFragment());
//                break;
//            case R.id.bottm_nav_categories:
//                loadFragment(new CategoriesFragment());
//                break;
//            case R.id.bottm_nav_cart:
//                loadFragment(new CartFragment());
//                break;
//
//
//
//        }
//
//
    return false;
    }

    public void loadFragment(Fragment fragment){
FragmentManager supportFragmentManager =getSupportFragmentManager();
  FragmentTransaction fragmentTransaction =supportFragmentManager.beginTransaction();
        FragmentTransaction transaction = fragmentTransaction.replace(R.id.container, fragment);
        transaction.commit();

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}