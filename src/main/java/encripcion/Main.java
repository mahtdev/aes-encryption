package encripcion;

/**
 * Created by Miguel on 23/04/14.
 */
public class Main
{
    public static void main(String[] args) throws Exception {
        String mode = "encrypt";
        String text = "zameer";
        if (mode.equals("encrypt")) {
            AES.encrypt(text);
        } else if (mode.equals("decrypt")) {
            AES.decrypt(text);
        }
    }
}
