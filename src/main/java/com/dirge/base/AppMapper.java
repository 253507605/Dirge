package com.dirge.base;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 不能放在mapper包下面，不能被组件给扫描到
 */
public interface AppMapper<T>
        extends BaseMapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T>, MySqlMapper<T>, Mapper<T> {
}