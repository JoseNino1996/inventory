package com.jbnsoft.inventory.sevice.product;

import com.jbnsoft.inventory.repository.product.ProductOrder;
import com.jbnsoft.inventory.repository.product.ProductOrderRepository;
import com.jbnsoft.inventory.repository.stock.ProductInventory;
import com.jbnsoft.inventory.sevice.stock.IProductInventoryService;
import com.jbnsoft.inventory.sevice.stock.helper.CreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductOrderService implements  IProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private IProductInventoryService productInventoryService;

    @Autowired
    private CreateOrder createOrder;
    @Override
    public ProductOrder create(ProductOrder productOrder) throws Exception {
        Map<Long, Long> productIdAndOrderQty = new HashMap<>();
        ProductInventory productInventory = productOrder.getProductInventory();
        productIdAndOrderQty.put(productInventory.getId(), productOrder.getOrderedQty());
        productInventoryService.processOrderQuantity(productIdAndOrderQty,createOrder);
        return productOrderRepository.save(productOrder);
    }

    @Override
    public void delete(Long id) {
        productOrderRepository.deleteById(id);
    }
    @Override
    public ProductOrder update(ProductOrder productOrder, Long id) {
        return productOrderRepository.save(productOrder);
    }

    @Override
    public ProductOrder findById(Long id) {
        return productOrderRepository.findById(id).orElse(null  );
    }
}
