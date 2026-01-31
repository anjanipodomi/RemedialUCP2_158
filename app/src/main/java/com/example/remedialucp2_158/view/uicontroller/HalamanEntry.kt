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
import com.example.remedialucp2_158.viewmodel.EntryViewModel
import com.example.remedialucp2_158.viewmodel.DetailBuku
import com.example.remedialucp2_158.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBukuScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiStateBuku
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BukuTopAppBar(
                title = stringResource(R.string.entry_buku),
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

            // FORM INPUT
            OutlinedTextField(
                value = uiState.detailBuku.judul,
                onValueChange = { viewModel.updateUIState(uiState.detailBuku.copy(judul = it)) },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.detailBuku.isbn,
                onValueChange = { viewModel.updateUIState(uiState.detailBuku.copy(isbn = it)) },
                label = { Text("ISBN") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.detailBuku.tahun,
                onValueChange = { viewModel.updateUIState(uiState.detailBuku.copy(tahun = it)) },
                label = { Text("Tahun") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveBuku()
                        navigateBack()
                    }
                },
                enabled = uiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}