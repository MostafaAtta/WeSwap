package com.atta.weswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.atta.weswap.model.SessionManager;
import com.atta.weswap.ui.HomeFragmentDirections;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String keyword;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*Intent intent = new Intent(MainActivity.this, NewAdActivity.class);
                startActivity(intent);*//*

                navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                    @Override
                    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                        //Toast.makeText(MainActivity.this, "Visible Fragment label Name: "+destination.getLabel(),Toast.LENGTH_LONG).show();

                        String fragmentLabel = String.valueOf(destination.getLabel());
                        switch (fragmentLabel){
                            case "Home":


                                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment)
                                        .navigate(HomeFragmentDirections.actionNavHomeToNewAdFragment());
                                break;
                            case "Subcategories":
                                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment)
                                        .navigate(SubcategoriesFragmentDirections.actionSubCategoriesFragmentToNewAdFragment());
                                break;
                            default:
                                break;

                        }

                    }
                });


            }
        });
        */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_favorites,
                R.id.nav_profile, R.id.nav_my_ads)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
/*
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                //Toast.makeText(MainActivity.this, "Visible Fragment label Name: "+destination.getLabel(),Toast.LENGTH_LONG).show();

                if (destination.getLabel().equals("Home") || destination.getLabel().equals("Subcategories")){
                    fab.setVisibility(View.VISIBLE);
                }else {
                    fab.setVisibility(View.GONE);
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Retrieve the SearchView and plug it into SearchManager
        MenuItem searchItem = menu.findItem(R.id.action_search);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                //Toast.makeText(MainActivity.this, "Visible Fragment label Name: "+destination.getLabel(),Toast.LENGTH_LONG).show();

                if (destination.getLabel().equals("Home")){
                    searchItem.setVisible(true);
                }else {
                    searchItem.setVisible(false);
                }
            }
        });

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {


                keyword = s.toUpperCase();

                Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment)
                        .navigate(HomeFragmentDirections.actionNavHomeToSearchFragment(keyword));

                searchView.setIconified(false);
                searchItem.setVisible(false);
                searchView.setVisibility(View.GONE);
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout){
            SessionManager.getInstance(this).logoutUser();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
