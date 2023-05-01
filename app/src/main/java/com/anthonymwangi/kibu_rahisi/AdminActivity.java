package com.anthonymwangi.kibu_rahisi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    Button logoutBtn;
    CardView cardAcademicBlock;
    String isAdmin, isUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        isAdmin = getIntent().getStringExtra("isAdmin");
        isUser = getIntent().getStringExtra("isUser");

//        logoutBtn = findViewById(R.id.logoutBtn);
        cardAcademicBlock = findViewById(R.id.cardAcademicBlock);
        cardAcademicBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isAdmin !=null) {
                    startActivity(new Intent(getApplicationContext(), AcademicBlock.class));
                }else if(isUser !=null) {
                    startActivity(new Intent(AdminActivity.this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(AdminActivity.this, Login.class));
                }
            }
        });

//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), Login.class));
//                finish();
//            }
//        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isAdmin = getIntent().getStringExtra("isAdmin");
        isUser = getIntent().getStringExtra("isUser");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Login Out", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}