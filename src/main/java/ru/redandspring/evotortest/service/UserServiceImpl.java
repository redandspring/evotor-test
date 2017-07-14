package ru.redandspring.evotortest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.redandspring.evotortest.model.User;
import ru.redandspring.evotortest.repository.UserDao;

import javax.annotation.Resource;

/**
 * @author Alexander Tretyakov
 */
@Component
public class UserServiceImpl implements UserService
{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public User getByLogin(String login) {
        User test = userDao.getByLogin(login);
        logger.info(String.valueOf(test));
        return test;
    }

    @Override
    public long save(User user) {
        long userId = userDao.save(user);
        logger.info("save new user id: " + userId);
        return userId;
    }
}
