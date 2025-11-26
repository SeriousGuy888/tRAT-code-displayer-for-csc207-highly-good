package io.github.seriousguy888.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame app = appBuilder
                .addTextEntryView()
                .addPresentationView()
                .addTextEntryUseCase()
                .build();

        app.setSize(800, 600);
        app.setLocationRelativeTo(null);
        app.setVisible(true);

        // test commit
    }
}