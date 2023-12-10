var accountBalance: Int = 0
var money:Int = 0
var accountType: String = ""

fun createAccount(){
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var userChoice: Int

    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = readln().toInt()
        println("The selected option is ${userChoice}.")

        accountType = when (userChoice) {
            1 -> "debit"
            2 -> "credit"
            3 -> "checking"
            else -> continue
        }
    }

    Runtime.getRuntime().exec("cls")

    println("\nYou have created a $accountType account.")

    val accountBalance = (0..1000).random()
    println("\nThe current balance is $accountBalance dollars.")

}

fun withdraw(amount: Int): Int {
    accountBalance -= amount
    println("You successfully withdrew $amount dollars." +
            " The current balance is $accountBalance dollars.")
    return amount
}

fun debitWithdraw(amount: Int): Int {
    return if (accountBalance == 0) {
        println("Can't withdraw, no money on this account!")
        accountBalance
    } else if (amount > accountBalance) {
        println("Not enough money on this account!" +
                " The current balance is $accountBalance dollars.")
        0
    } else {
        withdraw(amount)
    }
}

fun deposit(amount: Int): Int {
    accountBalance += amount
    println("You successfully deposited $amount dollars." +
            " The current balance is $accountBalance dollars.")
    return amount
}

fun creditDeposit(amount: Int): Int {
    return if (accountBalance == 0) {
        println("This account is completely paid off! Do not deposit money!")
        accountBalance
    } else if (accountBalance + amount > 0) {
        println("Deposit failed, you tried to pay off an amount greater than the credit balance." +
                " The checking balance is $accountBalance dollars.")
        0
    } else if (amount == -accountBalance) {
        accountBalance = 0
        println("You have paid off this account!")
        amount
    } else {
        deposit(amount)
    }
}

fun transfer(mode: String) {
    val transferAmount: Int

    println("\nEnter amount of Money you would to transfer !")
    money= readln().toInt()
    println("\nThe amount you transferred is $money dollars.")

    when (mode) {
        "withdraw" -> {
            transferAmount = if (accountType == "debit") {
                debitWithdraw(money)
            } else {
                withdraw(money)
            }
            println("The amount you withdrew is $transferAmount dollars.")
        }
        "deposit" -> {
            transferAmount = if (accountType == "credit") {
                creditDeposit(money)
            } else {
                deposit(money)
            }
            println("The amount you deposited is $transferAmount dollars.")
        }
        else -> return
    }
}

fun main() {

    println("Welcome to your banking system.")
    createAccount()

    var isSystemOpen = true
    var option: Int

    do {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        println("Which option do you choose? (1, 2, 3 or 4)")

        option = readln().toInt()
        println("The selected option is $option.")

        Runtime.getRuntime().exec("cls")

        when (option) {
            1 -> println("\nThe current balance is $accountBalance dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }while (isSystemOpen)

}

