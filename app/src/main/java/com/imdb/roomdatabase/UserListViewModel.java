package com.imdb.roomdatabase;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserListViewModel extends AndroidViewModel {

    public AppDataBase mAppDatabase;
    public LiveData<List<User>> mUserList;
    public LiveData<User> getContact;

    public UserListViewModel( Application application,long id) {
        super(application);
        mAppDatabase=AppDataBase.getDatabaseInstance(application.getApplicationContext());
        mUserList=mAppDatabase.userDao().getListofUserContacts();
        getContact=mAppDatabase.userDao().getContactByPhone(Utils.mPhone);
    }

    public LiveData<List<User>> getmUserList(){
        return mUserList;
    }

    public LiveData<User> getmUserConatct(){
        return getContact;
    }

    public void deleteContact(User user){
        new DeleteAsyncTask(mAppDatabase).execute(user);
    }

    public class DeleteAsyncTask extends AsyncTask<User,Void,Void>{
        private AppDataBase appDataBase;

        public DeleteAsyncTask(AppDataBase mAppDatabase) {
            appDataBase=mAppDatabase;
        }

        @Override
        protected Void doInBackground(User... users) {
             appDataBase.userDao().deleteContact(users[0]);
             return null;
        }
    }


}
