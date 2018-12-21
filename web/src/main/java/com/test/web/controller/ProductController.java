package com.test.web.controller;

import com.test.common.controller.BaseController;
import com.test.web.biz.ProductBiz;
import com.test.web.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ezbuy on ${date}
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController<ProductBiz, Product> {


}
