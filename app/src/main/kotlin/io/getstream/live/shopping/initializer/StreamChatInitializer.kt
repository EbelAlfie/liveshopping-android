/*
 * Copyright 2024 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Stream License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.live.shopping.initializer

import android.content.Context
import androidx.startup.Initializer
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.models.User
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import io.getstream.live.shopping.BuildConfig
import io.getstream.log.streamLog

/**
 * StreamChatInitializer initializes all Stream Client components.
 */
class StreamChatInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    streamLog { "StreamChatInitializer is initialized" }

    /**
     * initialize a global instance of the [ChatClient].
     * The ChatClient is the main entry point for all low-level operations on chat.
     * e.g, connect/disconnect user to the server, send/update/pin message, etc.
     */
    val logLevel = if (BuildConfig.DEBUG) ChatLogLevel.ALL else ChatLogLevel.NOTHING
    val offlinePluginFactory = StreamOfflinePluginFactory(
      appContext = context
    )
    val statePluginFactory = StreamStatePluginFactory(
      config = StatePluginConfig(
        backgroundSyncEnabled = true,
        userPresence = true
      ),
      appContext = context
    )
    val chatClient = ChatClient.Builder(BuildConfig.STREAM_API_KEY, context)
      .withPlugins(offlinePluginFactory, statePluginFactory)
      .logLevel(logLevel)
      .build()

    val chatUser = User(
      id = myId,
      name = myName
    )
    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiY2FjaW5nIn0.6bCbPSmbCAyrt8M3G3nzJ3tGw9-1tpTfKLUaTjG4Onk"
    chatClient.connectUser(chatUser, myToken).enqueue()
  }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    listOf(StreamLogInitializer::class.java)
}
