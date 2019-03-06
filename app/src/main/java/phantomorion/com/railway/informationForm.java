package phantomorion.com.railway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class informationForm extends AppCompatActivity {
    Spinner mSpin,mSpin2;
    Button mSubmitButton;
    AutoCompleteTextView mName,mID,mAge;
    String gender,department;
    String smName,smID,smAge;
    SharedPreferences spref;
    SharedPreferences.Editor ed;

    String [] genderlist = {"Male","Female","Other"};
    String [] departmentList = {"IT","Comps","EXTC","Elec","Mech","Prod","BioMed"};
    private FirebaseFirestore fdb=FirebaseFirestore.getInstance();
    private CollectionReference cref=fdb.collection("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spref=getApplicationContext().getSharedPreferences("userName",MODE_PRIVATE);
        ed=spref.edit();
        setContentView(R.layout.activity_information_form);
        mSpin = findViewById(R.id.gender_spinner);
        mSpin2 = findViewById(R.id.spinner_department);
        mSubmitButton = findViewById(R.id.register_button);
        mName = findViewById(R.id.register_username);
        mID = findViewById(R.id.register_id);
        mAge = findViewById(R.id.register_age);
        if(!MainActivity.pref.getString("userName","12121212121").equals("12121212121"))
        {
            mName.setText(MainActivity.pref.getString("userName",null));
        }

        mSpin.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,genderlist));
        mSpin2.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,departmentList));
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = genderlist[(int) mSpin.getSelectedItemId()];
                department = departmentList[(int)mSpin2.getSelectedItemId()];

                smName=mName.getText().toString();
                smAge=mAge.getText().toString();
                smID=mID.getText().toString();
                ed.putString("userName",smName);
                ed.commit();
                UserDetails userDetails =new UserDetails(smName,smID,smAge,department,gender);
                cref.document(smName).set(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(informationForm.this,"Welcome",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(informationForm.this,BottmNavigationActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(informationForm.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}