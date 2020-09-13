package de.timokrates.oxfm.context

import kotlinx.coroutines.flow.Flow

class Input(content: Flow<String>) : Flow<String> by content {

    var consumed = false
        private set

    fun consume() {
        consumed = true
    }
}
