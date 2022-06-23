package com.girayserter.rickandmortyvlmediatask.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.girayserter.rickandmortyvlmediatask.data.api.model.Result
import com.girayserter.rickandmortyvlmediatask.data.repository.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersPageViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
):ViewModel() {
    private val _state = MutableStateFlow(emptyList<Result>())
    private val _lastPageLoaded = MutableStateFlow(false)
    val state: StateFlow<List<Result>>
        get() = _state
    val lastPageLoaded:StateFlow<Boolean>
        get() = _lastPageLoaded
    var loadedPages=0

    init {
        viewModelScope.launch {
            loadNextPage()
        }
    }

    suspend fun loadNextPage(){
        if(_lastPageLoaded.value) {
            return
        }
        val characters= characterRepo.getCharacters(loadedPages+1)
        loadedPages++
        _state.value+=characters
        if (loadedPages==42){
            _lastPageLoaded.value=true
        }else {
            loadNextPage()
        }
    }



}