package com.example.android.puzzle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.puzzleEngine.PuzzleEngine
import com.example.android.puzzleEngine.PuzzleEngineProtocol
import com.example.android.puzzleEngine.PuzzleMoves
import com.example.android.twozerofoureightapp.MainScreen
import com.example.twozerofoureight.R
import kotlin.math.abs

class MainPuzzleActivity : AppCompatActivity(), PuzzleEngineProtocol {

    private lateinit var gridLayout: GridLayout
    private var tiles = Array(4) { arrayOfNulls<TextView>(4) }
    private var emptyTilePosition = Pair(3, 3)
    private lateinit var puzzleEngine: PuzzleEngine
    private lateinit var BackButton : ImageView
    private lateinit var NewGameButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puzzle_main)

        BackButton = findViewById(R.id.back_button)
        NewGameButton = findViewById(R.id.button)

        NewGameButton.setOnClickListener {
            puzzleEngine.newGame()
        }
        BackButton.setOnClickListener {
            val back = Intent(this, MainScreen::class.java)
            startActivity(back)
        }
        gridLayout = findViewById(R.id.grid_layout)
        puzzleEngine = PuzzleEngine(this)
        initializeTiles()
        puzzleEngine.newGame()
    }


    private fun initializeTiles() {
        for (i in 0 until gridLayout.childCount) {
            val view = gridLayout.getChildAt(i)
            if (view is TextView) {
                val id = resources.getResourceEntryName(view.id)
                val num = id.substring(5).toIntOrNull() ?: 0
                val text = if (num in 1..15) num.toString() else ""
                view.text = text
                tiles[num / 4][num % 4] = view
                view.setOnClickListener { moveTile(num / 4, num % 4) }
            }
        }
    }

    private fun moveTile(row: Int, col: Int) {
        val (emptyRow, emptyCol) = emptyTilePosition
        if ((row == emptyRow && abs(col - emptyCol) == 1) || (col == emptyCol && abs(row - emptyRow) == 1)) {
            tiles[emptyRow][emptyCol]?.text = tiles[row][col]?.text
            tiles[row][col]?.text = ""
            emptyTilePosition = Pair(row, col)
            puzzleEngine.actionMove(getMoveDirection(row, col))
        }
    }

    private fun getMoveDirection(row: Int, col: Int): PuzzleMoves {
        val (emptyRow, emptyCol) = emptyTilePosition
        return when {
            row < emptyRow -> PuzzleMoves.Down
            row > emptyRow -> PuzzleMoves.Up
            col < emptyCol -> PuzzleMoves.Right
            col > emptyCol -> PuzzleMoves.Left
            else -> PuzzleMoves.Up
        }
    }

    override fun updateTileValue(move: PuzzleEngine.Transition) {
        val row = move.row
        val col = move.col
        val value = move.value
        tiles[row][col]?.text = if (value == 0) "" else value.toString()
    }

    override fun userWin() {
        Toast.makeText(this, resources.getString(R.string.winner_toast_message), Toast.LENGTH_LONG).show()
    }

}
