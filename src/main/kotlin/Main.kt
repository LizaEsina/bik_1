import kotlin.random.Random
fun main() {
    val secretNumber = generateSecretNumber()
    var attempts = 0

    println("Игра 'Быки и Коровы' начинается!")
    println("Угадайте секретное число.")

    do {
        attempts++
        println("Попытка № $attempts")
        val guess = readGuessNumber()

        val (bulls, cows) = calculateBullsAndCows(guess, secretNumber)
        println("Быки: $bulls, Коровы: $cows")

        if (bulls == 4) {
            println("Вы победили! Загаданное число: $secretNumber")
            return
        }

    } while (true)
}
//возвращает строковое значение
fun generateSecretNumber(): String {
    val digits = mutableListOf<Int>()
    while (digits.size < 4) {
        val digit = Random.nextInt(10)// присваивание ей случайного значения от 0 до 9
        if (digits.isEmpty() && digit == 0) {
            continue
        } else if (digit !in digits) {
            digits.add(digit)
        }
    }
    return digits.joinToString("")
//возвращение строки, полученной путем объединения элементов списка `digits` без разделителей
}

fun readGuessNumber(): String {
    println("Введите четырёхзначное число:")
    var guess = readlnOrNull()
    while (guess == null || guess.length != 4 || !guess.matches(Regex("\\d+"))) {
        println("Некорректный ввод. Попробуйте ввести четырёхзначное число:")
        guess = readlnOrNull()
    }
    return guess
}
//мы проходимся по индексам элементов строки
//На каждой итерации проверяем, совпадает ли
// значение элемента строки guess по текущему индексу с соответствующим значением элемента строки secret
fun calculateBullsAndCows(guess: String, secret: String): Pair<Int, Int> {
    var bulls = 0
    var cows = 0

    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            bulls++
        } else if (guess[i] in secret) {
            cows++
        }
    }
    return bulls to cows
}