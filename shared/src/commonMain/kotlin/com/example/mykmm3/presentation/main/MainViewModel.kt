package com.example.mykmm3.presentation.main

import com.example.mykmm3.presentation.main.MainAction.*
import com.example.mykmm3.presentation.main.MainIntent.*
import com.example.mykmm3.presentation.main.MainResult.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel(private val processor: MainProcessor) {

    private val intentChannel = Channel<MainIntent> {  }

    private val intentPub: String = ""
//
//    val state: Flow<MainState> by lazy {
//
//    }

    private fun actionFrom(intent: MainIntent): MainAction {
        return when (intent) {
            is OnResume -> LoadLaunch
            is OnTap -> SkipMe
        }
    }

    private fun reducer(prevState: MainState, result: MainResult): MainState {
        val nextState: MainState = prevState.copy(error = null, isLoading = false)
        return when (result) {
            is DoneLoad -> nextState.copy(launchState = result.launchData)
            is Failed -> nextState.copy(error = result.error)
        }
    }
}