package org.cocktail.db.cocktail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.cocktail.db.BaseEntity;
import org.cocktail.db.cocktail.enums.Glass;
import org.cocktail.db.cocktail.enums.Method;
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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private double proof;

    @Column(columnDefinition = "varchar(255)",length = 50,nullable = false)
    private Glass glass;

    @Column(columnDefinition = "varchar(255)",length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private Method method;

    private String garnish;
    private String image;

    @Column(columnDefinition="TEXT")
    private String description;
}
