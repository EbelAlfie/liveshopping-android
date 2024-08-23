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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.unit.dp
import io.getstream.chat.android.compose.ui.components.composer.InputField
import io.getstream.chat.android.compose.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.models.Message
import io.getstream.chat.android.ui.common.state.messages.list.MessageItemState
import io.getstream.live.shopping.ProductModel
import io.getstream.live.shopping.R
import io.getstream.live.shopping.chat.ChannelConst.EXTRA_DESCRIPTION
import io.getstream.live.shopping.chat.ChannelConst.EXTRA_STREAMER_NAME
import io.getstream.live.shopping.chat.ChannelConst.EXTRA_STREAM_PREVIEW_LINK
import io.getstream.live.shopping.products
import io.getstream.live.shopping.ui.component.products.BottomSheet
import io.getstream.live.shopping.ui.component.products.ProductBanner
import io.getstream.live.shopping.ui.component.products.ProductList
import io.getstream.video.android.compose.theme.VideoTheme

@Composable
internal fun LiveShoppingBottomBar(
  messages: List<MessageItemState>,
  isHost: Boolean,
  listViewModel: MessageListViewModel,
  composerViewModel: MessageComposerViewModel,
  productList: List<ProductModel>,
  bannerUiState: ProductBannerUiState,
  onPinProductClicked: (String) -> Unit,
  onShareClicked: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(horizontal = 6.dp)
      .navigationBarsPadding()
      .fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(6.dp)
  ) {
    ChatOverly(messages = messages)

    when (bannerUiState) {
      is ProductBannerUiState.Pinned ->
        ProductBanner(
          image = bannerUiState.productData.image, //listViewModel.channel.extraData[EXTRA_STREAM_PREVIEW_LINK].toString(),
          name = bannerUiState.productData.name, //listViewModel.channel.extraData[EXTRA_STREAMER_NAME].toString(),
          description = bannerUiState.productData.desc, //listViewModel.channel.extraData[EXTRA_DESCRIPTION].toString()
        )

      else ->
        ProductBanner(
          image = listViewModel.channel.extraData[EXTRA_STREAM_PREVIEW_LINK].toString(),
          name = listViewModel.channel.extraData[EXTRA_STREAMER_NAME].toString(),
          description = listViewModel.channel.extraData[EXTRA_DESCRIPTION].toString()
        )
    }

    ChatInput(
      cid = listViewModel.channel.cid,
      isHost = isHost,
      messageComposerViewModel = composerViewModel,
      onPinProductClicked = onPinProductClicked,
      productList = productList,
      onShareClicked = onShareClicked
    )
  }
}

@Composable
private fun ChatInput(
  cid: String,
  isHost: Boolean,
  productList: List<ProductModel>,
  messageComposerViewModel: MessageComposerViewModel,
  onPinProductClicked: (String) -> Unit,
  onShareClicked: () -> Unit
) {
  val (text, changeText) = remember { mutableStateOf("") }
  var showBottomSheet by remember { mutableStateOf<Boolean?>(null) }

  Row(
    modifier = Modifier
      .imePadding()
      .fillMaxWidth()
      .padding(bottom = 12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = {
      showBottomSheet = true
    }) {
      Icon(
        imageVector = Icons.Default.ShoppingBag,
        tint = VideoTheme.colors.brandYellow,
        contentDescription = null
      )
    }

    InputField(
      modifier = Modifier
        .padding(horizontal = 6.dp)
        .weight(1f)
        .height(36.dp),
      value = text,
      onValueChange = changeText,
      innerPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
      decorationBox = { innerTextField ->
        innerTextField()
      }
    )

    Icon(
      modifier = Modifier.clickable(enabled = text.isNotEmpty()) {
        messageComposerViewModel.sendMessage(
          message = messageComposerViewModel.buildNewMessage(text)
        )
        changeText.invoke("")
      },
      imageVector = Icons.Default.Send,
      tint = if (text.isNotEmpty()) {
        VideoTheme.colors.brandPrimary
      } else {
        VideoTheme.colors.iconDisabled
      },
      contentDescription = null
    )

    IconButton(onClick = onShareClicked) {
      Icon(painter = painterResource(id = R.drawable.baseline_share_24), contentDescription = null)
    }
  }

  showBottomSheet?.let {
    if (it)
      BottomSheet(onDismiss = { showBottomSheet = false }) {
        ProductList(
          products = productList,
          isHost = isHost,
          onItemSelected = onPinProductClicked
        ) //dari mapi (?)
      }
  }

}
