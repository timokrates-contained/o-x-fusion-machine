fun interface Action {
    suspend fun run()
}

suspend fun runAction(a: Action) {
    a.run()
}

suspend fun main() {
    runAction {
        print("test")
    }
}
