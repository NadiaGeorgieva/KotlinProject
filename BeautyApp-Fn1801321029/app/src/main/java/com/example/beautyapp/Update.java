package com.example.beautyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    protected String ID;
    protected EditText editTitle, editDescription;
    protected Button DelBtn, UpdBtn;

    protected void CloseThisActivity() {
        finishActivity(200);
        Intent i = new Intent(Update.this, NoteActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);
        DelBtn = findViewById(R.id.btnDelete);
        UpdBtn = findViewById(R.id.btnUpdate);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            ID = b.getString("Id");
            editTitle.setText(b.getString("title"));
            editDescription.setText(b.getString("description"));
        }
        UpdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=null;
                try{
                    db=SQLiteDatabase.openOrCreateDatabase(
                            getFilesDir().getPath() + "/" + "notedb.db",
                            null

                    );
                    String title1= editTitle.getText().toString();
                    String description1=editDescription.getText().toString();
                    String ins=" UPDATE NOTEDB SET title=?,description=?";
                    ins+="WHERE Id=?; ";
                    db.execSQL(ins,new  Object[]{title1,description1,ID});
                    Toast.makeText(getApplicationContext(),"Update is successful",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"ERROR!!!",Toast.LENGTH_LONG).show();

                }finally {
                    if(db!=null){
                        db.close();
                        db=null;
                    }
                }
                CloseThisActivity();  }
        });

        DelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=null;
                try{
                    db=SQLiteDatabase.openOrCreateDatabase(
                            getFilesDir().getPath() + "/" + "notedb.db",
                            null

                    );
                    String title=editTitle.getText().toString();
                    String description=editDescription.getText().toString();
                    String ins=" DELETE FROM NOTEDB WHERE ";
                    ins+="  Id=?; ";
                    db.execSQL(ins,new  Object[]{ID});
                    Toast.makeText(getApplicationContext(),"Delete is successful",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"ERROR!!!",Toast.LENGTH_LONG).show();

                }finally {
                    if(db!=null){
                        db.close();
                        db=null;
                    }
                }
                CloseThisActivity();
            }
        });
    }
    }
