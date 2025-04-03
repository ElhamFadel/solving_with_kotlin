package sudokchecker
// mock sudoku valid 9*9
val validBoard =
    arrayOf(
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
//empty sudocku
val emptyBoard = Array(9) { CharArray(9) { '-' } }

/* Invalid board
** 1- dublicate number in same row
* 2- dublicate number in same column
* 3- dublicate number in same submatrix
* 4- invalid character
* 5- contain out of the range number
* 6- incorrect size board
*
 */
// Invalid sudoku
val invalidRowBoard = arrayOf(
    charArrayOf('5', '3', '-', '3', '7', '-', '-', '-', '-'),
    charArrayOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
    charArrayOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
    charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
    charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
    charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
    charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
    charArrayOf('-', '-', '-', '4', '_', '9', '-', '-', '5'),
    charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
)
val InvalidColumnBoard =
    arrayOf(
        charArrayOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
        charArrayOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
        charArrayOf('6', '9', '8', '-', '-', '-', '-', '6', '-'),
        charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
        charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
        charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
        charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
        charArrayOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
        charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
    )
val InvalidSubMatrixBoard =
    arrayOf(
        charArrayOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
        charArrayOf('6', '-', '3', '1', '9', '5', '-', '-', '-'),
        charArrayOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
        charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
        charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
        charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
        charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
        charArrayOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
        charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
    )
val InvalidCharacterBoard =
    arrayOf(
        charArrayOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
        charArrayOf('6', '-', '3', '1', '9', '5', '-', '-', '-'),
        charArrayOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
        charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
        charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
        charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
        charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
        charArrayOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
        charArrayOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
    )
//9*8
val InvalidIncorrectSizeBoard =
    arrayOf(
        charArrayOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
        charArrayOf('6', '-', '3', '1', '9', '5', '-', '-', '-'),
        charArrayOf('6', '9', '8', '-', '-', '-', '-', '6', '-'),
        charArrayOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
        charArrayOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
        charArrayOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
        charArrayOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
        charArrayOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
    )






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
fun isValidSudoku (board:Array<CharArray>):Boolean{
    if (board.size != 9 || board.any { it.size != 9 }) {
        return false
    }
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

fun runTest(description: String, expectedResult: Boolean,actualResult:Boolean) {
    val testStatus = if (actualResult == expectedResult) "PASSED" else "FAILED"
    val infoResutTest = "$description: $testStatus (Expected: $expectedResult, Got: $actualResult)";
    println(infoResutTest);
}




// function test validator sudocku matrix 3*3
fun testSudockuValidator() {
    // three cases need to check 1- valid sudocku 2- unvalid sudocku 3- empty suducku
    runTest("Valid Sudoku: completed all the condition", true, isValidSudoku(validBoard))
    runTest("Invalid Sudoku: duplicate in row", false, isValidSudoku(invalidRowBoard))
    runTest("Invalid Sudoku: duplicate in column", false, isValidSudoku(InvalidColumnBoard))
    runTest("Invalid Sudoku: duplicate in submatrix", false, isValidSudoku(InvalidSubMatrixBoard))
    runTest("Invalid Sudoku: Empty Sudoku", true, isValidSudoku(emptyBoard))
    runTest("Invalid Sudoku: character in Sudoku", false, isValidSudoku(InvalidCharacterBoard))
    runTest("Invalid Sudoku: Incorrect board size", false, isValidSudoku(InvalidIncorrectSizeBoard))
}


fun main() {
    testSudockuValidator();
}