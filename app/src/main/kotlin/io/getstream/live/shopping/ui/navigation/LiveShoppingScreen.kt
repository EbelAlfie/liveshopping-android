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

package io.getstream.live.shopping.ui.navigation

import kotlinx.serialization.Serializable

sealed interface LiveShoppingScreen {

  @Serializable
  data object Channels : LiveShoppingScreen

  @Serializable
  data object ChannelCreation: LiveShoppingScreen

  @Serializable
  data class LiveShopping(val cid: String, val isHost: Boolean) : LiveShoppingScreen
}
