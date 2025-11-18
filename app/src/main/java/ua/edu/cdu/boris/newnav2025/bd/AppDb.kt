package ua.edu.cdu.boris.newnav2025.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.edu.cdu.boris.newnav2025.dao.AppDao
import ua.edu.cdu.boris.newnav2025.entity.AuthorEntity
import ua.edu.cdu.boris.newnav2025.entity.BookEntity

@Database(entities = [AuthorEntity::class, BookEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        // Volatile annotation ensures that the INSTANCE variable is always up-to-date
        // and visible to all threads.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // This method provides a singleton instance of the database.
        fun getDatabase(context: Context): AppDatabase {
            // If INSTANCE is not null, return it; otherwise, create a new database instance.
            return INSTANCE ?: synchronized(this) { // Ensures only one thread creates the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // The name of your database file
                )
                    // .addMigrations(MIGRATION_1_2) // For production, implement proper migrations
                    .build()
                INSTANCE = instance
                // Return instance
                instance
            }
        }
    }
}