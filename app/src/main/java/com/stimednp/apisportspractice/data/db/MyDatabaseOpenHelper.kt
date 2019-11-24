package com.stimednp.apisportspractice.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.stimednp.apisportspractice.data.Favorites
import org.jetbrains.anko.db.*

/**
 * Created by rivaldy on 11/23/2019.
 */

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object{
        private var instance: MyDatabaseOpenHelper? = null
    }

    @Synchronized
    fun getInstance(ctx: Context): MyDatabaseOpenHelper {
        if (instance == null){
            instance = MyDatabaseOpenHelper(ctx.applicationContext)
        }
        return instance as MyDatabaseOpenHelper
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create table
        db.createTable(Favorites.TABLE_FAVORITE, true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.TEAM_ID to TEXT + UNIQUE,
            Favorites.TEAM_NAME to TEXT,
            Favorites.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVer: Int, newVer: Int) {
        //upgarde table
        db.dropTable(Favorites.TABLE_FAVORITE, true)
    }

}
//acces property  for context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper(applicationContext).getInstance(applicationContext)