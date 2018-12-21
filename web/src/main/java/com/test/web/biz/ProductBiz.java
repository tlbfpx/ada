package com.test.web.biz;

import com.test.common.biz.BaseBiz;
import com.test.web.entity.Product;
import com.test.web.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * @author ezbuy on ${date}
 */
@Service
public class ProductBiz extends BaseBiz<ProductMapper, Product> {
}
