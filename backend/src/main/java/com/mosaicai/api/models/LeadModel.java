package com.mosaicai.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record LeadModel(
        String name,
        String surname,
        String position,
        String enterprise,
        String email) {}
