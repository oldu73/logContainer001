public class Main {

    public static void main(String[] args) {

        Container container = new Container();
        LogOutputService logOutputService = (LogOutputService) container.getComponents("logOutputService");

        logOutputService.outputInfo("Coucou..");
        logOutputService.outputDebug("le monde!");

    }
}
