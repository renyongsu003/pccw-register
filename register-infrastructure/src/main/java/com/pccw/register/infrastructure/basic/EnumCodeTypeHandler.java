package com.pccw.register.infrastructure.basic;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EnumCodeTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private final Class<E> type;
    private final E[] enums;
    private final Map<Integer, Integer> ordinalCodeMap = new HashMap<>();
    private final Map<Integer, E> codeEnumMap = new HashMap<>();

    public EnumCodeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }

        for (E item : enums) {
            Integer code = getCode(item);
            ordinalCodeMap.put(item.ordinal(), code);
            codeEnumMap.put(code, item);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ordinalCodeMap.get(parameter.ordinal()));
    }

    private Integer getCode(E parameter) {
        try {
            Field value = parameter.getClass().getDeclaredField("code");
            value.setAccessible(true);
            return objectToInteger(value.get(parameter));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -9999;
    }

    private Integer objectToInteger(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof Integer) {
            return (Integer)obj;
        } else if (obj instanceof Byte) {
            Byte byteVal = (Byte)obj;
            return new Integer(byteVal.intValue());
        } else {
            return Integer.valueOf(obj.toString());
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        if (code == 0 && rs.wasNull()) {
            return null;
        }
        return toValueEnum(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int ordinal = rs.getInt(columnIndex);
        if (ordinal == 0 && rs.wasNull()) {
            return null;
        }
        return toValueEnum(ordinal);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        if (code == 0 && cs.wasNull()) {
            return null;
        }
        return toValueEnum(code);
    }

    private E toValueEnum(int code) {
        return codeEnumMap.get(code);
    }
}
