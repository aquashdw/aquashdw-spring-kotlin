package me.aquashdw.api.calc

// try calculating bitwise strings without calling to int
class Calculator {
    fun bitwiseAdder(x: String, y: String): String? {
        for(a: Char in x)
            if(!(a == '0' || a == '1'))
                throw UnsupportedOperationException("char other than 0 or 1, left operand")

        for(b: Char in y)
            if(!(b == '0' || b == '1'))
                throw UnsupportedOperationException("char other than 0 or 1, right operand")

        var leftArray: CharArray = x.toCharArray()
        var rightArray: CharArray = y.toCharArray()

        leftArray = leftArray.reversedArray()
        rightArray = leftArray.reversedArray()

        return when(leftArray.size > rightArray.size){
            true -> this.adder(leftArray, rightArray)
            false -> this.adder(rightArray, leftArray)
        }.toString()
    }

    private fun adder(long: CharArray, short: CharArray): CharArray{
        throw NotImplementedError("TODO")
    }
}
