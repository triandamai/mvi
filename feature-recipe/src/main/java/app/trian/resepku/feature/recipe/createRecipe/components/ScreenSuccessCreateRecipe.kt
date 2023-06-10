/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.recipe.createRecipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.R
import app.trian.core.ui.component.ButtonSecondary
import app.trian.core.ui.component.DottedLine

@Composable
fun ScreenSuccessCreateRecipe(
    onClose:()->Unit={},
    onEdit:(String)->Unit={}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IconToggleButton(
            checked = false,
            onCheckedChange = { onClose()},
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = "Close")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_congratulations),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Congratulation!",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your bank account is added successfully to the app",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Column(
                    modifier = Modifier

                ) {
                    Column {
                        Text(
                            text = "Recipe Name",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ikan Bakar sambal terasi",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Column {
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Lorem ipsum dolor sit amet, lorem ipsum dolor sit amet.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Category",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Food",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxHeight()
                                .width(1.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                        ) {

                        }
                        Column {
                            Text(
                                text = "Duration",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "60 Mins",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                    )
                    DottedLine(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "#Dish #Authentic",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        ButtonSecondary(
                            text = "Edit",
                            fullWidth = false,
                            onClick = {
                                onEdit("")
                            }
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewScreenSuccessCreateRecipe() {
    BaseMainApp {
        ScreenSuccessCreateRecipe()
    }
}