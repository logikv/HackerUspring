package repository.json;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.UsersAddress;
import org.springframework.util.ResourceUtils;
import repository.interfaces.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JSONUserRepository implements UserRepository {


    private static File file = null;

    {
        try {
            file = ResourceUtils.getFile("classpath:data.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ObjectMapper mapper;
    private int lastId;

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    private void cleanFile() throws IOException {
        mapper.writeValue(file, new String());
    }

    private void writeFile(List<UsersAddress> list) {
        try {
            cleanFile();
            mapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UsersAddress getUserAddress(int id) {
        return getUsersAddressList()
                .stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<UsersAddress> getUsersAddressList() {
        List<UsersAddress> list = null;
        try {
            list = mapper.readValue(file, new TypeReference<List<UsersAddress>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insert(UsersAddress address) {
        if (this.getUserAddress(address.getId()) == null) {
            List<UsersAddress> list = getUsersAddressList();
            list.add(address);
            writeFile(list);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        List<UsersAddress> list = getUsersAddressList();
        if (list != null) {
            Set<UsersAddress> removed = list.stream()
                    .filter(user -> user.getId() == id)
                    .limit(1)
//                    .allMatch(user -> user.getId()==id)
                    .collect(Collectors.toSet());

            list.removeAll(removed);
            writeFile(list);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UsersAddress address) {
        List<UsersAddress> list = getUsersAddressList();

        if (list != null) {
            list.stream()
                    .filter(usersAddress -> usersAddress.getId() == address.getId())
                    .forEach(usersAddress -> {
                        usersAddress.setFirstName(address.getFirstName());
                        usersAddress.setLastName(address.getLastName());
                        usersAddress.setEmail(address.getEmail());
                        usersAddress.setGender(address.getGender());
                        usersAddress.setIpAddress(address.getIpAddress());
                    });
            writeFile(list);
            return true;
        }
        return false;
    }
}
