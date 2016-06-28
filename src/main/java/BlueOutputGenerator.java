public class BlueOutputGenerator implements OutputGenerator{

    // SRC: http://stackoverflow.com/questions/1448858/how-to-color-system-out-println-output

    @Override
    public void output(String message) {
        System.out.println((char)27 + "[34m" + message + (char)27 + "[0m");
    }
}
