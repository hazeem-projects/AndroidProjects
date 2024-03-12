package com.awh.dressselling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.awh.dressselling.ui.Home.HomeFragment;
import com.awh.dressselling.ui.Profile.ProfieFragment;
import com.awh.dressselling.ui.cart.CartFragment;
import com.awh.dressselling.ui.categories.CategoriesFragment;
import com.awh.dressselling.ui.messages.MessagesFragment;
import com.awh.dressselling.ui.orders.OrdersFragment;
import com.awh.dressselling.ui.wishlist.WishlistFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener
{
NavigationView navigationView;
MaterialToolbar toolbar;
DrawerLayout drawerLayout;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView=findViewById(R.id.navigation_view);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        bottomNavigationView=findViewById(R.id.bottom_navigation);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);


         bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) this);

        loadFragment(new HomeFragment());

        View headerView =navigationView.getHeaderView(0);
        ImageView imageView= headerView.findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.ic_baseline_person_24)

                .override(80,80)
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


        switch (item.getItemId()){
            case R.id.bottm_nav_home:
                case R.id.side_nav_home:
                loadFragment(new HomeFragment());
                break;
            case R.id.side_nav_profile:
                 case R.id.bottm_nav_profile:
                loadFragment(new ProfieFragment());
                break;
            case R.id.bottm_nav_cart:
                loadFragment(new CartFragment());
                break;
            case  R.id.bottm_nav_categories:
                loadFragment(new CategoriesFragment());
                break;
            case R.id.side_nav_orders:
                loadFragment(new OrdersFragment());
                break;
            case R.id.side_nav_wishlist:
                loadFragment(new WishlistFragment());
                break;
            case R.id.side_nav_message:
                loadFragment(new MessagesFragment());
                break;
            case R.id.side_nav_login:

                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                break;
            case R.id.side_nav_logout:
                break;

        }
        return true;

    }
    public void loadFragment(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }
}

