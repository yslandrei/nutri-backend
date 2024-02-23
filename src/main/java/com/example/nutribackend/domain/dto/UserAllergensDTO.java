package com.example.nutribackend.domain.dto;

import com.example.nutribackend.domain.AllergenImportance;
import com.example.nutribackend.domain.UserAllergens;

public record UserAllergensDTO(
        AllergenImportance gluten,
        AllergenImportance milk,
        AllergenImportance eggs,
        AllergenImportance nuts,
        AllergenImportance peanuts,
        AllergenImportance sesameSeeds,
        AllergenImportance soybeans,
        AllergenImportance celery,
        AllergenImportance mustard,
        AllergenImportance lupin,
        AllergenImportance fish,
        AllergenImportance crustaceans,
        AllergenImportance molluscs,
        AllergenImportance sulphurDioxideAndSulphites
) {
    public static UserAllergensDTO fromUserAllergens(UserAllergens allergens) {
        if (allergens == null)
            return null;
        return new UserAllergensDTO(
                allergens.getGluten(),
                allergens.getMilk(),
                allergens.getEggs(),
                allergens.getNuts(),
                allergens.getPeanuts(),
                allergens.getSesameSeeds(),
                allergens.getSoybeans(),
                allergens.getCelery(),
                allergens.getMustard(),
                allergens.getLupin(),
                allergens.getFish(),
                allergens.getCrustaceans(),
                allergens.getMolluscs(),
                allergens.getSulphurDioxideAndSulphites()
        );
    }

    public UserAllergens toUserAllergens() {
        return new UserAllergens(
                null,
                null,
                gluten,
                milk,
                eggs,
                nuts,
                peanuts,
                sesameSeeds,
                soybeans,
                celery,
                mustard,
                lupin,
                fish,
                crustaceans,
                molluscs,
                sulphurDioxideAndSulphites
        );
    }
}
