package com.example.summervacationproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.summervacationproject.bean.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublishItemMapper extends BaseMapper<Item> {
    int addItem(Item item);
}
