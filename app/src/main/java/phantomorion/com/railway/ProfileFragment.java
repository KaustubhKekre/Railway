package phantomorion.com.railway;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {
    FirebaseFirestore fdb;
    CollectionReference cref;
    TextView name,id,department,gender,age;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile,container,false);
        name=view.findViewById(R.id.dName);
        id=view.findViewById(R.id.sap);
        fdb=FirebaseFirestore.getInstance();
        cref=fdb.collection("users");
        department=view.findViewById(R.id.department);
        gender=view.findViewById(R.id.gender);
        age=view.findViewById(R.id.dAge);
        fdb=FirebaseFirestore.getInstance();
        cref.document(MainActivity.pref.getString("userName",null)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserDetails ud=documentSnapshot.toObject(UserDetails.class);
                name.setText(""+ud.getName());
                id.setText(""+ud.getID());
                department.setText(""+ud.getDepartment());
                gender.setText(""+ud.getGender());
                age.setText(""+ud.getAge());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}