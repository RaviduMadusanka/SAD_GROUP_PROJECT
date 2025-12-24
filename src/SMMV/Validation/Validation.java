/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package SMMV.Validation;

/**
 *
 * @author user
 */
public enum Validation {

    EMAIL_VALIDATION{
        @Override
        public String validate() {
          return "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        }
        
    }, MOBILE_NUMBER_VALIDATION{
        @Override
        public String validate() {
           return "^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$";
        }
        
    }, LAND_NUMBER_VALIDATION{
        @Override
        public String validate() {
            return "^0((11)|(2(1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}$";
        }
        
    }, NIC_VALIDATION{
        @Override
        public String validate() {
            return super.validate(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
        
    }, PASSWORD_VALIDATION{
        @Override
        public String validate() {
            return "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        }
        
    }, INT_VALIDATION{
        @Override
        public String validate() {
            return "^(\\d+)$";
        }
        
    };

    public String validate() {
        return " ";
    }
}
