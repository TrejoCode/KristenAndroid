package mx.edu.upqroo.kristenandroid.database.entities

import androidx.annotation.NonNull

class Config(@NonNull val generalTopic: String,
             @NonNull val userTopic: String,
             @NonNull val baseAddress: String,
             @NonNull val userToken: String)
