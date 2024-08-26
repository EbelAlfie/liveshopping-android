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
import io.getstream.live.shopping.BuildConfig
import io.getstream.live.shopping.CredentialsAudience
import io.getstream.live.shopping.CredentialsHost
import io.getstream.live.shopping.LiveShoppingApp
import io.getstream.log.streamLog
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.core.notifications.NotificationConfig
import io.getstream.video.android.model.User

class StreamVideoInitializer : Initializer<Unit> {

  override fun create(context: Context) {
    streamLog { "StreamVideoInitializer is initialized" }
    if (LiveShoppingApp.HOST) {
      setCredentialAsHost(context)
    } else {
      setCredentialAsAudience(context)
    }
  }

  private fun setCredentialAsAudience(context: Context) {
    StreamVideoBuilder(
      context = context,
      apiKey = BuildConfig.STREAM_API_KEY,
      notificationConfig = NotificationConfig(hideRingingNotificationInForeground = true),
      runForegroundServiceForCalls = false,
//      token = StreamVideo.devToken(userId),
      token = CredentialsAudience.TOKEN,
      user = User(
        id = CredentialsAudience.ID,
        name = CredentialsAudience.NAME,
        image = CredentialsAudience.AVATAR,
        role = CredentialsAudience.ROLE
      )
    ).build()
  }

  private fun setCredentialAsHost(context: Context) {
    StreamVideoBuilder(
      context = context,
      apiKey = BuildConfig.STREAM_API_KEY,
      notificationConfig = NotificationConfig(hideRingingNotificationInForeground = true),
      runForegroundServiceForCalls = false,
//      token = StreamVideo.devToken(userId),
      token = CredentialsHost.TOKEN,
      user = User(
        id = CredentialsHost.ID,
        name = CredentialsHost.NAME,
        image = CredentialsHost.AVATAR,
        role = CredentialsHost.ROLE
      )
    ).build()
  }

  override fun dependencies(): List<Class<out Initializer<*>>> =
    listOf(StreamLogInitializer::class.java)
}
