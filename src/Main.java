import java.io.FileWriter;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static void printMessage(String msg, Printable printer) {
    printer.print(msg);
    }
    public static void main(String[] args) {
        String Message = "Message" ;
        Printable consolePrinter = new Printable() {
            @Override
            public void print(String msg) {
                System.out.println("Standart msg print: " + msg);
            }
        };
        printMessage(Message, consolePrinter);
        Printable errorPrinter = msg -> System.err.println("Standard errors print: " + msg);
        printMessage(Message, errorPrinter);
        Printable filePrinter = msg -> {
            try {
                FileWriter writer = new FileWriter("print.txt");
                writer.write(msg);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        printMessage(Message, filePrinter);
    }

}