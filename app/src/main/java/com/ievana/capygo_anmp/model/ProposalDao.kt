package com.ievana.capygo_anmp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProposalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg proposal: Proposal)

    @Query("SELECT * FROM proposal")
    fun selectAllProp(): List<Proposal>
}

