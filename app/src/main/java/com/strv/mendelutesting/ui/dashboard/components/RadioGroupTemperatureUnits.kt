package com.strv.mendelutesting.ui.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.strv.mendelutesting.R
import com.strv.mendelutesting.logic.TemperatureUnitsEnum

@Composable
fun RadioGroupTemperatureUnits(
    selectedUnit: TemperatureUnitsEnum,
    onUnitChange: (TemperatureUnitsEnum) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.dashboard_temperature_units).uppercase(),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.size(8.dp))

        //  Radio buttons
        TemperatureUnitsEnum.values().forEach { unit ->
            TemperatureUnitRadioButton(
                unit = unit,
                selectedUnit = selectedUnit,
                onClick = { onUnitChange(unit) }
            )
        }
    }
}

@Composable
private fun TemperatureUnitRadioButton(
    unit: TemperatureUnitsEnum,
    selectedUnit: TemperatureUnitsEnum,
    onClick: () -> Unit
) {
    val isItemSelected: (TemperatureUnitsEnum) -> Boolean = { selectedUnit == it }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .selectable(
                selected = isItemSelected(unit),
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(8.dp)
    ) {
        RadioButton(
            selected = isItemSelected(unit),
            onClick = null
        )
        Text(
            modifier = Modifier.padding(start = 24.dp),
            text = stringResource(id = unit.label)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun TemperatureUnitsSelection_Preview() {
    RadioGroupTemperatureUnits(
        selectedUnit = TemperatureUnitsEnum.CELSIUS,
        onUnitChange = {}
    )
}
