package repository.interfaces;


import entity.UsersAddress;

import java.util.List;

public interface UserRepository {

    UsersAddress getUserAddress(int id);
    List<UsersAddress> getUsersAddressList();
    boolean insert(UsersAddress address);
    boolean delete(int id);
    boolean update(UsersAddress address);

}
