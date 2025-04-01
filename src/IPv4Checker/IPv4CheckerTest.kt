package IPv4Checker


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



fun testOperation(description: String, expectedResult: Boolean,actualResult:Boolean) {
    val testStatus = if (actualResult == expectedResult) "PASSED" else "FAILED"
    val infoResutTest = "$description: $testStatus (Expected: $expectedResult, Got: $actualResult)";
    println(infoResutTest);
}

fun IPvCheckerTest(){
    val validIPv4 = "192.168.1.1";
    val invalidLengthIPv4= "192.168.1.1.1";
    val invalidNotDigitIPv4= "192.168.1.aa";
    val invalidMissingIPv4= "192.168.1..1";
    val invalidRangeIPv4= "265.168.1.1.1";
    val invalidPrefixIPv4= "192.168.001.1";
    testOperation("Valid IPv4", true, isValidIPv4(validIPv4))
    testOperation("Invalid IPv4", false, isValidIPv4(invalidLengthIPv4))
    testOperation("Not digit  IPv4", false, isValidIPv4(invalidNotDigitIPv4))
    testOperation("Missing segment ", false, isValidIPv4(invalidMissingIPv4))
    testOperation("Invalid range", false, isValidIPv4(invalidRangeIPv4))
    testOperation("invalid Prefix IPv4", false, isValidIPv4(invalidPrefixIPv4))
}
fun main(){
    IPvCheckerTest();
}