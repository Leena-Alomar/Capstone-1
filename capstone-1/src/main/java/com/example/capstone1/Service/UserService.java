package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Product> wishlist=new ArrayList<>();
    private final MerchantService merchantService;
    private final ProductService productService;



    public ArrayList getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public User BuyProduct(String id, String productID, String merchantID) {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getMerchantStock();
        boolean isProductId = productService.checkId(productID);
        boolean isMerchantId = merchantService.checkId(merchantID);

        if (!isProductId && !isMerchantId) {
            return null;
        }

        for (User u:users) {

            if (u.getId().equals(id)) {

             for(MerchantStock m:merchantStocks){

                 if(m.getStock()>0&&m.getProductID().equals(productID)&&m.getMerchantID().equals(merchantID)){
                     double price= productService.getProcuctPriceByID(productID);

                     if(u.getBalance()>=price){
                         int newstock=m.getStock()-1;
                         m.setStock(newstock);
                         u.setBalance(u.getBalance()-price);
                         return u;
                     }
                 }
             }
            }
        }
        return null;
    }

    // Extra End Points ****************
    public boolean transferToUserById(String id,String email,double amount){
        for(User u:users){
            if(u.getId().equals(id)&&u.getBalance()>=amount){
                for(User e:users){
                    if(e.getEmail().equalsIgnoreCase(email)){
                        u.setBalance(u.getBalance()-amount);
                        e.setBalance(e.getBalance()+amount);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // Extra End Points ****************
    public ArrayList<Product> addProductToWishlist(String id,String proudctID){
        ArrayList<Product> products=productService.getProducts();

        for(Product w:wishlist){
            if(w.getId().equals(proudctID)){
                return wishlist;
            }
        }
        for(Product p:products) {
            if (p.getId().equals(proudctID)) {
                wishlist.add(p);
            }
        }
        return wishlist;
    }

    public boolean checkId(String id){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    // Extra End Points ****************
    public ArrayList<Product> getRecommenedProdcut(String id){
        ArrayList<Product> products=productService.getProducts();
        ArrayList<Product> recomended=new ArrayList<>();
        boolean isUser=checkId(id);
        double max=wishlist.get(0).getPrice();
        double min=wishlist.get(0).getPrice();
        String maxcate="";

        if(isUser){
            for(int i=0;i< wishlist.size();i++){
                if(wishlist.get(i).getPrice()>max){
                    max=wishlist.get(i).getPrice();

                }
                if(wishlist.get(i).getPrice()<min){
                    min=wishlist.get(i).getPrice();
                }
            }

            int countMax=0;
            int count=0;
            for(int i=0;i<wishlist.size();i++){
                String catid=wishlist.get(i).getCategoryID();
                for(int j=0;j<wishlist.size();j++)
                    if(wishlist.get(j).getCategoryID().equals(catid)){
                        count++;
                        maxcate=catid;
                    }

            }
            if(count>countMax){
                countMax=count;
                for(Product p:products){
                    if(p.getPrice()>=min&&p.getPrice()<=max&&p.getCategoryID().equals(maxcate)){
                        recomended.add(p);
                    }
                }
            }
        }

        return recomended;
    }

}
