package phantomorion.com.railway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ApplyFragment extends Fragment {
    Spinner s1,s2;
    String[] stationlist = {"Churchgate", "Marine Lines","Charni Road","Grant Road", "Mumbai Central","Dadar"};
    TextView name,sap;
    RadioGroup rg,rc;
    Button apply;
    String source,  type, issued, expiry,gender;
    FirebaseFirestore fdb;
    int type2,gender2;
    CollectionReference cref,cref2;
    SharedPreferences spref;
    RadioButton temp1,temp2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_apply,container,false);
        spref=getActivity().getSharedPreferences("userName",Context.MODE_PRIVATE);
        fdb=FirebaseFirestore.getInstance();
        cref=fdb.collection("users");
        cref2=fdb.collection("pass");
        s1 = view.findViewById(R.id.spinner_from);
        name=view.findViewById(R.id.name);
        sap=view.findViewById(R.id.sap);
        rg=view.findViewById(R.id.radio_group_gender);
        rc=view.findViewById(R.id.radio_group_class);
        apply=view.findViewById(R.id.button_apply);
        s1.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,stationlist));
        cref.document(spref.getString("userName",null)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserDetails ud=documentSnapshot.toObject(UserDetails.class);
                name.setText(""+ud.getName());
                sap.setText(""+ud.getID());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                source=stationlist[(int)s1.getSelectedItemPosition()];
                type2=rc.getCheckedRadioButtonId();
                gender2=rg.getCheckedRadioButtonId();
                temp1=view.findViewById(type2);
                temp2=view.findViewById(gender2);
                type=temp1.getText().toString();
                gender=temp2.getText().toString();

                PassDetails pd =new PassDetails(false,false,source,type,null,null,gender,spref.getString("userName",null));
                cref2.document(spref.getString("userName",null)).set(pd).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Concession pass request raised",Toast.LENGTH_LONG).show();
                       getFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatusFragment()).commit();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_LONG);
                    }
                });

            }




        });
        return view;
    }
}
