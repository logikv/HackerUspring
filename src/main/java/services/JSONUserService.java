package services;

import entity.UsersAddress;
import exceptions.UserAddressException;
import repository.interfaces.UserRepository;
import services.iterfaces.UserService;

import java.util.List;

public class JSONUserService implements UserService {

    private UserRepository repository;

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsersAddress> get() throws UserAddressException {
        List<UsersAddress> list = repository.getUsersAddressList();
        if (list == null) {
            throw new UserAddressException("Список пользователей пуст");
        }

        return list;
    }

    @Override
    public UsersAddress get(int id) throws UserAddressException {
        UsersAddress user = repository.getUserAddress(id);
        if (user == null) {
            throw new UserAddressException("Пользователь с id=" + id + " не найден в системе");
        }
        return user;
    }

    @Override
    public void delete(int id) throws UserAddressException {
        if (!repository.delete(id)) throw new UserAddressException("Пользователь не удален");
    }

    @Override
    public void update(UsersAddress address) throws UserAddressException {
        repository.update(address);
    }

    @Override
    public boolean insert(UsersAddress address) throws UserAddressException {
        return repository.insert(address);
    }


}
