package io.getstream.live.shopping.ui.feature.livecreation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.getstream.chat.android.ui.common.state.channels.actions.Cancel.channel
import io.getstream.live.shopping.R
import io.getstream.live.shopping.ui.component.StreamButton
import io.getstream.live.shopping.ui.component.StreamTab
import io.getstream.live.shopping.ui.component.UrlText
import io.getstream.live.shopping.ui.navigation.LiveShoppingScreen
import io.getstream.live.shopping.ui.navigation.currentComposeNavigator

@Composable
fun ChannelCreationScreen() {
  val navigator = currentComposeNavigator

  Column {
    Text(text = "Choose your software")

    StreamTab()

    UrlText(url = "")

    UrlText(url = "")

    StreamButton(text = stringResource(id = R.string.livestream_to_backstage))
    {
      navigator.navigate(
        LiveShoppingScreen.LiveShopping(cid = channel.cid, isHost = true)
      )
    }
  }


}