package phantomorion.com.railway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Intent;

public class LogOutFragment extends Fragment {

    FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        FirebaseAuth.getInstance().signOut();
                        MainActivity.editor.clear();

                        MainActivity.editor.commit();
                        Intent intent1 =new Intent(getContext(),signInOptions.class);
                        startActivity(intent1);
                        if(mAuth.getCurrentUser()!=null)
                        {
                            mAuth.getCurrentUser().delete();
                        }

                        getActivity().finish();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        builder.setMessage("Are you sure you want to log out?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("Back", dialogClickListener).show();

        return inflater.inflate(R.layout.fragment_log_out,container,false);
    }
}