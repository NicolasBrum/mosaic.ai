package com.mosaicai.api.entities;

import com.mosaicai.api.models.LeadModel;
import com.mosaicai.api.services.LeadService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_lead")
public class LeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String surname;
    private String position;
    private String enterprise;
    private String email;

    public LeadEntity(LeadModel leadModel){
        this.id = null;
        this.name = leadModel.name()
                .trim();
        this.surname = leadModel.surname()
                .trim();
        this.enterprise = leadModel.enterprise()
                .trim();
        this.position = leadModel.position()
                .trim();
        this.email = leadModel.email()
                .trim();
    }
}
