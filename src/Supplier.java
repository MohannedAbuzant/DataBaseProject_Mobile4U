/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
class Supplier {
    
    String SFirstName;
    String SLastName;
    int SPhoneNumber;
    int Product_Id;
    public Supplier(){
        
    }
    public Supplier(String SFirstName,  String SLastName,int SPhoneNumber,   int Product_Id){
        this.SFirstName= SFirstName;
        this.SLastName=SLastName;
        this.SPhoneNumber = SPhoneNumber;
        this.Product_Id= Product_Id;
    }
}