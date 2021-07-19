package com.r2solution.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r2solution.convidados.service.model.GuestModel
import com.r2solution.convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getRepository(mContext)
    
    
    private val mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest = mSaveGuest

    fun save(name: String , presence: Boolean){
        val guestModel: GuestModel = GuestModel(name = name, presence = presence)
        mSaveGuest.value = mGuestRepository.save(guestModel)
    }
}