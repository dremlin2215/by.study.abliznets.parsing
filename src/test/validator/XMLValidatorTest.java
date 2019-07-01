package validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLValidatorTest {

    private static final Path CORRECT_FILE_PATH
            = Paths.get("src/main/resources/correctDevicesForTesting.xml");
    private static final Path INCORRECT_FILE_PATH
            = Paths.get("src/main/resources/incorrectDevicesForTesting.xml");


    @Test
    public void validate_validStream_true() throws IOException {
        InputStream inputStream = Files.newInputStream(CORRECT_FILE_PATH);
        boolean result = XMLValidator.validate(inputStream);
        Assert.assertTrue(result);

    }

    @Test
    public void validate_invalidStream_true() throws IOException{

        InputStream inputStream = Files.newInputStream(INCORRECT_FILE_PATH);
        boolean result = XMLValidator.validate(inputStream);
        Assert.assertFalse(result);

    }
}

/*


    @Test(expectedExceptions = WrongParamsException.class)
    // Ask if such format while throwing an exception is valid in testing
    public void convertDot_invalidDot_WrongParamsException() throws WrongParamsException {
        // ^ The right name ends this way or with true?
        //given
        DataConverter dataConverter = DataConverter.getInstance();
        String str = "3,0,0.4";
        //when
        Dot testDot = dataConverter.convertDot(str);
        //then
        Assert.assertFalse(false);
    }

*/