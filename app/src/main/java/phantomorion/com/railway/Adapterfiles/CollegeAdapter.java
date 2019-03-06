package phantomorion.com.railway.Adapterfiles;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


import phantomorion.com.railway.CollegeMain;
import phantomorion.com.railway.PassDetails;
import phantomorion.com.railway.R;


public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.ViewHolder> {

    ArrayList<PassDetails> collegeDetails;
    private onItemClickedListener mListener;
    FirebaseFirestore fdb=FirebaseFirestore.getInstance();
    CollectionReference cref =fdb.collection("pass");

    public interface onItemClickedListener{

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickedListener listener){
        mListener = listener;
    }

    public CollegeAdapter(ArrayList<PassDetails> eventDetails){
        this.collegeDetails=eventDetails;
    }

    @NonNull
    @Override
    public CollegeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflate=LayoutInflater.from(viewGroup.getContext());
        View view = inflate.inflate(R.layout.card_view_for_college,viewGroup,false);
        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final CollegeAdapter.ViewHolder viewHolder, final int i) {
  if (collegeDetails.get(i).getApprovedCollege())
   { viewHolder.tick.setVisibility(View.GONE);
     viewHolder.clear.setVisibility(View.GONE);
        }
        viewHolder.mName.setText(collegeDetails.get(i).getName());
        viewHolder.mGender.setText(collegeDetails.get(i).getGender());

        if(collegeDetails.get(i).getType().equals("I"))
        {
            viewHolder.mClass.setText(collegeDetails.get(i).getType() + " Class");
        }
        else
        {
            viewHolder.mClass.setText(collegeDetails.get(i).getType() + " Class");
        }
        viewHolder.mFrom.setText(collegeDetails.get(i).getSource());

        viewHolder.tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                cref.document(collegeDetails.get(i).getName()).update("approvedCollege",true).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        viewHolder.tick.setVisibility(View.GONE);
                        viewHolder.clear.setVisibility(View.GONE);
                        Toast.makeText(view.getContext(),"Pass for "+collegeDetails.get(i).getName()+" forwarded to Railway authorities",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        viewHolder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                cref.document(collegeDetails.get(i).getName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(v.getContext(),"Request of "+collegeDetails.get(i).getName()+" declined",Toast.LENGTH_LONG).show();
                        v.getContext().startActivity(new Intent(v.getContext(),CollegeMain.class));

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return collegeDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName,mGender,mClass,mFrom;
        ImageView tick,clear;
        public ViewHolder(@NonNull View itemView, final onItemClickedListener listener) {
            super(itemView);

            mName=itemView.findViewById(R.id.name);
            mGender=itemView.findViewById(R.id.gender);
            mClass=itemView.findViewById(R.id.Class);

            mFrom=itemView.findViewById(R.id.from);

            tick = itemView.findViewById(R.id.tick);
            clear = itemView.findViewById(R.id.clear);

        }
    }
}