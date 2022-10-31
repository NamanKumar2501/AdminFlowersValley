package com.example.adminflowersvalley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adminflowersvalley.fregments.AddFlowerFragment;
import com.example.adminflowersvalley.fregments.AdminFragment;
import com.example.adminflowersvalley.fregments.BannerFragment;
import com.example.adminflowersvalley.fregments.OrderFragment;
import com.example.adminflowersvalley.fregments.ViewAllBannerFragment;
import com.example.adminflowersvalley.fregments.ViewAllFlowerFragment;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private String fragmentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        if (intent != null) {
            fragmentName = intent.getStringExtra("fragment_name");
        }

        switch (fragmentName) {
            case "add_flower": {
                replaceFragment(new AddFlowerFragment());
                break;
            }
            case "view_all_flower": {
                replaceFragment(new ViewAllFlowerFragment());
                break;
            }
            case "banner": {
                replaceFragment(new BannerFragment());
                break;
            }
            case "view_all_banner": {
                replaceFragment(new ViewAllBannerFragment());
                break;
            }
            case "order": {
                replaceFragment(new OrderFragment());
                break;
            }
            case "admin": {
                replaceFragment(new AdminFragment());
                break;
            }
            default: {
                Log.i(TAG, "onCreate: Fragment Name not found.");
            }

        }
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

            super.onActivityResult(requestCode, resultCode, data);
            Log.i(TAG, "onActivityResult: " + requestCode);
            Log.i(TAG, "onActivityResult: " + resultCode);
            Log.i(TAG, "onActivityResult: " + data);

            // List of Banner
            if (requestCode == 101 && data != null && data.getData() != null) {
                Uri mImageUri = data.getData();
                Bundle bundle = new Bundle();
                bundle.putString("image_uri", mImageUri.toString());

                BannerFragment addBannerFragment = new BannerFragment();
                addBannerFragment.setArguments(bundle);
                replaceFragment(addBannerFragment);

                // List of Flowers
            } else if (requestCode == 102 && data != null && data.getData() != null) {
                Uri mImageUri = data.getData();
                Bundle bundle = new Bundle();
                bundle.putString("image_uri", mImageUri.toString());

                AddFlowerFragment addBannerFragment = new AddFlowerFragment();
                addBannerFragment.setArguments(bundle);
                replaceFragment(addBannerFragment);
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}
