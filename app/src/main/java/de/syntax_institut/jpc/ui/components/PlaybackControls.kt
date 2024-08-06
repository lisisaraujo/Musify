package de.syntax_institut.jpc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jpc.R

@Composable
fun PlaybackControls(
    onPreviousClick: () -> Unit,
    onPlayClick: () -> Unit,
    onNextClick: () -> Unit,
    isPlaying: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ImageButton(
            imageResId = R.drawable.shuffle_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Shuffle",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface
        )
        ImageButton(
            imageResId = R.drawable.skip_previous_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Previous",
            onClick = onPreviousClick,
            tint = MaterialTheme.colorScheme.onSurface
        )
        ImageButton(
            imageResId = if (isPlaying) R.drawable.baseline_pause_circle_outline_24
            else R.drawable.play_circle_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = if (isPlaying) "Pause" else "Play",
            onClick = onPlayClick,
            tint = MaterialTheme.colorScheme.onSurface
        )
        ImageButton(
            imageResId = R.drawable.skip_next_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Next",
            onClick = onNextClick,
            tint = MaterialTheme.colorScheme.onSurface
        )
        ImageButton(
            imageResId = R.drawable.repeat_one_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Repeat",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

