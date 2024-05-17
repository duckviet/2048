package com.example.android.puzzleEngine

import java.io.Serializable
import kotlin.collections.ArrayList
import kotlin.math.abs
import java.util.Random

interface PuzzleEngineProtocol {
    fun updateTileValue(move: PuzzleEngine.Transition)
    fun userWin()
}

enum class PuzzleMoves { Up, Down, Left, Right }

enum class TileMoveType { Slide, Reset }

open class PuzzleEngine(private var delegate: PuzzleEngineProtocol) : Serializable {

    private val gridCount = 15
    private val rowCnt = 3
    private val colCnt = 3
    private val blankTile = 0

    private var gameOver = false
    private var tiles: ArrayList<Int> = ArrayList()

    private val rand = Random()

    open fun newGame() {
        tiles.clear()
        for (i in 1..15) {
            tiles.add(i)
        }

        tiles.shuffle()
        gameOver = false
        applyGameMoves()
    }

    fun actionMove(move: PuzzleMoves) {
        if (!gameOver) {
            when (move) {
                PuzzleMoves.Up -> moveTile(-3)
                PuzzleMoves.Down -> moveTile(3)
                PuzzleMoves.Left -> moveTile(-1)
                PuzzleMoves.Right -> moveTile(1)
            }
            checkWin()
        }
    }
    fun shuffleTiles() {
        if (!gameOver) {
            tiles.shuffle()
            applyGameMoves()
        }
    }


    private fun moveTile(offset: Int) {
        val blankIndex = tiles.indexOf(blankTile)
        val newIndex = blankIndex + offset
        val rowDiff = abs((blankIndex / colCnt) - (newIndex / colCnt))
        val colDiff = abs((blankIndex % colCnt) - (newIndex % colCnt))

        // Ensure the newIndex is within grid bounds and only moves horizontally or vertically
        if (newIndex in 0 until gridCount && (rowDiff == 1 && colDiff == 0 || rowDiff == 0 && colDiff == 1)) {
            tiles[blankIndex] = tiles[newIndex]
            tiles[newIndex] = blankTile
            applyGameMoves()
        }
    }


    private fun checkWin() {
        if (!gameOver && tiles == (1..15).toList() + listOf(0)) {
            gameOver = true
            delegate.userWin()
        }
    }

    private fun applyGameMoves() {
        for (i in 0 until gridCount) {
            val value = tiles[i]
            val row = i / colCnt
            val col = i % colCnt
            delegate.updateTileValue(PuzzleEngine.Transition(TileMoveType.Slide, value, row, col))
        }
    }

    class Transition(
        var action: TileMoveType,
        var value: Int,
        var row: Int,
        var col: Int
    ) : Serializable
}
