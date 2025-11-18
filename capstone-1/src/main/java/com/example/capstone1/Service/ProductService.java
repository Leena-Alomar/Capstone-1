package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    ArrayList<Product> products =new ArrayList<>();
    private final CategoryService categoryService;

    public ArrayList getProducts(){
        return products;
    }

    public void  addProduct(Product product){
        boolean isCatgoryID= categoryService.checkId(product.getCategoryID());
        if(isCatgoryID){
            products.add(product);
        }
    }

    public boolean updateProduct(String id,Product product){
        boolean isCatgoryID= categoryService.checkId(product.getCategoryID());
        if(isCatgoryID){
            for(int i=0;i<products.size();i++){
                if(products.get(i).getId().equals(id)){
                    products.set(i,product);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteProduct(String id){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkId(String id){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public double getProcuctPriceByID(String id){
        boolean isProuductId=checkId(id);
        if(isProuductId){
            for(Product p:products){
                if(p.getId().equals(id)){
                    return p.getPrice();
                }
            }
        }
        return 0.0;
    }

    // Extra End Points ****************
    public ArrayList<Product> filterByProductPrice(double minPrice,double maxPrice,String lowesthighest){
        ArrayList<Product> priceRange=new ArrayList<>();
        ArrayList<Product> priceRangeRev=new ArrayList<>();
        for(Product p:products){
            if(p.getPrice()>=minPrice&&p.getPrice()<=maxPrice){
                priceRange.add(p);
            }
        }

        if(lowesthighest.equalsIgnoreCase("lowest")){
            priceRange.sort(Comparator.comparingDouble(Product::getPrice));
        } else if (lowesthighest.equalsIgnoreCase("highest")) {
             priceRange.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        }
        return priceRange;

    }

// Bounce End Points*******
    public ArrayList<Product> getProductBycategoryID(String cartegotyID){
        ArrayList<Product> productCatgory=new ArrayList<>();
        boolean isCategoryId= categoryService.checkId(cartegotyID);
        if(isCategoryId){
            for(Product p:products){
                if(p.getCategoryID().equals(cartegotyID)){
                    productCatgory.add(p);

                }
            }
        }
        return productCatgory;
    }

    public ArrayList<Product> getProductBynName(String name){
        ArrayList<Product> productByName=new ArrayList<>();
        for(Product p:products){
            if(p.getName().contains(name)){
                productByName.add(p);
            }
        }
        return productByName;
    }

}
