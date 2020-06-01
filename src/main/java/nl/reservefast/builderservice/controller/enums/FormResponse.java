package nl.reservefast.builderservice.controller.enums;

public enum FormResponse {
    INVALID_PARAMS("Invalid parameters"),
    UNEXPECTED_ERROR("A unexpected error occurred. Try again later"),
    NO_FORM("No form has been found"),
    NO_FORMS("No forms are found"),
    NO_COMPANY("No company was found");

    private final String text;

    FormResponse(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
