package com.study.ssyx.product.mapper;

//import com.study.ssyx.product.entity.Category;
import com.atguigu.ssyx.model.product.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author WearingCucumber
 * @since 2023-06-29
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
