package com.rutvik.apps.tictactoe.game

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rutvik.apps.tictactoe.R
import com.rutvik.apps.tictactoe.board.TicTacToeMarks
import com.rutvik.apps.tictactoe.board.TicTacToeBoard
import com.rutvik.apps.tictactoe.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity(), Game {

    private lateinit var binding: ActivityGameBinding

    private lateinit var gameBoard: TicTacToeBoard

    private lateinit var boxes: ArrayList<ImageView>

    private var movesCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameBoard = TicTacToeBoard(3)

        boxes = arrayListOf(
            binding.boxOne,
            binding.boxTwo,
            binding.boxThree,
            binding.boxFour,
            binding.boxFive,
            binding.boxSix,
            binding.boxSeven,
            binding.boxEight,
            binding.boxNine
        )

        for (i in 0 until boxes.size)
        {
            boxes[i].setOnClickListener {
                gameBoard.playMove(i+1)
                movesCount++
                boxes[i].setImageDrawable(getDrawableForMove(gameBoard.getLastPlayedMove()))
                declareWinner()
            }
        }
    }

    override fun declareWinner() {
        val winner = gameBoard.getWinner()
        if (winner == TicTacToeMarks.X) {
            Toast.makeText(applicationContext, "X winner", Toast.LENGTH_SHORT).show()
            restartGame()
            return
        }
        if (winner == TicTacToeMarks.O) {
            Toast.makeText(applicationContext, "O winner", Toast.LENGTH_SHORT).show()
            restartGame()
            return
        }
        if (movesCount >= 9) {
            restartGame()
            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun restartGame() {
        gameBoard.clear()
        movesCount = 0
        clearBoard(boxes)
    }

    private fun clearBoard(imageViewArray: ArrayList<ImageView>) {
        for (i in imageViewArray) {
            i.setImageResource(android.R.color.transparent)
        }
    }

    private fun getDrawableForMove(move: TicTacToeMarks) : Drawable? {
        return if (move == TicTacToeMarks.X) {
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_x)
        } else {
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_zero)
        }
    }
}