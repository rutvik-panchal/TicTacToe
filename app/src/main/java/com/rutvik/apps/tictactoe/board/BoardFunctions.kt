package com.rutvik.apps.tictactoe.board

interface BoardFunctions {

    fun playMove(i: Int)

    fun getWinner(): TicTacToeMarks
}