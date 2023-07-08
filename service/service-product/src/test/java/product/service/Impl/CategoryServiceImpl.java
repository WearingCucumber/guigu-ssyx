package product.service.Impl;

import com.atguigu.ssyx.model.product.Category;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import product.mapper.CategoryMapper;
import product.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
