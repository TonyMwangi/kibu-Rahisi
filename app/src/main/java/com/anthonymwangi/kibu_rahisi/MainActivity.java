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
import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.UserHomeFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class   MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseFirestore db;
    int values = 0;
    NotificationManagerCompat noti;
    BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }
        getSupportActionBar().setTitle("Academic block");

        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        replacefragment(new UserHomeFragment(MainActivity.this));
        createNotificationChannel();
        db = FirebaseFirestore.getInstance();
        badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notification_menu);
        badgeDrawable.setVisible(true);
        db.collection("ScheduleClass").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                values = value.size();
                badgeDrawable.setNumber(values);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Kibu_Rahisi");
                builder.setSmallIcon(R.drawable.kibabii_logo);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kibabii_logo));
                builder.setContentTitle("New Class added");
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                noti = NotificationManagerCompat.from(MainActivity.this);
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
                    replacefragment(new UserHomeFragment(MainActivity.this));
                    return false;
                case R.id.map_menu:
                    //continued implementation
                    return false;
                case R.id.notification_menu:
                    replacefragment(new NotificationFragment(MainActivity.this));
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
        fragmentTransaction.replace(R.id.mainActivityframeLayout,fragment);
        fragmentTransaction.commit();
    }
}



//package com.anthonymwangi.kibu_rahisi;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.firebase.auth.FirebaseAuth;
//
//public class MainActivity extends AppCompatActivity {
//    Button logoutBtn;
//    CardView cardLectureRoom,cardLaboratory, cardOffice, cardWashRoom;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getSupportActionBar().setTitle("Academic Block");
//
//        setContentView(R.layout.activity_main);
//
//        logoutBtn = findViewById(R.id.logoutBtn);
//        cardLectureRoom = findViewById(R.id.cardLectureRoom);
//        cardLaboratory = findViewById(R.id.cardLaboratory);
//        cardOffice = findViewById(R.id.cardOffice);
//        cardWashRoom = findViewById(R.id.cardWashRoom);
//
//        cardLectureRoom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Category.class));
//            }
//        });
//        cardLaboratory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Laboratory.class));
//            }
//        });
//
//        cardOffice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Office.class));
//            }
//        });
//        cardWashRoom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Washroom.class));
//            }
//        });
//
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), Login.class));
//                finish();
//            }
//        });
//    }
//}