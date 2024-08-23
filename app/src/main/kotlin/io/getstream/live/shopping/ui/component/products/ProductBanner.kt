package io.getstream.live.shopping.ui.component.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer.Resonate
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.models.Channel
import io.getstream.live.shopping.R.string
import io.getstream.live.shopping.chat.ChannelConst
import io.getstream.live.shopping.ui.component.StreamButton
import io.getstream.live.shopping.ui.theme.shimmerBase
import io.getstream.live.shopping.ui.theme.shimmerHighlight
import io.getstream.video.android.compose.theme.VideoTheme

@Composable
fun ProductBanner(
  image: String,
  name: String,
  description: String,
  leadingContent: @Composable RowScope.() -> Unit = {
    StreamButton(
      modifier = Modifier
        .padding(start = 16.dp)
        .height(36.dp),
      text = stringResource(string.buy),
      onClick = {}
    )
  }
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        color = ChatTheme.colors.appBackground.copy(alpha = 0.85f),
        shape = RoundedCornerShape(16.dp)
      )
      .padding(12.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    GlideImage(
      modifier = Modifier
        .size(76.dp)
        .clip(RoundedCornerShape(16.dp)),
      imageModel = { image },
      component = rememberImageComponent {
        +ShimmerPlugin(
          Resonate(
            baseColor = shimmerBase,
            highlightColor = shimmerHighlight
          )
        )
      }
    )

    Column(
      modifier = Modifier.padding(6.dp).weight(1F),
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(
        text = name,
        color = ChatTheme.colors.textHighEmphasis,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
      )

      Text(
        text = description,
        color = ChatTheme.colors.textLowEmphasis,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
      )

      Text(
        text = "$79.99",
        color = VideoTheme.colors.brandRed,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
      )
    }

    leadingContent()
  }
}