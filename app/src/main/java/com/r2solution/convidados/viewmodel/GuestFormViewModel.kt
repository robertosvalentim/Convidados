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

    private val mGuest = MutableLiveData<GuestModel>()
    val guest = mGuest

    fun save(id: Int, name: String , presence: Boolean){
        val guestModel: GuestModel = GuestModel(id, name, presence)

        if(id == 0){
            mSaveGuest.value = mGuestRepository.save(guestModel)
        }else{
            mSaveGuest.value = mGuestRepository.update(guestModel)
        }


    }

    fun load(id: Int){
        mGuest.value = mGuestRepository.get(id)
    }
}