package ru.liga.batisMapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.liga.models.Orders;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Mapper
public interface OrderMapper {
    List<Orders> getOrdersByStatus(String status);
    Orders getOrderById(Long id);

}
