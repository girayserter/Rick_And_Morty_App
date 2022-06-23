package com.girayserter.rickandmortyvlmediatask.ui.characterDetailPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girayserter.rickandmortyvlmediatask.data.api.model.Location
import com.girayserter.rickandmortyvlmediatask.data.api.model.Origin
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result
import com.girayserter.rickandmortyvlmediatask.data.repository.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
): ViewModel() {


    private var _characterInfo:MutableStateFlow<Result> =MutableStateFlow(createEmptyResult())
    val characterInfo: StateFlow<Result>
        get() = _characterInfo

    suspend fun getCharacterInfo(characterId: Int){
        _characterInfo.value= characterRepo.getCharacterById(characterId)
    }

    private fun createEmptyResult():Result{
        return Result("", emptyList(),"",0,"", Location("",""),"", Origin("",""),"","","","")
    }
}