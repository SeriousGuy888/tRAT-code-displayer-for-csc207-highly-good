package io.github.seriousguy888.interface_adapter;

public class PresentationViewModel extends ViewModel<String> {
    public PresentationViewModel(String text) {
        super("presentation");
        setState(text);
    }
}
