package com.r2solution.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r2solution.convidados.service.model.GuestModel
import com.r2solution.convidados.service.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList
    
    fun load(){
        mGuestList.value = mGuestRepository.getAll()
    }

    fun delete(id:Int){
        mGuestRepository.delete(id)
    }
}