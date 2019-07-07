package cyber.com.kamus;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import cyber.com.kamus.database.database.Database;


public class DatabaseTest {
    @Mock
    Database database;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testQuery(){
    }

}
