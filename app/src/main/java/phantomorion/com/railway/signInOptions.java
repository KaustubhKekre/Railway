package phantomorion.com.railway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class signInOptions extends AppCompatActivity {
    Button student,college,railway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_options);
        student=findViewById(R.id.student);
        college=findViewById(R.id.college);
        railway=findViewById(R.id.railway);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signInOptions.this,studentLogin.class));
                finish();
            }
        });



        college.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(signInOptions.this,collegeAndrailwayLogin.class);
                intent.putExtra("type","college");
                startActivity(intent);
            }
        });



        railway.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(signInOptions.this,collegeAndrailwayLogin.class);
                intent.putExtra("type","railway");
                startActivity(intent);

            }
        });


    }
}
