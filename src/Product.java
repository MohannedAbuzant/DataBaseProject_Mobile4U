/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dr.Abuzant
 */
public class Product {
   int product_ID;
   int S_price;
   int P_price;
   int Quantity;
   int profit;

    Product(int id,int sprice,int pprice,int qty,int pprofit) {product_ID=id;
       S_price=sprice;
       P_price= pprice;
       Quantity=qty;
       profit= pprofit;
    }
  
}
