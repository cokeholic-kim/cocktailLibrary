package org.cocktail.db.cocktail;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
import org.cocktail.db.CocktailIngredient.CocktailIngredientEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
import org.cocktail.db.file.FileEntity;
import org.cocktail.db.user.UserEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "cocktail")
public class CocktailEntity extends BaseEntity {
    //    @Column(length = 100,nullable = false)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 50, nullable = false)
    private String cocktailName;

    @Column(nullable = false)
    private double proof;

    @Column(columnDefinition = "varchar(255)", length = 50, nullable = false)
    private Glass glass;

    @Column(columnDefinition = "varchar(255)", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Method method;

    private String garnish;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileEntity file;

    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CocktailIngredientEntity> cocktailIngredients;
}
