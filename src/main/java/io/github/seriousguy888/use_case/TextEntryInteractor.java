package io.github.seriousguy888.use_case;

public class TextEntryInteractor implements TextEntryInputBoundary {
    private final TextEntryOutputBoundary presenter;

    public TextEntryInteractor(TextEntryOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(TextEntryInputData input) {
        presenter.prepareSuccessView(new TextEntryOutputData(input.getText()));
    }
}
