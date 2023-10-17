
package MC.lab2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;

public class Lab2Window {

  private static JFrame frame;

  public Lab2Window() {
    initUI();
  }

  private void initUI() {
    frame = new JFrame("СМО");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700, 400);
    frame.setLocationRelativeTo(null);

    frame.getContentPane().setBackground(new Color(173, 231, 134));

    createUI();

    frame.setVisible(true);
  }

  private void createUI() {
    frame.setLayout(new GridLayout(7, 2, 10, 10));

    JLabel x0Label = new JLabel("Початкове значення генератора (X0):");
    JTextField x0Entry = new JTextField();
    JLabel arrivalLabel = new JLabel("Діапазон часу між поступленнями заявок (хв):");
    JTextField arrivalMinEntry = new JTextField();
    JTextField arrivalMaxEntry = new JTextField();
    JLabel serviceLabel = new JLabel("Діапазон часу обслуговування заявок (хв):");
    JTextField serviceMinEntry = new JTextField();
    JTextField serviceMaxEntry = new JTextField();
    JLabel numRequestsLabel = new JLabel("Кількість заявок для моделювання:");
    JTextField numRequestsEntry = new JTextField();
    JButton simulateButton = new JButton("Моделювати");

    x0Label.setForeground(new Color(10, 9, 9));
    arrivalLabel.setForeground(new Color(20, 21, 20));
    serviceLabel.setForeground(new Color(2, 2, 2));
    numRequestsLabel.setForeground(new Color(0, 0, 0));

    simulateButton.setBackground(new Color(229, 13, 13));
    simulateButton.setForeground(Color.WHITE);

    simulateButton.addActionListener(e -> {
      int X0 = Integer.parseInt(x0Entry.getText());
      int arrivalMin = Integer.parseInt(arrivalMinEntry.getText());
      int arrivalMax = Integer.parseInt(arrivalMaxEntry.getText());
      int serviceMin = Integer.parseInt(serviceMinEntry.getText());
      int serviceMax = Integer.parseInt(serviceMaxEntry.getText());
      int numRequests = Integer.parseInt(numRequestsEntry.getText());

      String s = SingleServerQueueSimulation.solve(X0, arrivalMin, arrivalMax, serviceMin, serviceMax, numRequests);

      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Формат часу
      String formattedTime = dateFormat.format(new Date()); // Поточний час у форматі HH:mm:ss
      JOptionPane.showMessageDialog(frame, "Початок моделювання о " + formattedTime + "\n" + s);
    });

    frame.add(x0Label);
    frame.add(x0Entry);
    frame.add(arrivalLabel);
    frame.add(arrivalMinEntry);
    frame.add(new JLabel("-"));
    frame.add(arrivalMaxEntry);
    frame.add(serviceLabel);
    frame.add(serviceMinEntry);
    frame.add(new JLabel("-"));
    frame.add(serviceMaxEntry);
    frame.add(numRequestsLabel);
    frame.add(numRequestsEntry);
    frame.add(simulateButton);

    frame.pack();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Lab2Window());
  }
}
