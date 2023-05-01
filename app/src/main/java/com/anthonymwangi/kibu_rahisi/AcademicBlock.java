package com.anthonymwangi.kibu_rahisi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.HomeFragment;
import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.NotificationFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class   AcademicBlock extends AppCompatActivity {
   BottomNavigationView bottomNavigationView;
    FirebaseFirestore db;
    int values = 0;
    NotificationManagerCompat noti;
    BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Academic block");

        setContentView(R.layout.activity_academic_block);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        replacefragment(new HomeFragment(AcademicBlock.this));
        createNotificationChannel();
        db = FirebaseFirestore.getInstance();
        badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification_menu);
        badgeDrawable.setVisible(true);
        db.collection("ScheduleClass").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
               values = value.size();
               badgeDrawable.setNumber(values);
               NotificationCompat.Builder builder = new NotificationCompat.Builder(AcademicBlock.this, "Kibu_Rahisi");
               builder.setSmallIcon(R.drawable.kibabii_logo);
               builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kibabii_logo));
               builder.setContentTitle("New Class added");
               builder.setPriority(NotificationCompat.PRIORITY_HIGH);

               noti = NotificationManagerCompat.from(AcademicBlock.this);
               noti.notify(1, builder.build());
           }
       });


        bottomNavigationView.setOnNavigationItemSelectedListener(nav);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            values = 0;
            switch (item.getItemId()){
                case R.id.home_menu:
                    replacefragment(new HomeFragment(AcademicBlock.this));
                    return false;
                case R.id.map_menu:
                    //continued implementation
                    return false;
                case R.id.notification_menu:
                    replacefragment(new NotificationFragment(AcademicBlock.this));
                    noti.cancelAll();
                    badgeDrawable.setNumber(values);
                    return  false;
                default:
                    return false;
            }
        }
    };

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence charSequence = "Notification";
            String description = "A new Class added";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Kibu_Rahisi", charSequence, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}