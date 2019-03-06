package phantomorion.com.railway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class collegeAndrailwayLogin extends AppCompatActivity {
    EditText userName,password;
    Button login;
    ImageView image;
    String type;
    String userN,pwd;
    SharedPreferences spref;
    SharedPreferences.Editor ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spref=getApplicationContext().getSharedPreferences("userName",MODE_PRIVATE);
        ed=spref.edit();
        setContentView(R.layout.activity_college_andrailway_login);
        Intent intent=getIntent();
        type=intent.getExtras().getString("type");
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        image=findViewById(R.id.image);
        ed =spref.edit();


        if (type.equals("college"))
        {
            image.setImageResource(R.drawable.college);
        }
        else
        {
            image.setImageResource(R.drawable.railwayrailway);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userN=userName.getText().toString();
                pwd=password.getText().toString();
                if(type.equals("college"))
                {   if(userN.equals("college") && pwd.equals("college"))
                    {
                        ed.putString("userName","college");
                        ed.commit();
                   Intent intent = new Intent(collegeAndrailwayLogin.this, CollegeMain.class);
                   startActivity(intent);
                   finish();

                    }
                    else
                {
                    Toast.makeText(collegeAndrailwayLogin.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                    userName.setText("");
                    password.setText("");
                }
                }
                else
                {
                    if(userN.equals("railway") && pwd.equals("railway"))
                    {
                        ed.putString("userName","railway");
                        ed.commit();
                        Intent intent = new Intent(collegeAndrailwayLogin.this,RailwayMain.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(collegeAndrailwayLogin.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                        userName.setText("");
                        password.setText("");
                    }

                }
            }
        });
    }
}
