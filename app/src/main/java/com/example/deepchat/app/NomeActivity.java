package com.example.deepchat.app;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NomeActivity extends AppCompatActivity {

    TextInputLayout mTextInputLayout;
    Button mButtonSubmit;
    DatabaseReference mDatabaseReference;
    private FirebaseUser mCurrentUser;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome);

        //-----CHANGING TITLE AND BACK BUTTON----
        this.setTitle("Alterar Nome de perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressDialog=new ProgressDialog(this);

        mCurrentUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=mCurrentUser.getUid();

        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        mTextInputLayout=(TextInputLayout)findViewById(R.id.textInputStatus);
        mButtonSubmit=(Button)findViewById(R.id.buttonChangeStatus);

        //-----ADDING PREVIOUS STATUS AS DEFAULT-----
        String currentStatus=getIntent().getStringExtra("current_name");
        mTextInputLayout.getEditText().setText(currentStatus);
    }
    public void buttonIsClicked(View view){

        String name=mTextInputLayout.getEditText().getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(NomeActivity.this, "Por favor, escreva alguma coisa ...", Toast.LENGTH_SHORT).show();
            return ;
        }
        mProgressDialog.setTitle("Atualizando Nome de Perfil");
        mProgressDialog.setMessage("Por favor, aguarde enquanto está atualizando...");
        mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();


        //------ADDING STATUS TO DATABASE----
        mDatabaseReference.child("name").setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mProgressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(NomeActivity.this, "Nome atualizado com sucesso", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(NomeActivity.this, "Nome não pode ser atualizado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // mDatabaseReference.child("online").setValue("true");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // mDatabaseReference.child("online").setValue(ServerValue.TIMESTAMP);

    }
}
