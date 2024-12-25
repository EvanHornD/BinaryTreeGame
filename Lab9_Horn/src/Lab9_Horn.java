import game.Application;

public class Lab9_Horn {
    public static void main(String[] args) throws Exception {
        // try {
        //     while (true) {
        //         int keyCode = RawConsoleInput.read(false); // Wait for a key press
        //         if (keyCode == -1) {
        //             System.out.println("End of input (EOF).");
        //             break;
        //         }
        //         System.out.println("Key pressed: " + keyCode + " (" + (char) keyCode + ")");

        //         if ((char) keyCode == 'q') {
        //             System.out.println("Exiting...");
        //             break;
        //         }
        //         Thread.sleep(33);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     try {
        //         RawConsoleInput.resetConsoleMode(); // Reset console mode to normal
        //     } catch (Exception e) {
        //         System.err.println("Failed to reset console mode.");
        //         e.printStackTrace();
        //     }
        // }
        
        Application game = new Application();
        game.run();
    }
}
