package de.syntax_institut.jpc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jpc.R

@Composable
fun PlaybackControls() {
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
            tint = MaterialTheme.colorScheme.onSurface // Use theme color
        )
        ImageButton(
            imageResId = R.drawable.skip_previous_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Previous",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface // Use theme color
        )
        ImageButton(
            imageResId = R.drawable.play_circle_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Play",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface // Use theme color
        )
        ImageButton(
            imageResId = R.drawable.skip_next_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Next",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface // Use theme color
        )
        ImageButton(
            imageResId = R.drawable.repeat_one_24dp_e8eaed_fill0_wght400_grad0_opsz24,
            contentDescription = "Repeat",
            onClick = { /* TODO */ },
            tint = MaterialTheme.colorScheme.onSurface // Use theme color
        )
    }
}

