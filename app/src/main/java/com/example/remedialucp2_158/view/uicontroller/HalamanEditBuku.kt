package com.example.remedialucp2_158.view.uicontroller

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.remedialucp2_158.R
import com.example.remedialucp2_158.viewmodel.EditViewModel
import com.example.remedialucp2_158.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBukuScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiStateBuku
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BukuTopAppBar(
                title = stringResource(R.string.edit_buku),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // FORM INPUT EDIT
            OutlinedTextField(
                value = uiState.detailBuku.judul,
                onValueChange = { viewModel.updateUiState(uiState.detailBuku.copy(judul = it)) },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.detailBuku.isbn,
                onValueChange = { viewModel.updateUiState(uiState.detailBuku.copy(isbn = it)) },
                label = { Text("ISBN") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.detailBuku.tahun,
                onValueChange = { viewModel.updateUiState(uiState.detailBuku.copy(tahun = it)) },
                label = { Text("Tahun") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateBuku()
                        navigateBack()
                    }
                },
                enabled = uiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
            }
        }
    }
}