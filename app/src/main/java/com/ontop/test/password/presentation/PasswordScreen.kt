package com.ontop.test.password.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.test.R
import com.ontop.test.password.helper.Constants

@Composable
fun PasswordScreen(
    viewModel: PasswordViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsState()
    PasswordContent(
        viewState = viewState,
        changeLength = viewModel::changeLength,
        changeUpperCaseStatus = viewModel::changeUpperCaseStatus,
        changeNumbersStatus = viewModel::changeNumbersStatus,
        changeSymbolsStatus = viewModel::changeSymbolsStatus,
        generatePassword = viewModel::generatePassword,
    )

}

@Composable
fun PasswordContent(
    viewState: PasswordViewState,
    changeLength: (Int) -> Unit,
    changeUpperCaseStatus: (Boolean) -> Unit,
    changeNumbersStatus: (Boolean) -> Unit,
    changeSymbolsStatus: (Boolean) -> Unit,
    generatePassword: () -> Unit,
) {
    var sliderPosition by remember { mutableFloatStateOf(viewState.length.toFloat()) }
    var length by remember { mutableIntStateOf(viewState.length) }
    val steps by remember { mutableIntStateOf(Constants.PASSWORD_MAX_LENGTH - Constants.PASSWORD_MIN_LENGTH) }
    val valueRange by remember { mutableStateOf(Constants.PASSWORD_MIN_LENGTH.toFloat()..Constants.PASSWORD_MAX_LENGTH.toFloat()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = stringResource(R.string.select_length),
        )
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                length = it.toInt()
            },
            onValueChangeFinished = {
                changeLength.invoke(length)
            },
            steps = steps,
            valueRange = valueRange,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = length.toString(),
            textAlign = TextAlign.Center,
        )
        CheckBoxItem(
            description = stringResource(R.string.include_uppercase),
            isChecked = viewState.includeUpperCase,
            onClick = changeUpperCaseStatus,
        )
        CheckBoxItem(
            description = stringResource(R.string.include_numbers),
            isChecked = viewState.includeNumbers,
            onClick = changeNumbersStatus,
        )
        CheckBoxItem(
            description = stringResource(R.string.include_symbols),
            isChecked = viewState.includeSymbols,
            onClick = changeSymbolsStatus,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = generatePassword,
            ) {
                Text(
                    text = stringResource(R.string.button_generate)
                )
            }

            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = viewState.password,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Composable
fun CheckBoxItem(
    modifier: Modifier = Modifier,
    description: String,
    isChecked: Boolean,
    onClick: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onClick,
        )
        Text(
            text = description,
        )
    }
}
