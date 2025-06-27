package knittingapp.controller.model;

import jakarta.validation.constraints.NotNull;

public record RavelryRequest(@NotNull String category) {
}
