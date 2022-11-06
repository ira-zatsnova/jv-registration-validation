package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null) {
            throw new RuntimeException("The user login can't be null");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RuntimeException("The user with such login already exists in the Storage");
        }
        if (user.getAge() < 18) {
            throw new RuntimeException("The user should be at least 18 years old");
        }
        if (user.getPassword().length() < 6) {
            throw new RuntimeException("The user password should be at least 6 characters");
        }
        return storageDao.add(user);
    }
}
