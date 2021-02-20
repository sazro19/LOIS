import java.io.FileReader;
import java.io.IOException;

public class LogicLanguageService {

    private Parser parser;

    public void startParse(String path) {
        StringBuilder formula = new StringBuilder();
        try {
            FileReader reader = new FileReader(path);
            int c;
            while ((c = reader.read()) != -1) {
                formula.append((char)c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Parser parser = new Parser(formula);
    }

}
