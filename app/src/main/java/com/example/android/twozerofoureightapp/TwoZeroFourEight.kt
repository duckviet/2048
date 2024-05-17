package com.example.android.twozerofoureightapp

import com.example.android.engine.GameEngine
import com.example.android.engine.GameEngineProtocol
import java.util.*

/**
 * Grid is from bottom left to right, bottom to top.
 */
class TwoZeroFourEight(delegated: GameEngineProtocol) : GameEngine(delegate = delegated) {

    var startedPlaying : Date
        private set
    var startLastGame : Date
        private set

    init {
        this.startedPlaying = Date()
        this.startLastGame = this.startedPlaying
    }

    override fun newGame(newHighScore: Int) {
        super.newGame(newHighScore)
        this.startLastGame = Date()
    }

    fun getTotalTimePlaying() = (Date().time - startedPlaying.time)
    fun getTimePlayingCurrent() = (Date().time - startLastGame.time)
}