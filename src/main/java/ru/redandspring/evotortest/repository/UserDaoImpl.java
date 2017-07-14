package ru.redandspring.evotortest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.redandspring.evotortest.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao
{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertUser;


    @Autowired
    public UserDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName(User.TABLE_NAME)
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public long save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("balance", user.getBalance());

        try {
            return (long) insertUser.executeAndReturnKey(map);
        }
        catch (Exception e){
            logger.warn(e.getMessage());
            return 0;
        }
    }

    @Override
    public User getByLogin(String login) {

        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE login=?", ROW_MAPPER, login);
        return DataAccessUtils.singleResult(users);
    }
}
