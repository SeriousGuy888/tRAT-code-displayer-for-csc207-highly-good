package io.github.seriousguy888.app;

import io.github.seriousguy888.interface_adapter.*;
import io.github.seriousguy888.use_case.TextEntryInputBoundary;
import io.github.seriousguy888.use_case.TextEntryInteractor;
import io.github.seriousguy888.use_case.TextEntryOutputBoundary;
import io.github.seriousguy888.view.PresentationView;
import io.github.seriousguy888.view.TextEntryView;
import io.github.seriousguy888.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel(); // panel that contains all views

    // a layout that shows only one component at a time
    // https://docs.oracle.com/javase/8/docs/api/java/awt/CardLayout.html
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private TextEntryView textEntryView;
    private PresentationView presentationView;
    private PresentationViewModel presentationViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public JFrame build() {
        final JFrame app = new JFrame("aaa");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        app.add(cardPanel);

        viewManagerModel.setState(textEntryView.getName());
        viewManagerModel.firePropertyChange();

        return app;
    }

    public AppBuilder addTextEntryView() {
        textEntryView = new TextEntryView();
        cardPanel.add(textEntryView, textEntryView.getViewName());
        return this;
    }

    public AppBuilder addPresentationView() {
        presentationViewModel = new PresentationViewModel("sample text!!!");
        presentationView = new PresentationView(presentationViewModel);

        try {
            URL resource = ClassLoader.getSystemResource("yes.png");
            if(resource == null) {
                throw new IOException();
            }
            Image image = new ImageIcon(resource).getImage();
            presentationView.setBackgroundImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cardPanel.add(presentationView, presentationView.getViewName());
        return this;
    }

    public AppBuilder addTextEntryUseCase() {
        TextEntryOutputBoundary presenter = new TextEntryPresenter(viewManagerModel, presentationViewModel);
        TextEntryInputBoundary interactor = new TextEntryInteractor(presenter);

        TextEntryController controller = new TextEntryController(interactor);
        textEntryView.setController(controller);
        return this;
    }


}
