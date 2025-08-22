package com.example.recruiterhunter.data.network.google_cse

import android.content.Context

class CseRetrofitClient(
    private val context: Context,
    private val api: GoogleCseApi
) : GoogleNetworkClient {
}