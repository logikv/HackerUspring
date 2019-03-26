package services.iterfaces;

import entity.UsersAddress;
import exceptions.UserAddressException;

import java.util.List;

public interface UserService {
    List<UsersAddress> get() throws UserAddressException;
    UsersAddress get(int id) throws UserAddressException;
    void delete(int id) throws UserAddressException;
    void update(UsersAddress address) throws UserAddressException;
    boolean insert(UsersAddress address) throws UserAddressException;
}
