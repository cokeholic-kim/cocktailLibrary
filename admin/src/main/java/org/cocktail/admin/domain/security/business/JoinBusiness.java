package org.cocktail.admin.domain.security.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cocktail.admin.domain.security.controller.model.UserRequest;
import org.cocktail.admin.domain.security.controller.model.UserResponse;
import org.cocktail.admin.domain.security.converter.JoinConverter;
import org.cocktail.admin.domain.security.service.JoinService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinBusiness {

    private final JoinService joinService;
    private final JoinConverter joinConverter;

    public void register(UserRequest request) {
        joinService.register(joinConverter.toEntity(request));
    }

    public List<UserResponse> findUsers() {
        return joinService.findUsers().stream().map(joinConverter::toResponse).toList();
    }

    public void changeRole(Long id, String role) {

        joinService.changeRole(id, role);
    }
}

