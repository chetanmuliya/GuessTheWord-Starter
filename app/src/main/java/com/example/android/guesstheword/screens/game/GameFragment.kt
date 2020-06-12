/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
const val  KEY_WORD = "KEY_WORD";
const val  KEY_SCORE = "KEY_SCORE";
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel



    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })

        /*viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord.toString()
        })*/
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { isGameFinished ->
            if(isGameFinished)
                onEndGame()
        })
        /*if (savedInstanceState!=null){
            score = savedInstanceState.getInt(KEY_SCORE,0)
            word = savedInstanceState.getString(KEY_WORD,"default")
        }*/
        //resetList()
        //nextWord()


        return binding.root

    }

    private fun onEndGame() {
        Toast.makeText(activity,"Game is finished",Toast.LENGTH_LONG).show()
        val action = GameFragmentDirections.actionGameToScore();
        val score = viewModel.score.value?:0
        action.score = score
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onGameFinishedCompletely()
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       /* outState.putString(KEY_WORD,word)
        outState.putInt(KEY_SCORE,score)*/
    }
    /** Methods for buttons presses **/





}
