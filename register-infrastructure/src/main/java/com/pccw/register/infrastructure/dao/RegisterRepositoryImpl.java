package com.pccw.register.infrastructure.dao;


import com.pccw.register.domain.entity.User;
import com.pccw.register.domain.entity.UserMail;
import com.pccw.register.domain.exceptions.BussinessException;
import com.pccw.register.domain.repository.RegisterRepository;
import com.pccw.register.domain.utils.ConstantsUtils;
import com.pccw.register.infrastructure.convert.UserConvert;
import com.pccw.register.infrastructure.convert.UserMailConvert;
import com.pccw.register.infrastructure.mapper.UserMailMapper;
import com.pccw.register.infrastructure.mapper.UserMapper;
import com.pccw.register.infrastructure.po.UserExample;
import com.pccw.register.infrastructure.po.UserMailExample;
import com.pccw.register.infrastructure.po.UserMailPO;
import com.pccw.register.infrastructure.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Slf4j
public class RegisterRepositoryImpl implements RegisterRepository {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserMailMapper userMailMapper;

    @Autowired
    private UserConvert userConvert;

    @Autowired
    private UserMailConvert userMailConvert;

    @Override
    public boolean createUser(User user) {
        UserPO existUser = getUserPO(user.getEmail());
        if(existUser!=null){
            if(existUser.getState()==User.UserState.NORMAL) {
                throw new BussinessException(ConstantsUtils.USER_EXIST);
            }else{
                existUser.setState(User.UserState.NORMAL);
                existUser.setName(user.getName());
                existUser.setPassword(user.getPassword());
                Integer result = userMapper.updateByPrimaryKeySelective(existUser);
                return result != null;
            }
        }else {

            UserPO userPO = userConvert.covertDomainToPO(user);
            log.info("user is :{}",userPO);
            Integer result = userMapper.insert(userPO);

            return result != null;
        }
    }

    @Override
    public boolean createUserMail(UserMail userMail) {
        UserMailPO userMailPO = userMailConvert.covertDomainToPO(userMail);
        Integer id =userMailMapper.insertSelective(userMailPO);
        return id!=null;
    }

    @Override
    public boolean existUser(String email){
        return getUserPO(email)!=null;
    }

    @Override
    public User getUser(String email){
        UserPO userPO = getUserPO(email);
        return userPO!=null ? userConvert.covertPOToDomain(userPO):null;
    }

    private UserPO getUserPO(String email){
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<UserPO> userPOS = userMapper.selectByExample(example);
        return userPOS.size()>0? userPOS.get(0):null;
    }

    @Override
    public List<UserMail> listUnSendMail() {

        UserMailExample example = new UserMailExample();

        example.createCriteria()
                .andCreateTimeLessThan(LocalDateTime.now().minusMinutes(5))
                .andStateEqualTo(UserMail.MailState.TO_BE_SENT);
        List<UserMailPO> userMailPOS = userMailMapper.selectByExample(example);


        return userMailConvert.covertPOSToDomains(userMailPOS);
    }

    @Override
    public UserMail getUserMail(String email) {
        UserMailExample example = new UserMailExample();
        example.createCriteria()
                .andEmailEqualTo(email)
                .andStateEqualTo(UserMail.MailState.TO_BE_SENT);

        List<UserMailPO> userMailPOS = userMailMapper.selectByExample(example);
        if(userMailPOS.size()>0){
            return  userMailConvert.covertPOToDomain(userMailPOS.get(0));
        }else{
            return null;
        }

    }

    @Override
    public boolean updateUserMailState(UserMail userMail){
        UserMailExample example = new UserMailExample();
        example.createCriteria()
                .andEmailEqualTo(userMail.getEmail());
        UserMailPO updateMail = new UserMailPO();
        updateMail.setState(UserMail.MailState.SENDED);
        Integer result = userMailMapper.updateByExampleSelective(updateMail,example);
        return result!=null;
    }

    @Override
    public boolean editUser(User user) {
        UserPO userPO = getUserPO(user.getEmail());
        if(userPO == null){
            throw new BussinessException(ConstantsUtils.USER_NOT_EXIST);
        }
        if(userPO.getState() == User.UserState.DELETED){
            throw new BussinessException(ConstantsUtils.USER_DEACTIVATE);
        }

        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(user.getEmail());
        userMapper.updateByExampleSelective(userConvert.covertDomainToPO(user),example);
        return false;
    }

    @Override
    public boolean deactivateUser(String email) {

        if(!existUser(email))throw new BussinessException(ConstantsUtils.USER_NOT_EXIST);

        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        UserPO userPO = new UserPO();
        userPO.setState(User.UserState.DELETED);
        userMapper.updateByExampleSelective(userPO,example);
        return true;
    }






}
