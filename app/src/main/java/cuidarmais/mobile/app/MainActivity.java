package cuidarmais.mobile.app;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import cuidarmais.mobile.app.fragments.AskEquipmentFragment;
import cuidarmais.mobile.app.fragments.AskMaterialFragment;
import cuidarmais.mobile.app.fragments.AskMedicineFragment;
import cuidarmais.mobile.app.fragments.AvailabilityFragment;
import cuidarmais.mobile.app.fragments.AdmissionFragment;
import cuidarmais.mobile.app.fragments.ContactFragment;
import cuidarmais.mobile.app.fragments.DefaultFragment;
import cuidarmais.mobile.app.fragments.DispMaterialFragment;
import cuidarmais.mobile.app.fragments.DispMedicineFragment;
import cuidarmais.mobile.app.fragments.InputEquipmentFragment;
import cuidarmais.mobile.app.fragments.InputMaterialFragment;
import cuidarmais.mobile.app.fragments.InputMedicineFragment;
import cuidarmais.mobile.app.fragments.NotificationFragment;
import cuidarmais.mobile.app.fragments.DeployFragment;
import cuidarmais.mobile.app.fragments.PreferencesFragment;
import cuidarmais.mobile.app.fragments.ScheduleFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected CardView admissionCardView,
                        deployCardView,
                        hospitalizationCardView;

    protected Toolbar toolbar;

    protected Bundle savedInstanceState;

    protected void start(){


        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        admissionCardView = (CardView) findViewById(R.id.admissionCardView);
        deployCardView = (CardView) findViewById(R.id.deployCardView);
        hospitalizationCardView = (CardView) findViewById(R.id.hospitalizationCardView);

        deployCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openActivity(DeployActivity.class);
            }
        });

        admissionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(AdminissionActivity.class);
                //displayScreen(R.id.admission);
            }
        });



        //method that hides soft keyboard when it loses focus
        findViewById(R.id.drawer_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        start();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        displayScreen(id);
        return true;
    }

    public void displayScreen(int id){
        Fragment fragment = null;


        switch(id){
            case R.id.admission:
                fragment = new AdmissionFragment();
                break;
            case R.id.deploy:
                openActivity(DeployActivity.class);
                break;
            case R.id.hospitalization:

                openActivity(HospActivity.class);
                break;
            case R.id.input_medicine:
                fragment = new InputMedicineFragment();
                break;
            case R.id.nav_ask_medicine:
                fragment = new AskMedicineFragment();
                break;
            case R.id.nav_disp_medicine:
                fragment = new DispMedicineFragment();
                break;
            case R.id.input_material:
                fragment = new InputMaterialFragment();
                break;
            case R.id.nav_ask_material:
                fragment = new AskMaterialFragment();
                break;

            case R.id.input_equipment:
                fragment = new InputEquipmentFragment();
                break;
            case R.id.nav_ask_equipment:
                fragment = new AskEquipmentFragment();
                break;
            case R.id.nav_schedule:
                fragment = new ScheduleFragment();
                break;
            case R.id.nav_pref:
                fragment = new PreferencesFragment();
                break;
            case R.id.nav_avail:
                fragment = new AvailabilityFragment();
                break;
            case R.id.nav_contact:
                fragment = new ContactFragment();
                break;
            default:
                //fragment = new DefaultFragment();
                start();
                break;

        }

        //method that hides soft keyboard when it loses focus


        if(fragment != null){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(fragment.toString());

            String hash = String.valueOf(fragment.hashCode());

            transaction.replace(R.id.content_frame, fragment,hash);
            transaction.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }

    private void openActivity(Class activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }


}
