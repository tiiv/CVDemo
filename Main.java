import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Thread() {
        
            @Override
            public void run() {
                Window window = new Window(new CalendarDataSource());
                window.setVisible(true);
            }
        });
    }
}
