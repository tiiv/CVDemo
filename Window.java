import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.event.ListDataListener;

public class Window extends JFrame {

    protected final CalendarDataSource source;

    protected final JComboBox<Integer> dayComboBox;
    protected final JComboBox<String>  monthComboBox;
    protected final JComboBox<Integer> yearComboBox;

    protected final CalendarView       calendarView;

    protected final ItemListener       itemListener;

    public Window(CalendarDataSource source) {

        // link data source
        this.source = source;

        // create combo boxes
        this.dayComboBox   = new JComboBox<Integer>();
        this.monthComboBox = new JComboBox<String>();
        this.yearComboBox  = new JComboBox<Integer>();

        // create calendar view
        this.calendarView = new CalendarView(this.source);

        // create selection listener
        this.itemListener = new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                calendarView.repaint();
            }
        };

        // configure and layout components
        this.configureComponents();
        this.layoutComponents();
    }

    protected void configureComponents() {

        this.setTitle("Calendar View");
        this.setLocation(100, 100);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ----- day selection -----
        this.dayComboBox.setModel(new ComboBoxModel<Integer>() {

            @Override
            public void addListDataListener(ListDataListener listener) {}

            @Override
            public Integer getElementAt(int index) {
                return index + 1;
            }

            @Override
            public Object getSelectedItem() {
                return Integer.valueOf(source.getDay());
            }

            @Override
            public void setSelectedItem(Object day) {
                source.setDay((Integer) day);
            }

            @Override
            public int getSize() {
                return source.getNumberOfDays();
            }

            @Override
            public void removeListDataListener(ListDataListener listener) {}
        });
        this.dayComboBox.addItemListener(this.itemListener);

        // ----- month selection -----
        this.monthComboBox.setModel(new ComboBoxModel<String>() {

            @Override
            public void addListDataListener(ListDataListener listener) {}

            @Override
            public String getElementAt(int index) {
                switch (index) {
                    default:
                    case 0:  return "January";
                    case 1:  return "February";
                    case 2:  return "March";
                    case 3:  return "April";
                    case 4:  return "May";
                    case 5:  return "June";
                    case 6:  return "July";
                    case 7:  return "August";
                    case 8:  return "September";
                    case 9:  return "October";
                    case 10: return "November";
                    case 11: return "December";
                }
            }

            @Override
            public Object getSelectedItem() {
                return this.getElementAt(source.getMonth());
            }

            @Override
            public void setSelectedItem(Object month) {
                switch ((String) month) {
                    case "January":   source.setMonth(0);  break;
                    case "February":  source.setMonth(1);  break;
                    case "March":     source.setMonth(2);  break;
                    case "April":     source.setMonth(3);  break;
                    case "May":       source.setMonth(4);  break;
                    case "June":      source.setMonth(5);  break;
                    case "July":      source.setMonth(6);  break;
                    case "August":    source.setMonth(7);  break;
                    case "September": source.setMonth(8);  break;
                    case "October":   source.setMonth(9);  break;
                    case "November":  source.setMonth(10); break;
                    case "December":  source.setMonth(11); break;
                }
            }

            @Override
            public int getSize() {
                return 12;
            }

            @Override
            public void removeListDataListener(ListDataListener listener) {}
        });
        this.monthComboBox.addItemListener(this.itemListener);

        // ----- year selection -----
        this.yearComboBox.setModel(new ComboBoxModel<Integer>() {

            @Override
            public void addListDataListener(ListDataListener listener) {}

            @Override
            public Integer getElementAt(int index) {
                return source.getCurrentYear() - index;
            }

            @Override
            public Object getSelectedItem() {
                return source.getYear();
            }

            @Override
            public void setSelectedItem(Object year) {
                source.setYear((Integer) year);
            }

            @Override
            public int getSize() {
                return source.getCurrentYear() - 1900 + 1;
            }

            @Override
            public void removeListDataListener(ListDataListener listener) {}
        });
        this.yearComboBox.addItemListener(this.itemListener);
    }

    protected void layoutComponents() {

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(5)
                .addComponent(this.dayComboBox, 80, 80, 80)
                .addGap(5)
                .addComponent(this.monthComboBox)
                .addGap(5)
                .addComponent(this.yearComboBox, 100, 100, 100)
                .addGap(5)
            )
            .addComponent(this.calendarView)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(5)
            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(this.dayComboBox)
                .addComponent(this.monthComboBox)
                .addComponent(this.yearComboBox)
            )
            .addGap(5)
            .addComponent(this.calendarView)
        );

        pack();
    }
}
