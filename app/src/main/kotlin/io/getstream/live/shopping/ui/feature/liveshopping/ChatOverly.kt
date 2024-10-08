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

package io.getstream.live.shopping.ui.feature.liveshopping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.getstream.chat.android.compose.ui.components.avatar.Avatar
import io.getstream.chat.android.ui.common.state.messages.list.MessageItemState

@Composable
internal fun ChatOverly(
  modifier: Modifier = Modifier,
  messages: List<MessageItemState>
) {
  val configuration = LocalConfiguration.current

  LazyColumn(modifier = modifier.width((configuration.screenWidthDp * 0.65f).dp)) {
    itemsIndexed(messages.reversed(), key = { _, item -> item.message.id }) { index, message ->
      Message(
        modifier = Modifier
          .padding(bottom = 4.dp)
          .clip(RoundedCornerShape(32.dp))
          .alpha((0.20f * index).coerceIn(minimumValue = 0.25f, maximumValue = 0.8f)),
        messageItemState = message
      )
    }
  }
}

@Composable
private fun Message(
  modifier: Modifier,
  messageItemState: MessageItemState
) {
  Box(
    modifier = Modifier.fillMaxWidth()
  ) {
    Box(
      modifier = modifier
        .matchParentSize()
        .background(Color.Black)
    )

    Column(
      modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp)
      ) {
        Avatar(
          modifier = Modifier.size(15.dp),
          imageUrl = messageItemState.message.user.image,
          initials = ""
        )
        Text(
          text = "${messageItemState.message.user.name}:",
          color = Color.White,
          fontSize = 13.sp
        )
      }

      Text(
        text = messageItemState.message.text,
        color = Color.White,
        fontSize = 13.sp
      )
    }

  }
}
