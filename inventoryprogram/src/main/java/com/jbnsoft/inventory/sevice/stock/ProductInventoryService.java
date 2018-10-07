package com.jbnsoft.inventory.sevice.stock;
import com.jbnsoft.inventory.repository.stock.ProductInventory;
import com.jbnsoft.inventory.repository.stock.ProductInventoryRepository;
import com.jbnsoft.inventory.sevice.stock.helper.ProcessOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductInventoryService implements IProductInventoryService {
    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Override
    public ProductInventory create(ProductInventory productInventory) {
        return productInventoryRepository.save(productInventory);
    }

    @Override
    public ProductInventory update(ProductInventory productInventory, Long id) {
        ProductInventory storedProductInventory = productInventoryRepository.findById(id).orElse(null   );
          productInventory.setId(storedProductInventory.getId());
        return productInventoryRepository.save(productInventory);
    }

    @Override
    public void delete(Long id) {
        productInventoryRepository.deleteById(id);
    }

    @Override
    public ProductInventory findById(Long id) {
        return productInventoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<ProductInventory> getListOfProductInventory() {
        List<ProductInventory> listOfProductInventory  = new ArrayList<>();
        for(ProductInventory productInventory : productInventoryRepository.findAll()) {
            if(productInventory == null) { continue; }
            listOfProductInventory.add(productInventory);
        }
        return listOfProductInventory;
    }

    @Override
    public double processOrderQuantity(Map<Long, Long> productIdAndOrderedQty, ProcessOrder processOrder) throws Exception {
        return processOrder.processOrderQuantity(productIdAndOrderedQty);
    }

    @Override
    public void saveAll(Iterable<ProductInventory> productInventories) {
        productInventoryRepository.saveAll(productInventories);
    }
}
