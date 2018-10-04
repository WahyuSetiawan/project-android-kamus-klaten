package cyber.com.kamus.database;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    Database database;

    @Before
    public void setUp() throws Exception {
        database = new Database(InstrumentationRegistry.getContext());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void getTableCategory() {
    }

    @Test
    public void getTableKamus() {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onUpgrade() {
    }
}