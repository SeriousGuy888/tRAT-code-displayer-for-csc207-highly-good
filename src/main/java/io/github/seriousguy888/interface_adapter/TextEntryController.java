package io.github.seriousguy888.interface_adapter;

import io.github.seriousguy888.use_case.TextEntryInputBoundary;
import io.github.seriousguy888.use_case.TextEntryInputData;

public class TextEntryController {
    private final TextEntryInputBoundary interactor;

    public TextEntryController(TextEntryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String text) {
        TextEntryInputData inputData = new TextEntryInputData(text);
        interactor.execute(inputData);
    }
}
