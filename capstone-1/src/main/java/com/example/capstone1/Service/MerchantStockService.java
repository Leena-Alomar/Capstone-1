package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks =new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;


    public ArrayList getMerchantStock(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        boolean isProductId= productService.checkId(merchantStock.getProductID());
        boolean isMerchantId=merchantService.checkId(merchantStock.getMerchantID());
        if(isProductId&&isMerchantId){
            merchantStocks.add(merchantStock);
        }

    }

    public boolean updateMerchantStock(String id,MerchantStock merchantStock){
        boolean isProductId= productService.checkId(merchantStock.getProductID());
        boolean isMerchantId=merchantService.checkId(merchantStock.getMerchantID());
        if(isProductId&&isMerchantId){
            for(int i=0;i<merchantStocks.size();i++){
                if(merchantStocks.get(i).getId().equals(id)){
                    merchantStocks.set(i,merchantStock);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for(int i=0;i<merchantStocks.size();i++){
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateStock(String id,String productID,String merchantID ,int stock){
        boolean isProductId= productService.checkId(productID);
        boolean isMerchantId=merchantService.checkId(merchantID);
        if(isProductId&&isMerchantId){
            for(MerchantStock m:merchantStocks){
            if(m.getProductID().equals(productID)&&m.getMerchantID().equals(merchantID)){
                int newStock=stock+m.getStock();
                m.setStock(newStock);
                return true;
            }
        }
        }
            return false;
    }

// Extra End Points ****************
    public boolean checkStock(String id,String productID,String merchantID ,int stock){
        boolean isProductId= productService.checkId(productID);
        boolean isMerchantId=merchantService.checkId(merchantID);
        if(isProductId&&isMerchantId){
            for(MerchantStock m:merchantStocks){
                if(m.getProductID().equals(productID)&&m.getMerchantID().equals(merchantID)){
                    if(m.getStock()>0)
                    return true;
                }
            }
        }
        return false;
    }

}
