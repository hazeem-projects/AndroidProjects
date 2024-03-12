package com.awh.dressmart;

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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.awh.dressmart.ui.categories.CategoriesFragment;
import com.awh.dressmart.ui.home.HomeFragment;
import com.awh.dressmart.ui.message.MessagesFragment;
import com.awh.dressmart.ui.orders.OrdersFragment;
import com.awh.dressmart.ui.wishlist.WishlistFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        NavigationBarView.OnItemSelectedListener {

    private static final String TAG = "eshop";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        loadFragment(new HomeFragment(), false);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.side_nav_home:
            case R.id.bottom_nav_home:
                loadFragment(new HomeFragment(), false);
                break;
            case R.id.side_nav_profile:
            case R.id.bottom_nav_profile:
//                if (firebaseAuth.getCurrentUser() != null) {
//                    loadFragment(new AccountFragment(), false);
//                    System.out.println(firebaseAuth.getCurrentUser().getEmail());
//                } else {
//                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
//                }
                break;
            case R.id.bottom_nav_cart:
//                if (firebaseAuth.getCurrentUser() != null) {
//                    loadFragment(new CartFragment(), false);
//                } else {
//                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
//                }
//                break;
            case R.id.bottom_nav_categories:
                loadFragment(new CategoriesFragment(), true);
                break;
            case R.id.side_nav_orders:
                loadFragment(new OrdersFragment(), false);
                break;
            case R.id.side_nav_wishlist:
                loadFragment(new WishlistFragment(), false);
                break;
            case R.id.side_nav_message:
                loadFragment(new MessagesFragment(), false);
                break;
            case R.id.side_nav_login:
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                break;
            case R.id.side_nav_logout:
//                firebaseAuth.signOut();
//                finish();
//                startActivity(getIntent());
        break;

        }
        return true;
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if (addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int entryCount = getSupportFragmentManager().getBackStackEntryCount();
            Log.i(TAG, entryCount+"");
            if (entryCount > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }

        }
    }
    public void showBottomNavigationView(boolean show){
        if (show){
            bottomNavigationView.setVisibility(View.VISIBLE);
        }else{
            bottomNavigationView.setVisibility(View.GONE);
        }
    }
}