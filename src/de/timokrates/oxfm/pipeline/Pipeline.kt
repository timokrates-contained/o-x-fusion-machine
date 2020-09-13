package de.timokrates.oxfm.pipeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

open class Pipeline<C>(
        private val phases: Flow<Phase<C>> = emptyFlow(),
        private val executor: Executor<C> = Executor()
) : Phase<C> {

    suspend fun execute(context: C): C {
        run(context)
        return context
    }

    override suspend fun run(context: C) {
        executor.execute(phases, context)
    }

}
