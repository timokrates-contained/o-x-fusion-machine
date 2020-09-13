package de.timokrates.oxfm.pipeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

open class Executor<C> {
    open suspend fun execute(phases: Flow<Phase<C>>, context: C) {
        phases.collect {
            it.run(context)
        }
    }
}
