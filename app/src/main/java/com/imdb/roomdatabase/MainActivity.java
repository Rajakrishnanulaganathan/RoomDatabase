package com.imdb.roomdatabase;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imdb.roomdatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private UserListViewModel userListViewModel;
    private AddUserViewModel addUserViewModel;
    private UserListAdapter userListAdapter;
    private FloatingActionButton floatingActionButton;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Intiate View Here
        initView(activityMainBinding);

        //ViewModel
        userListViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), 1)).get(UserListViewModel.class);
        addUserViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), 1)).get(AddUserViewModel.class);
        userListViewModel.getmUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userListAdapter.setList(users);
            }
        });
        activityMainBinding.setPresenter(this);

    }

    private void initView(ActivityMainBinding activityMainBinding) {
        //Toolbar
        Toolbar toolbar = activityMainBinding.mToolbar;
        setSupportActionBar(toolbar);
        //RecyclerView
        RecyclerView recyclerView = activityMainBinding.mRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adapter
        userListAdapter = new UserListAdapter(getApplicationContext(), new ArrayList<User>());
        recyclerView.setAdapter(userListAdapter);

        floatingActionButton = activityMainBinding.mFab;
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabclicked();
            }
        });


    }

    public void fabclicked() {
        showDialog(this);
    }

    public void showDialog(Activity activity) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_add_user);

        final EditText mUserNameEditText = dialog.findViewById(R.id.mUsername);
        final EditText mUserEmailEditText = dialog.findViewById(R.id.mUserEmail);
        final EditText mUserPhoneEditText = dialog.findViewById(R.id.mUserPhone);
        final EditText mUserCityEditText = dialog.findViewById(R.id.mUserCity);
        Button mAddButton = dialog.findViewById(R.id.mButtonAdd);
        Button mCloseButton = dialog.findViewById(R.id.mButtonClose);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserNameEditText.getText().toString().trim() != null && !mUserNameEditText.getText().toString().trim().isEmpty() && !mUserEmailEditText.getText().toString().trim().isEmpty() && mUserEmailEditText.getText().toString().trim() != null && !mUserPhoneEditText.getText().toString().trim().isEmpty() && mUserPhoneEditText.getText().toString().trim() != null && !mUserCityEditText.getText().toString().trim().isEmpty() && mUserCityEditText.getText().toString().trim() != null) {
                    User user = new User(mUserNameEditText.getText().toString(), mUserEmailEditText.getText().toString(), mUserPhoneEditText.getText().toString(), mUserCityEditText.getText().toString());
                    addUserViewModel.addContact(user);
                    Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_LONG).show();
                }
            }
        });
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
