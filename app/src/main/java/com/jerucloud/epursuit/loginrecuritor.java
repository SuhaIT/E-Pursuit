package com.jerucloud.epursuit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginrecuritor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginrecuritor extends Fragment {
    EditText editTextTextEmailAiddress,editTextTeoxtPassword;
    String  gmail,pass;
    FirebaseAuth firebaseAuth;
    TextView forgetpass;
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loginrecuritor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginrecuritor.
     */
    // TODO: Rename and change types and number of parameters
    public static loginrecuritor newInstance(String param1, String param2) {
        loginrecuritor fragment = new loginrecuritor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_loginrecuritor, container, false);
        Button goToSigniUpBtn =view.findViewById(R.id.goToSigniUpBtn);
        Button loginBtn =view.findViewById(R.id.loginBitn);
        forgetpass=view.findViewById(R.id.forg);
        editTextTextEmailAiddress=view.findViewById(R.id.editTextTextEmaiilAddress);
        editTextTeoxtPassword= view.findViewById(R.id.editTextTexitPassword);

        firebaseAuth= FirebaseAuth.getInstance();

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(editTextTextEmailAiddress.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(),"Check your email to reset your password ",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(getActivity(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(editTextTextEmailAiddress.getText().toString().trim(),editTextTeoxtPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            // PreferenceUtils.saveid("spass", register.this);
                         //   PreferenceUtils.saveid(editTextTextEmailAiddress.getText().toString(), getActivity());
                           // PreferenceUtils.savePassword(editTextTeoxtPassword.getText().toString(), getActivity());
                            PreferenceUtils.saveid("2", getContext());
                            PreferenceUtils.savePassword(firebaseAuth.getCurrentUser().getUid(), getContext());
                            Intent toHome = new Intent(getActivity(),home_recreater.class);
                            startActivity(toHome);
                            Toast.makeText(getActivity(),"Welcome",Toast.LENGTH_LONG).show();
                            getActivity().finish();}
                        else{
                            Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        goToSigniUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),registerrecreater.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        return view;
    }

}