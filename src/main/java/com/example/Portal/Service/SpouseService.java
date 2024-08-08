package com.example.Portal.Service;

import com.example.Portal.Model.Spouse;
import java.sql.SQLException;
import java.util.List;

public interface SpouseService {
    Spouse saveSpouse(Spouse spouse) throws SQLException;
    List<Spouse> getAllSpouses();
    Spouse getSpouseById(Long spouseId);
    Spouse updateSpouse(Spouse spouse) throws SQLException;
    void deleteSpouse(Long spouseId) throws SQLException;
}
