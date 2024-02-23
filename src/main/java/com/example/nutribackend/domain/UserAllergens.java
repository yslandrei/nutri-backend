package com.example.nutribackend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user_allergens")
public class UserAllergens {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private AllergenImportance gluten;

    private AllergenImportance milk;

    private AllergenImportance eggs;

    private AllergenImportance nuts;

    private AllergenImportance peanuts;

    private AllergenImportance sesameSeeds;

    private AllergenImportance soybeans;

    private AllergenImportance celery;

    private AllergenImportance mustard;

    private AllergenImportance lupin;

    private AllergenImportance fish;

    private AllergenImportance crustaceans;

    private AllergenImportance molluscs;

    private AllergenImportance sulphurDioxideAndSulphites;

    public UserAllergens() {

    }

    public AllergenImportance getMolluscs() {
        return molluscs;
    }

    public void setMolluscs(AllergenImportance molluscs) {
        this.molluscs = molluscs;
    }

    public UserAllergens(String id, User user, AllergenImportance gluten, AllergenImportance milk, AllergenImportance eggs, AllergenImportance nuts, AllergenImportance peanuts, AllergenImportance sesameSeeds, AllergenImportance soybeans, AllergenImportance celery, AllergenImportance mustard, AllergenImportance lupin, AllergenImportance fish, AllergenImportance crustaceans, AllergenImportance molluscs, AllergenImportance sulphurDioxideAndSulphites) {
        this.id = id;
        this.user = user;
        this.gluten = gluten;
        this.milk = milk;
        this.eggs = eggs;
        this.nuts = nuts;
        this.peanuts = peanuts;
        this.sesameSeeds = sesameSeeds;
        this.soybeans = soybeans;
        this.celery = celery;
        this.mustard = mustard;
        this.lupin = lupin;
        this.fish = fish;
        this.crustaceans = crustaceans;
        this.molluscs = molluscs;
        this.sulphurDioxideAndSulphites = sulphurDioxideAndSulphites;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AllergenImportance getGluten() {
        return gluten;
    }

    public void setGluten(AllergenImportance gluten) {
        this.gluten = gluten;
    }

    public AllergenImportance getMilk() {
        return milk;
    }

    public void setMilk(AllergenImportance milk) {
        this.milk = milk;
    }

    public AllergenImportance getEggs() {
        return eggs;
    }

    public void setEggs(AllergenImportance eggs) {
        this.eggs = eggs;
    }

    public AllergenImportance getNuts() {
        return nuts;
    }

    public void setNuts(AllergenImportance nuts) {
        this.nuts = nuts;
    }

    public AllergenImportance getPeanuts() {
        return peanuts;
    }

    public void setPeanuts(AllergenImportance peanuts) {
        this.peanuts = peanuts;
    }

    public AllergenImportance getSesameSeeds() {
        return sesameSeeds;
    }

    public void setSesameSeeds(AllergenImportance sesameSeeds) {
        this.sesameSeeds = sesameSeeds;
    }

    public AllergenImportance getSoybeans() {
        return soybeans;
    }

    public void setSoybeans(AllergenImportance soybeans) {
        this.soybeans = soybeans;
    }

    public AllergenImportance getCelery() {
        return celery;
    }

    public void setCelery(AllergenImportance celery) {
        this.celery = celery;
    }

    public AllergenImportance getMustard() {
        return mustard;
    }

    public void setMustard(AllergenImportance mustard) {
        this.mustard = mustard;
    }

    public AllergenImportance getLupin() {
        return lupin;
    }

    public void setLupin(AllergenImportance lupin) {
        this.lupin = lupin;
    }

    public AllergenImportance getFish() {
        return fish;
    }

    public void setFish(AllergenImportance fish) {
        this.fish = fish;
    }

    public AllergenImportance getCrustaceans() {
        return crustaceans;
    }

    public void setCrustaceans(AllergenImportance crustaceans) {
        this.crustaceans = crustaceans;
    }

    public AllergenImportance getSulphurDioxideAndSulphites() {
        return sulphurDioxideAndSulphites;
    }

    public void setSulphurDioxideAndSulphites(AllergenImportance sulphurDioxideAndSulphites) {
        this.sulphurDioxideAndSulphites = sulphurDioxideAndSulphites;
    }
}
