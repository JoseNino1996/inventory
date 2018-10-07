package com.jbnsoft.inventory.sevice.stock;



import com.jbnsoft.inventory.repository.stock.ProductInventory;
import com.jbnsoft.inventory.sevice.stock.helper.ProcessOrder;
import org.springframework.jdbc.object.MappingSqlQuery;


import java.util.List;
import java.util.Map;

public interface  IProductInventoryService {
      ProductInventory create(ProductInventory productInventory);
       ProductInventory update(ProductInventory productInventory, Long id);
       void delete(Long id);
       ProductInventory findById(Long id);
       List<ProductInventory> getListOfProductInventory();

      void saveAll(Iterable<ProductInventory> productInventories);
      double processOrderQuantity(Map<Long, Long> productIdAndOrderedQty, ProcessOrder processOrder) throws Exception;


}
