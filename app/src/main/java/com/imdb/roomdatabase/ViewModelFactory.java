package com.imdb.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import java.util.Map;

import javax.inject.Provider;

public class ViewModelFactory  extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    private final long id;

    public ViewModelFactory(@NonNull Application application, long id) {
        this.application = application;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == UserListViewModel.class) {
            return (T) new UserListViewModel(application, id);
        }
        else {
            return (T) new AddUserViewModel(application, id);

        }
    }
}