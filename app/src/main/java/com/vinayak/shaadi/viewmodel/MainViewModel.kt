package com.vinayak.shaadi.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinayak.shaadi.api.MainRepository
import com.vinayak.shaadi.db.ProfileRepository
import com.vinayak.shaadi.model.Profile
import com.vinayak.shaadi.utils.Resource
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository,private val profileRepository: ProfileRepository) :
    ViewModel() {
    private val _profiles = MutableLiveData<Resource<List<Profile>>>()
    val profiles : LiveData<Resource<List<Profile>>>
        get() = _profiles


    init {
        getProfiles()
    }

    fun getProfiles()  = viewModelScope.launch {
        _profiles.postValue(Resource.loading(null))
        try {
            mainRepository.getProfiles().let {
                if (it.isSuccessful) {
                    _profiles.postValue(Resource.success(it.body()))
                } else {
                    _profiles.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }catch (e: UnknownHostException){
            _profiles.postValue(Resource.error("No Internet Connection",null))
        }
    }

    fun insertProfiles(list: List<Profile>) = viewModelScope.launch {
        profileRepository.insertProfiles(list)
    }

    fun getOfflineProfiles() = profileRepository.profiles
}