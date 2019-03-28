import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    // Read from a file and turn it into a string
    if(args.isEmpty()){
        println("Please provide a .txt file")
    } else {
        val inputStream: InputStream = File(args[0]).inputStream()

        var allTheWords = inputStream.bufferedReader().use { it.readText() }

        // Make a list of the words separated out
        val regexWord = """[^a-zA-Z]\W+""".toRegex()
        val regexEnd = """[\.|\n]""".toRegex()
        var words = allTheWords.toLowerCase().replace(regexEnd, " ").replace(regexWord, " ").split(" ")

        // Get a counted map of all the words
        val mappedWords = mutableMapOf<String, Int>()
        for (word in words) {
            if (word != "") {
                if (mappedWords[word] == null) {
                    mappedWords[word] = 1
                } else {
                    val currentWordCount = mappedWords[word]!!
                    mappedWords[word] = currentWordCount + 1
                }
            }
        }

        // Convert map to list
        val wordList = mappedWords.toList()

        // Sort the list
        val sortedList = wordList.sortedWith(compareByDescending { it.second })

        // Print a sorted list of the most popular words
        for (word in sortedList) {
            println("${word.first} - ${word.second}")
        }
    }
}