package com.darien.week6vp.ui.Widgets
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.darien.week6vp.ViewModel.ProjectFormState

@Composable
fun PopUp(
    communityName: String,
    form: ProjectFormState,
    techOptions: List<String>,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTechToggle: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val blue = Color(0xFF2F5BEA)
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color(0xFFF3F4F6),
        unfocusedContainerColor = Color(0xFFF3F4F6),
        focusedBorderColor = blue,
        unfocusedBorderColor = Color(0xFFD1D5DB)
    )

    AlertDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        containerColor = Color.White,
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(28.dp),
        title = {
            Column {
                Text("Add Project", fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Komunitas: $communityName",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Color.Gray
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Judul Proyek *", fontSize = 13.sp, lineHeight = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    OutlinedTextField(
                        value = form.title,
                        onValueChange = onTitleChange,
                        placeholder = {Text("Contoh: Smart Campus Navigation", fontSize = 14.sp, color = Color.Gray)},
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        "Deskripsi Proyek (Minimal 3 Baris) *",
                        fontSize = 13.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = form.deskripsi,
                        onValueChange = onDescriptionChange,
                        placeholder = {Text("Jelaskan proyekmu...", fontSize = 14.sp, color = Color.Gray)},
                        minLines = 3,
                        maxLines = 3,
                        shape = RoundedCornerShape(12.dp),
                        colors = fieldColors,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Tech Stack (Pilihan Multi-Selection) *",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            color = Color.Black
                        )
                        Text(
                            "${form.tech.size} Dipilih",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = blue
                        )
                    }
                    TechStackRow(
                        items = techOptions,
                        selected = form.tech,
                        onToggle = onTechToggle
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onSave,
                enabled = form.isValid,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFD1D5DB),
                    disabledContentColor = Color.White
                )
            ) {
                Text("Save", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel", fontWeight = FontWeight.Bold, color = blue)
            }
        }
    )
}