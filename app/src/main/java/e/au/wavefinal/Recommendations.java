package e.au.wavefinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Recommendations extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new RecommendationsFragment());
    }
    private boolean loadFragment(Fragment fragment)
    {

        if(fragment!= null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = new RecommendationsFragment();
                break;
            case R.id.navigation_genres:
                fragment = new GenreFragment();
                break;
            case R.id.navigation_wave:
                fragment = new WaveFragment();
                break;
            case R.id.navigation_trending:
                fragment = new TrendingFragment();
                break;
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;

        }
        return loadFragment(fragment);
    }
}
