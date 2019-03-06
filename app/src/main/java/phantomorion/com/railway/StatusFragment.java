package phantomorion.com.railway;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vipul.hp_hp.timelineview.TimelineView;

import phantomorion.com.railway.Adapterfiles.CollegeAdapter;

public class StatusFragment extends Fragment {
    SharedPreferences spref;
    TimelineView mImageView1;
    TimelineView mImageView2;
    TimelineView mImageView3;
    TimelineView mImageView4;
    SharedPreferences.Editor edit;
    FirebaseFirestore fb;
    CollectionReference cb;
    Boolean clg,railway;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_status, container, false);
        mImageView1 = view.findViewById(R.id.received);
        mImageView2 = view.findViewById(R.id.verified);
        spref = getActivity().getSharedPreferences("userName", Context.MODE_PRIVATE);
        edit = spref.edit();
        final Context context =getContext();

        fb = FirebaseFirestore.getInstance();
        cb = fb.collection("pass");


//        cb.document(spref.getString("userName", null)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                PassDetails pd = documentSnapshot.toObject(PassDetails.class);
//                clg=pd.getApprovedCollege();
//                railway=pd.getApprovedRailway();
//
//
//                if(!clg && !railway)
//                {
//                   mImageView1.setMarker(context.getResources().getDrawable(R.drawable.filledcircle));
//
//                }
//                else if(clg && !railway)
//                {
//                    mImageView2.setMarker(context.getResources().getDrawable(R.drawable.filledcircle));
//                }
//                else
//                {
//                    mImageView3.setMarker(context.getResources().getDrawable(R.drawable.filledcircle));
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
//            }
//        });


        return view;
    }
}
