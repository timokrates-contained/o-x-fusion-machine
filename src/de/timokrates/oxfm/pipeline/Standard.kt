package de.timokrates.oxfm.pipeline

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

suspend fun <C> pipeline(block: suspend PipelineBuilder<C>.() -> Unit): Pipeline<C> =
        buildPipeline(block)

private suspend fun <C> buildPipeline(block: suspend PipelineBuilder<C>.() -> Unit) =
        Pipeline<C>(flow { PipelineBuilder<C>(this).block() })

class PipelineBuilder<C>(private val collector: FlowCollector<Phase<C>>) {

    //TODO remove after SAM fix
    suspend fun phase(block: suspend (C) -> Unit) {
        phase(object : Phase<C> {
            override suspend fun run(context: C) = block(context)
        })
    }

    suspend fun phase(phase: Phase<C>) {
        collector.emit(phase)
    }
    suspend fun pipeline(block: suspend PipelineBuilder<C>.() -> Unit) {
        collector.emit(buildPipeline(block))
    }
    suspend operator fun Phase<C>.unaryPlus() {
        collector.emit(this)
    }
}
