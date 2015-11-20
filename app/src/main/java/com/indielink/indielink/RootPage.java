package com.indielink.indielink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.indielink.indielink.Profile.ProfileContent;
import com.indielink.indielink.Profile.UserRole;
import org.json.JSONObject;

public class RootPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        UserRole.IsMusician();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Initial fragment when starting the app
        getSupportFragmentManager().beginTransaction().addToBackStack("Search")
        .replace(R.id.frame_container, new SearchFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        //    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout
        //        if (drawer.isDrawerOpen(GravityCompat.START)) {
        //        drawer.closeDrawer(GravityCompat.START);}
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.root_page, menu);
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

    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        fragmentManager = getSupportFragmentManager();
        int popBackStackCount = fragmentManager.getBackStackEntryCount();
        fragmentTransaction = fragmentManager.beginTransaction();
        String CurrentFragment = fragmentManager.getBackStackEntryAt(popBackStackCount-1).getName();
        int id = item.getItemId();
        switch (id){
            case (R.id.Profile): {
                if(CurrentFragment != "Profile" && CurrentFragment != "EditProfile")
                {
                    GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    Fragment fragment = new ProfileFragment();
                                    ProfileContent.InitializeProfile(object);
                                    fragmentManager.beginTransaction().addToBackStack("Profile")
                                            .replace(R.id.frame_container, fragment).commit();
                                    //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                                    //drawer.closeDrawer(GravityCompat.START);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,gender,birthday,picture.width(300).height(300)");
                request.setParameters(parameters);
                request.executeAsync();
                }
                break;
            }
            case (R.id.Search): {
                if(CurrentFragment != "Search") {
                    fragmentTransaction.addToBackStack("Search");
                    fragment = new SearchFragment();
                }
                break;
            }
            case (R.id.Music):
            {
                if(CurrentFragment != "Music") {
                    fragmentTransaction.addToBackStack("Music");
                    fragment = new AudioRecorderFragment();
                }
                break;
            }
            case (R.id.band1):
            {
                if(CurrentFragment != "BandProfile") {
                    fragmentTransaction.addToBackStack("BandProfile");
                    fragment = new BandProfileFragment();
                }
                break;
            }
            case (R.id.band2):
            {
                break;
            }
            case (R.id.CreateBand):
            {
                if(CurrentFragment != "CreateBand") {
                    fragmentTransaction.addToBackStack("CreateBand");
                    fragment = new CreateBandFragment();
                }
                break;
            }
        }
        if (fragment != null && id != R.id.Profile) {
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
