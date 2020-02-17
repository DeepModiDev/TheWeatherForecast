package com.theweatherforecast;

import android.content.Intent;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.theweatherforecast.CategoryViewHolder.CategoryViewHolder;
import com.theweatherforecast.Model.DataCallModel;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseRecyclerAdapter<DataCallModel,CategoryViewHolder> adapter;
    FirebaseDatabase database_final;
    DatabaseReference reference_final;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    String[] arr_location ={"Gandhinagar","Ahmedabad"};
    Spinner spinner;
    private int mPosition = 0;
    SwipeRefreshLayout swipeRefreshLayoutHome;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);



















        progressBar = findViewById(R.id.progressBar);

        swipeRefreshLayoutHome = findViewById(R.id.swipe_referesh_home);
        spinner = findViewById(R.id.spinner);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ReferenceActivity.class));
            }
        });
        //generate date

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        database_final = FirebaseDatabase.getInstance();
        reference_final = database_final.getReference("UserTemp");

        recyclerView = findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,arr_location);
        spinner.setAdapter(spinner_adapter);

        loadData();

        swipeRefreshLayoutHome.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayoutHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutHome.setRefreshing(false);
                loadData();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadData() {
        FirebaseRecyclerOptions<DataCallModel> options = new FirebaseRecyclerOptions.Builder<DataCallModel>()
                .setQuery(reference_final.child(arr_location[mPosition]), DataCallModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<DataCallModel, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i, @NonNull DataCallModel dataCallModel) {
                progressBar.setVisibility(View.GONE);
                categoryViewHolder.finalMaxTempTextView.setText(String.format("%s°C", dataCallModel.getFinalMaxTemp()));
                categoryViewHolder.finalMinTempTextView.setText(String.format("%s°C", dataCallModel.getFinalMinTemp()));
                categoryViewHolder.condition.setText(dataCallModel.getCondition());
                categoryViewHolder.city.setText(arr_location[mPosition]);
                categoryViewHolder.finalTempTime.setText(dataCallModel.getFinalDate());
                if(dataCallModel.getCondition().equals("Rain"))
                {
                    Picasso.get().load(dataCallModel.getImg_strom()).into(categoryViewHolder.img);
                }else if(dataCallModel.getCondition().equals("Haze"))
                {
                    Picasso.get().load(dataCallModel.getImg_cloudy()).into(categoryViewHolder.img);
                }else
                {
                    Picasso.get().load(dataCallModel.getImg_sun()).into(categoryViewHolder.img);
                }

                switch (i) {
                    case 0:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card2);
                        break;
                    case 1:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card);
                        break;
                    case 2:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card1);
                        break;
                    case 3:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card2);
                        break;
                    case 4:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card);
                        break;
                    case 5:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card1);
                        break;
                    case 6:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card2);
                        break;
                    case 7:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card);
                        break;
                    case 8:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card1);
                        break;
                    case 9:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card2);
                        break;

                    default:
                        categoryViewHolder.constraintLayout.setBackgroundResource(R.drawable.card);
                        break;
                }
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view, parent, false);
                return new CategoryViewHolder(view);
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.home, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                startActivity(new Intent(HomeActivity.this,AboutDeveloper.class));
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);

           if (id == R.id.nav_Support) {
               startActivity(new Intent(HomeActivity.this,MainActivity.class));
               drawer.closeDrawer(GravityCompat.START);
            }else if(id == R.id.about_developer)
            {
                startActivity(new Intent(HomeActivity.this,AboutDeveloper.class));
                drawer.closeDrawer(GravityCompat.START);
            }
            return true;
        }
}