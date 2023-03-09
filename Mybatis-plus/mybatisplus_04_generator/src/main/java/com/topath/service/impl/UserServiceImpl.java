package com.topath.service.impl;

import com.topath.domain.User;
import com.topath.dao.UserDao;
import com.topath.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kaiyuan
 * @since 2023-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
