package com.example.adminflowersvalley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private final String mobile = "";
    public CardView add_flower,view_all_flower,banner,view_all_banner,order,admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_flower=findViewById(R.id.add_flower);
        view_all_flower=findViewById(R.id.view_all_flower);
        banner=findViewById(R.id.banner);
        view_all_banner=findViewById(R.id.view_all_banner);
        order=findViewById(R.id.order);
        admin=findViewById(R.id.admin);




        add_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","add_flower");
                startActivity(intent);
            }
        });
        view_all_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","view_all_flower");
                startActivity(intent);
            }
        });
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","banner");
                startActivity(intent);
            }
        });
        view_all_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","view_all_banner");
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","order");
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("fragment_name","admin");
                startActivity(intent);
            }
        });


    }

    void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}

