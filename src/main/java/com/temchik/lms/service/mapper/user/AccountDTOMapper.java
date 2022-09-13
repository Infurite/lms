package com.temchik.lms.service.mapper.user;

import com.temchik.lms.dto.user.AccountDTO;
import com.temchik.lms.model.user.Account;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccountDTOMapper implements EntityToDTOMapper<AccountDTO, Account, AccountDTO> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AccountDTO toDTO(Account entity, Object... args) {
        return mapper.map(entity, AccountDTO.class);
    }

    @Override
    public Account toEntity(AccountDTO dto, Object... args) {
        Account account = mapper.map(dto, Account.class);
        account.setUpdatedAt(new Date(System.currentTimeMillis()));
        return account;
    }
}
