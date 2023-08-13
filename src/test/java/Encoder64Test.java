import org.example.Encoder64;
import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

public class Encoder64Test {
    @Test
    public void encodeTest() {
        var decodedString = "Hello World";
        var encodedResult = Encoder64.encode(decodedString.getBytes());

        Assert.assertEquals(Base64.getEncoder().encodeToString(decodedString.getBytes()), encodedResult);
    }
}
