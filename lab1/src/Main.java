public class Main {

    public static void main(String[] args) {
        LogicLanguageService service = new LogicLanguageService();
        service.startParse(System.getProperty("user.dir") + "\\src\\formula");
    }

}
