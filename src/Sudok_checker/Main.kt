package Sudok_checker

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.



fun main() {
    fun isValidation(value:Char,r:Int,c:Int,board:Array<CharArray>):Boolean{
        // Three validation
        // check the row
        for(j in 0 until 9){
            if(j!=c && board[r][j] == value){
                return false;
            }
        }
        // check the columns
        for(i in 0 until 9){
            if(i!=r && board[i][c] == value){
                return false;
            }
        }
        // check the submatrix
        // where subnatrix start
        val rowStartsubMatrix= 3* (r/3);
        val columStartsubMatrix= 3* (c/3);
        val rowEndsubMatrix= rowStartsubMatrix + 3;
        val colEndsubMatrix= columStartsubMatrix + 3;
        for(i in rowStartsubMatrix until rowEndsubMatrix){
            for(j in columStartsubMatrix until  colEndsubMatrix){
                if (i != r && j != c && board[i][j] == value) {
                    return false
                }
            }
        }

        return true;
    }
    // Hello world
    fun isValidSudoku (board:Array<CharArray>):Boolean{
        // check for every cell indvidual
        for(i in 0 until 9){
            for(j in 0 until 9){
                if(board[i][j] != '-'){
                    if (!isValidation(board[i][j], i, j, board)) {
                        return false
                    }
                }


            }

        }
        return true;
    }







    // function test validator sudocku matrix 3*3
    fun testSudockuValidator() {
        // three cases need to check 1- valid sudocku 2- unvalid sudocku 3- empty suducku
        //valid sudocku
        val validBoard = arrayOf(
            charArrayOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
            charArrayOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
            charArrayOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
            charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
            charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
            charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
            charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
            charArrayOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
            charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
        )
        //invalid sudocku
        val invalidBoard = arrayOf(
            charArrayOf('5', '3', '-', '3', '7', '-', '-', '-', '-'),
            charArrayOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
            charArrayOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
            charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
            charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
            charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
            charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
            charArrayOf('-', '-', '-', '4', '4', '9', '-', '-', '5'),
            charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
        )
        //empty sudocku
        val emptyBoard = Array(9) { CharArray(9) { '-' } }

        // testing process
        fun testOperation(description: String, expectedResult: Boolean, board: Array<CharArray>) {
            val actualResult = isValidSudoku(board);
            val testStatus = if (actualResult == expectedResult) "PASSED" else "FAILED"
            val infoResutTest = "$description: $testStatus (Expected: $expectedResult, Got: $actualResult)";
            println(infoResutTest);
        }

        //Here Run my test
        testOperation("1. Valid Sudoku", true, validBoard)
        testOperation("2. Invalid Sudoku", false, invalidBoard)
        testOperation("3. Empty Sudoku", true, emptyBoard)
    }

    testSudockuValidator();
}