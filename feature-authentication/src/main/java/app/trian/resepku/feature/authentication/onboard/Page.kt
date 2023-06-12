/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.feature.authentication.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.core.ui.BaseMainApp
import app.trian.core.ui.UIListener
import app.trian.core.ui.UIWrapper
import com.google.accompanist.pager.rememberPagerState
import app.trian.core.ui.component.R
import app.trian.core.ui.component.AnnotationTextItem
import app.trian.core.ui.component.ButtonPrimary
import app.trian.core.ui.component.ButtonSecondary
import app.trian.core.ui.component.TextWithAction
import app.trian.core.ui.routes.Routes


@Composable
fun ScreenOnboard(
    invoker: UIListener<OnboardState, OnboardEvent>
) = UIWrapper(invoker = invoker) {
    val privacyPolicyText = listOf(
        AnnotationTextItem.Text(stringResource(id = R.string.text_license_agreement)),
        AnnotationTextItem.Button(stringResource(id = R.string.text_privacy_policy)),
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val totalPage= 4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 20.dp,
                bottom = 40.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            modifier = Modifier.fillMaxSize(fraction = 0.6f),
            painter = painterResource(id = R.drawable.ic_onboard),
            contentDescription = stringResource(R.string.content_description_image_onboard),
            contentScale = ContentScale.FillHeight
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material3.Text(
                text = stringResource(R.string.title_onboard),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            androidx.compose.material3.Text(
                text = stringResource(R.string.subtitle_onboard),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonPrimary(
                    text = stringResource(id = R.string.btn_signin),
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = 150.dp
                        ),
                    fullWidth = false
                ) {
                    navigateAndReplaceAll(Routes.SignIn.routeName)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ButtonSecondary(
                    text = stringResource(R.string.btn_create_new_account),
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = 150.dp
                        ),
                    fullWidth = false
                ) {
                    navigateSingleTop(Routes.SignUp.routeName)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextWithAction(
                labels = privacyPolicyText,
                onTextClick = {
                    if(it==1){
                        showBottomSheet()
                    }
                }
            )

        }
    }
}

@Preview
@Composable
fun PreviewScreenOnboard() {
    BaseMainApp {
        ScreenOnboard(
            invoker = UIListener(
                controller = it,
                state = OnboardState()
            )
        )
    }
}