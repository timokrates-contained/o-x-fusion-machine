package de.timokrates.oxfm.pipeline

fun interface Phase<C> {
    suspend fun run(context: C)
}
