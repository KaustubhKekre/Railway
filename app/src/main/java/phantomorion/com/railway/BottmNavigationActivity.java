package phantomorion.com.railway;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class BottmNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottm_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;

                switch(item.getItemId()){
                    case R.id.profile:
                        tempFragment = new ProfileFragment();
                        break;
                    case R.id.apply:
                        tempFragment = new ApplyFragment();
                        break;
                    case R.id.pass:
                        tempFragment = new PassFragment();
                        break;
                    case R.id.status:
                        tempFragment = new StatusFragment();
                        break;
                    case R.id.log_out:
                        tempFragment = new LogOutFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tempFragment).commit();
                return true;
            }
        });
    }
}
