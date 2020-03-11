package com.imdb.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.imdb.roomdatabase.databinding.ActivityDetailsBinding;
import com.imdb.roomdatabase.databinding.ActivityDetailsBindingImpl;

import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityDetailsBinding activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        final AddUserViewModel addUserViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication(), 2)).get(AddUserViewModel.class);
        final UserListViewModel userListViewModel = ViewModelProviders.of(this, new ViewModelFactory(getApplication(), 2)).get(UserListViewModel.class);
        userListViewModel.getmUserConatct().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
                activityDetailsBinding.setUser(user);

            }
        });
        //Toolbar
        Toolbar toolbar = activityDetailsBinding.mToolbar;
        setSupportActionBar(toolbar);
        activityDetailsBinding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (addUserViewModel.isContactExist(activityDetailsBinding.txtUserphoneTitle.getText().toString())) {
                        User user = new User(activityDetailsBinding.txtUsername.getText().toString(), activityDetailsBinding.mUserEmail.getText().toString(), activityDetailsBinding.txtUserphoneTitle.getText().toString(), activityDetailsBinding.txtUsercity.getText().toString());
                        addUserViewModel.updateContact(user);
                        Toast.makeText(getApplicationContext(), R.string.user_updated, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        activityDetailsBinding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (addUserViewModel.isContactExist(Utils.mPhone)) {
                        userListViewModel.deleteContact(mUser);
                        finish();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
