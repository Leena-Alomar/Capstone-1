package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList getCategory(){
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public boolean updateCategory(String id,Category category){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String id){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkId(String id){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }


}
