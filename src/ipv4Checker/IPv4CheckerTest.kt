package ipv4Checker


fun isValidIPv4(ipv:String):Boolean{
    // conditions for correct IPv4
    if(ipv.isEmpty())return false
    // 1- Having 3 dots, 4 digits
    val segments = ipv.split('.')
    if(segments.size !=4) return false
    for (segment in segments ) {
        if (segment.isEmpty()) return false
        if (!segment.all { it.isDigit() }) return false
        // Check if segment start with 0
        if (segment.length > 1 && segment.startsWith("0")) return false
        // Check numeric value
        val num = segment.toInt()
        if (num !in 0..255) return false
    }
    return true;
}



fun runTest(description: String, expectedResult: Boolean,actualResult:Boolean) {
    val testStatus = if (actualResult == expectedResult) "PASSED" else "FAILED"
    val infoResutTest = "$description:(Expected: $expectedResult, Got: $actualResult) -  $testStatus ";
    println(infoResutTest);
}

fun iPvCheckerTest(){
    //valid
    runTest("Valid - Broadcast IP:", true, isValidIPv4("255.255.255.255"))
    runTest("Valid - Default IP:", true, isValidIPv4("0.0.0.0"))
    runTest("Valid - localhost IP:", true, isValidIPv4("127.0.0.1"))
    runTest("Valid - IP in the range:", true, isValidIPv4("8.8.8.8"))
    runTest("Valid - DNS IP:", true, isValidIPv4("172.50.50.50"))

    // Invalid IPs
    runTest("Invalid - Empty String", false, isValidIPv4(""))
    runTest("Invalid - Out of the range", false, isValidIPv4("192.0.2.256"))
    runTest("Invalid - Forgetton Segment", false, isValidIPv4("192.80..0"))
    runTest("Invalid - Invalid number of segments(too short)", false, isValidIPv4("192.50"))
    runTest("Invalid - Invalid number of segments(too long)", false, isValidIPv4("192.50.50.50.50"))
    runTest("Invalid - Negative segment", false, isValidIPv4("192.-50.50.50"))
    runTest("Invalid - prefix zero segment", false, isValidIPv4("192.168.001.1"))
    runTest("Invalid - Segment Has a whitespace ", false, isValidIPv4("192. 50.50"))
    runTest("Invalid - Segment has character not valid", false, isValidIPv4("192.168.1.1$"))
}
fun main(){
    iPvCheckerTest();
}