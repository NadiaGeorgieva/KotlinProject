package com.example.beautyapp;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {
    protected EditText noteTitle,noteDescription;
    protected Button noteAddBtn;
    protected ListView noteViewAll;

    protected void initDB() throws SQLiteException{
        SQLiteDatabase notedb=null;
        notedb=SQLiteDatabase.openOrCreateDatabase(
                getFilesDir().getPath() + "/" + "notedb.db",
                null

        );
        String insertinto="CREATE TABLE if not exists NOTEDB(";
        insertinto+=" Id integer primary key AUTOINCREMENT, ";
        insertinto+=" title text not null, ";
        insertinto+=" description text not null, ";
        insertinto+=" unique(title,description) );";

        notedb.execSQL(insertinto);
        notedb.close();
    }
    protected void selectDB() throws SQLiteException {
        SQLiteDatabase notedb = null;
        notedb = SQLiteDatabase.openOrCreateDatabase(
                getFilesDir().getPath() + "/" + "notedb.db",
                null
        );
        noteViewAll.clearChoices();
        ArrayList<String> selRes = new ArrayList<String>();
        String sel = " SELECT * FROM NOTEDB;";
        Cursor cursor = notedb.rawQuery(sel, null);
        while (cursor.moveToNext()) {
            String Id = cursor.getString(cursor.getColumnIndex("Id"));
            String noteTitle = cursor.getString(cursor.getColumnIndex("title"));
            String noteDescription = cursor.getString(cursor.getColumnIndex("description"));
            selRes.add(Id+"\t "+ noteTitle + "\t " + noteDescription);
        }
        ArrayAdapter<String> arrayAdapter=
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.activity_list_view,
                        R.id.viewallnotes,
                        selRes
                );
        noteViewAll.setAdapter(arrayAdapter);
        notedb.execSQL(sel);
        notedb.close();
    }

    @Override
    @CallSuper
    protected void onActivityResult(int reqcode,int rescode,Intent data){
        super.onActivityResult(reqcode,rescode,data);
        try {
            selectDB();
        }catch (Exception e){

        }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteTitle = findViewById(R.id.titlePTId);
        noteDescription = findViewById(R.id.descrPTId);
        noteAddBtn = findViewById(R.id.btnAddId);
        noteViewAll = findViewById(R.id.lv1);
        noteViewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String selectedrow = "";
                TextView clickedtext = view.findViewById(R.id.viewallnotes);
                selectedrow = clickedtext.getText().toString();
                String[] elements = selectedrow.split("\t");
                String ID = elements[0];
                String title1 = elements[1];
                String description1 = elements[2];
                Intent updintent = new Intent(NoteActivity.this, Update.class);
                Bundle bun = new Bundle();
                bun.putString("Id", ID);
                bun.putString("title", title1);
                bun.putString("description", description1);
                updintent.putExtras(bun);
                startActivityForResult(updintent, 200, bun);
            }
        });
        try {
            initDB();
            selectDB();

        } catch (Exception e) {
            Toast.makeText(this, "ERROR!!!", Toast.LENGTH_LONG).show();

        }

        noteAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = null;
                try {
                    db = SQLiteDatabase.openOrCreateDatabase(
                            getFilesDir().getPath() + "/" + "notedb.db",
                            null
                    );
                    String noteTitleStr = noteTitle.getText().toString();
                    String noteDescriptionStr = noteDescription.getText().toString();
                    String ins = "INSERT INTO NOTEDB (title,description)";
                    ins += "VALUES(?,?); ";
                    db.execSQL(ins, new Object[]{noteTitleStr, noteDescriptionStr});
                    Toast.makeText(getApplicationContext(), "Insert is successful", Toast.LENGTH_LONG).show();
                    noteTitle.setText("");
                    noteDescription.setText("");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR!!!", Toast.LENGTH_LONG).show();

                } finally {
                    if (db != null) {
                        db.close();
                        db = null;
                    }
                }
                try {
                    selectDB();
                } catch (Exception e) {

                }
            }
        });

    }

}



