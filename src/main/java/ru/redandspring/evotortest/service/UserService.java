package ru.redandspring.evotortest.service;

import ru.redandspring.evotortest.model.User;

/**
 * @author Alexander Tretyakov
 */
public interface UserService
{
    User getByLogin(String login);

    long save(User user);
}
