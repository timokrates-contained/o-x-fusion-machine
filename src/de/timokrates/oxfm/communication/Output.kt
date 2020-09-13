package de.timokrates.oxfm.communication

import kotlinx.coroutines.channels.Channel

interface Output : Channel<String>
