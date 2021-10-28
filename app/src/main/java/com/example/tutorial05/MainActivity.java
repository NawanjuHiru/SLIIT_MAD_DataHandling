package com.example.tutorial05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial05.database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_username, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
    }

    public void saveUser(View view){
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();
        DBHelper dbHelper = new DBHelper(this);

        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Enter values", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.addInfo(username,password);
        }
    }

    public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);

        List info = dbHelper.readAll();

        String[] infoArray = (String[]) info.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Users Details");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String username = infoArray[i].split(":")[0];
                //Toast.makeText(MainActivity.this, "username", Toast.LENGTH_SHORT).show();

                edt_username.setText(username);
                edt_password.setText("**********");
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteUser(View view){
        DBHelper dbHelper = new DBHelper(this);

        String username = edt_username.getText().toString();
        if (username.isEmpty()){
            Toast.makeText(this, "Select a User", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.deleteInfo(username);
            Toast.makeText(this, username+" is deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUser(View view){
        DBHelper dbHelper = new DBHelper(this);
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();

        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Select or type User", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.updateInfo(view,username,password);
        }
    }
}