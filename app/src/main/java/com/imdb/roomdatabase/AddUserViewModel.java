package com.imdb.roomdatabase;


import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutionException;

public class AddUserViewModel extends AndroidViewModel {

    public AppDataBase mAppDatabase;



    public AddUserViewModel(Application application,long id) {
        super(application);
        mAppDatabase=AppDataBase.getDatabaseInstance(application.getApplicationContext());

    }


    public void addContact(User user){
        new AddAsyncTask(mAppDatabase).execute(user);
    }

    public void updateContact(User user){
        Log.d("uPATW","CALLED");
        Log.d("uPATMEDD",user.mUserPhone);
        new UpdateAsyncTask(mAppDatabase).execute(user);
    }

    public Boolean isContactExist(String phone) throws ExecutionException, InterruptedException {
        return  new IsContactExistAsynctask(mAppDatabase).execute(phone).get();
    }


    public class AddAsyncTask extends AsyncTask<User,Void,Void>{
        private AppDataBase appDataBase;

        public AddAsyncTask(AppDataBase mAppDatabase) {
            this.appDataBase=mAppDatabase;
        }

        @Override
        protected Void doInBackground(User... users) {
             appDataBase.userDao().addcontact(users[0]);
             return null;
        }
    }
    public class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private AppDataBase appDataBase;

        public UpdateAsyncTask(AppDataBase mAppDatabase) {
            this.appDataBase=mAppDatabase;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDataBase.userDao().updateContact(users[0]);
            return null;
        }
    }

    public class IsContactExistAsynctask  extends AsyncTask<String,Boolean,Boolean>{
        private AppDataBase appDataBase;

        public IsContactExistAsynctask(AppDataBase mAppDatabase) {
             appDataBase=mAppDatabase;
        }


        @Override
        protected Boolean doInBackground(String... strings) {

            if (appDataBase.userDao().getContactByPhone(strings[0]) != null)
                return true;
            else
                return false;

        }
    }


}
