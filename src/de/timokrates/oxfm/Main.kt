package de.timokrates.oxfm

import de.timokrates.oxfm.context.Input
import de.timokrates.oxfm.pipeline.pipeline
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    pipeline<Input> {
        repeat(100) {
            pipeline {
                repeat(1000) {
                    phase {
                        if (it.consumed) return@phase

                        it.collect {
                            print(it)
                        }
                        it.consume()
                        println()
                    }
                }
            }
        }
    }.execute(Input(flow {
        repeat(100) {
            emit(it.toString() + "_")
        }
    }))
}
