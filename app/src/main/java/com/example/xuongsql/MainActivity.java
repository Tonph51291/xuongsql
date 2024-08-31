package com.example.xuongsql;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.xuongsql.Fragment.CapNhatFrag;
import com.example.xuongsql.Fragment.QuanLyFrag;
import com.example.xuongsql.Fragment.QuanLyThanhVienFrag;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    QuanLyFrag quanly;
    QuanLyThanhVienFrag quanlytv;
    CapNhatFrag capnhat;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drawerLayout = findViewById(R.id.main);
        toolbar = findViewById(R.id.toolbar_view);
        navigationView = findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);

        // Setup drawer
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.chuoi_open,
                R.string.chuoi_dong
        );
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fm = getSupportFragmentManager();
        quanly = new QuanLyFrag();
        quanlytv = new QuanLyThanhVienFrag();
        capnhat = new CapNhatFrag();

        // Get user role and check if information needs to be updated
        SharedPreferences sha = getSharedPreferences("data", MODE_PRIVATE);
        String chucvu = sha.getString("chucvu", "");
        boolean isInfoUpdated = sha.getBoolean("isInfoUpdated", false); // This flag should be set after the user updates their info

        // If not Admin and info not updated, force user to update info
        if (!chucvu.equals("Admin") && !isInfoUpdated) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, capnhat).commit();
            toolbar.setTitle("Cập nhật tài khoản");
            // Disable other menu items
            navigationView.getMenu().findItem(R.id.qlpb).setEnabled(false);
            navigationView.getMenu().findItem(R.id.qlnv).setEnabled(false);
        } else {
            // Admin or info already updated, load the default fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, quanly).commit();
        }

        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlpb) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, quanly).commit();
                    toolbar.setTitle("Quản lý phòng ban");
                } else if (item.getItemId() == R.id.qlnv) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, quanlytv).commit();
                    SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putInt("maphongban",0);
                    editor.apply();


                    toolbar.setTitle("Quản lý nhân viên");
                } else if (item.getItemId() == R.id.cntk) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, capnhat).commit();
                    toolbar.setTitle("Cập nhật tài khoản");
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }


    public void onInfoUpdated() {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putBoolean("isInfoUpdated", true);
        editor.apply();

        // Enable other menu items again
        navigationView.getMenu().findItem(R.id.qlpb).setEnabled(true);
        navigationView.getMenu().findItem(R.id.qlnv).setEnabled(true);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, quanly).commit();
        toolbar.setTitle("Quản lý phòng ban");
    }


}