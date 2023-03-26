package com.javatechie.crud.example.customKey;

import static org.apache.commons.codec.digest.DigestUtils.sha512Hex;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        String key = target.getClass().getSimpleName()+method.getName()+ Arrays.toString(params);

//        return target.getClass().getSimpleName()+ "_" +method.getName()+ "_"+
//                StringUtils.arrayToDelimitedString(params,"_");
//
//        return Objects.hash(params);

        return sha512Hex(key);

    }
}
