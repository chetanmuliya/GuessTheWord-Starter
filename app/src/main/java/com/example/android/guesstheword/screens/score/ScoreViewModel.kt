package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalscore :Int) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score;

    private val _eventplayAgain = MutableLiveData<Boolean>()
    val eventplayAgain : LiveData<Boolean>
        get() = _eventplayAgain

    fun onPlayAgain(){
        _eventplayAgain.value = true
    }
    fun onPlayAgainFinished(){
        _eventplayAgain.value = false
    }

    init {
        _score.value = finalscore
    }

    init {
        Log.i("ScoreViewModel", "Final score is $finalscore")
    }
}