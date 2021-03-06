package phantomorion.com.railway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import phantomorion.com.railway.Adapterfiles.CollegeAdapter;
import phantomorion.com.railway.Adapterfiles.RailwayAdapter;


public class RailwayMain extends AppCompatActivity {

    RecyclerView mRecyclerViewEvents;
    ArrayList<PassDetails> ed;
    Button logout;
    CollegeAdapter mAdapter;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    CollectionReference crf = fdb.collection("pass");
    SharedPreferences spref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_railway_main);
        logout=findViewById(R.id.logout);
        spref=getApplicationContext().getSharedPreferences("userName",MODE_PRIVATE);
        editor=spref.edit();
        mRecyclerViewEvents = findViewById(R.id.recycler_view_for_railway);
        mRecyclerViewEvents.setHasFixedSize(true);
        mRecyclerViewEvents.setLayoutManager(new LinearLayoutManager(RailwayMain.this));
        mRecyclerViewEvents.setNestedScrollingEnabled(true);


        crf.whereEqualTo("approvedCollege",true).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ed = (ArrayList<PassDetails>) queryDocumentSnapshots.toObjects(PassDetails.class);
                        mRecyclerViewEvents.setAdapter(new RailwayAdapter(ed));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RailwayMain.this, "HEYEYEYEYEYEY", Toast.LENGTH_SHORT).show();
                    }
                });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RailwayMain.this);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:


                                editor.clear();
                                editor.commit();
                                Intent intent1 =new Intent(RailwayMain.this,signInOptions.class);
                                startActivity(intent1);
                                finish();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                builder.setMessage("Are you sure you want to log out?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("Back", dialogClickListener).show();

            }
        });


    }
}