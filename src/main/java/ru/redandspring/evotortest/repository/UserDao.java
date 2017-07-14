package ru.redandspring.evotortest.repository;

import ru.redandspring.evotortest.model.User;

public interface UserDao
{
    long save(User user);

    User getByLogin(String login);
}
