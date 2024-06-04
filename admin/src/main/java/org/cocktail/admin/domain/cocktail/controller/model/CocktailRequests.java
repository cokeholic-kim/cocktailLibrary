package org.cocktail.admin.domain.cocktail.controller.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CocktailRequests {
    private List<CockTailRequest> cockTailRequestList;
}
