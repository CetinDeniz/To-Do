package com.axuca.todo.viewmodels.provider

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axuca.todo.viewmodels.AddVM
import com.axuca.todo.viewmodels.UpdateVM
import com.axuca.todo.viewmodels.HomeVM

class VMProvider(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVM::class.java)) {
            Log.e("Home VM Provider", " called")
            @Suppress("UNCHECKED_CAST")
            return HomeVM(application) as T
        } else if (modelClass.isAssignableFrom(AddVM::class.java)) {
            Log.e("Add VM Provider", " called")
            @Suppress("UNCHECKED_CAST")
            return AddVM(application) as T
        } else if (modelClass.isAssignableFrom(UpdateVM::class.java)) {
            Log.e("Update VM Provider", " called")
            @Suppress("UNCHECKED_CAST")
            return UpdateVM(application) as T
        }
        throw IllegalArgumentException("Unable to construct ViewModel")
    }
}