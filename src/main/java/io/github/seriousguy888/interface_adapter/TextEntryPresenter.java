package io.github.seriousguy888.interface_adapter;

import io.github.seriousguy888.use_case.TextEntryOutputBoundary;
import io.github.seriousguy888.use_case.TextEntryOutputData;

public class TextEntryPresenter implements TextEntryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final PresentationViewModel presentationViewModel;

    public TextEntryPresenter(ViewManagerModel viewManagerModel, PresentationViewModel presentationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.presentationViewModel = presentationViewModel;
    }

    @Override
    public void prepareSuccessView(TextEntryOutputData outputData) {
        presentationViewModel.setState(outputData.getText());
        this.viewManagerModel.setState(presentationViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}
