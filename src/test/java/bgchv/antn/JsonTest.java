package bgchv.antn;

import bgchv.antn.model.JsonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonTest {
    private static String jsonFile = "json/user.json";
    private final ClassLoader classLoader = JsonTest.class.getClassLoader();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void applicationJsonTest() throws IOException {
        try (InputStream stream = classLoader.getResourceAsStream(jsonFile);
             Reader reader = new InputStreamReader(stream)) {

            JsonModel model = objectMapper.readValue(reader, JsonModel.class);

            assertThat(model.getFirstName()).isEqualTo("Homer");
            assertThat(model.getLastName()).isEqualTo("Simpson");
            assertThat(model.getPhoneNumber()).isEqualTo("123-456-78-90");
            assertThat(model.getDateOfBirth()).isEqualTo("1956-05-12");
            assertThat(model.getCurrentAddress()).isEqualTo("742 Evergreen Terrace");
            assertThat(model.getFavoriteFood()).contains("donuts", "pork chops", "american cheese");
        }
    }
}
