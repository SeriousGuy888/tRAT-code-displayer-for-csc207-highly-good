package io.github.seriousguy888.view;

import io.github.seriousguy888.interface_adapter.TextEntryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEntryView extends JPanel {
    private final String viewName = "text entry";

    private final JTextField field = new JTextField(8);
    private final JLabel label = new JLabel();
    private final JButton submitButton = new JButton("ok");

    private TextEntryController controller;

    public TextEntryView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.execute(field.getText());
            }
        });

        this.add(label);
        this.add(field);
        this.add(Box.createHorizontalStrut(5));
        this.add(submitButton);
    }

    public void setController(TextEntryController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
