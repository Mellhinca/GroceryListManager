# Design Document V2

**Author**:Team JMASK

## 1 Designment Considerations

### 1.1 Assumptions

  Assume user can download and install the application without any storage limit.  Internet is not required to used the application.

### 1.2 Constraints

  Since this software is only used for learning and does not use network communication (internet), it is not possible to interact and share with other software (such as PayPal, zelle and other payment software).

### 1.3 System Enviroment

  The application can only run on Android devices(Android phone, tablet, or any other devices running Android operating system) and requires Android API level 21 or greater software.

## 2 Architectural Design

  A high-level design view of the system and provide detailed design work.

### 2.1 Component Diagram

  There are total four components in GroceryListManager application design.
  Component Item contains all items, item type and quantity.
  Component Grocery List contains all lists that we can add, modify or any other updates.
  The database means "Grocery List" and "Item" all belong to database. in the other words, Grocery List and Item component represents the databse.
  Component GroceryListManager manage all the information from our database.
  Component User Interface directly showing everything that GroceryListManager got from our database.

 * Old Version Component Diagram
 
 ![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_OldComponentDiagram.png)

 * Final version Component Diagram
 
 ![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_FinalComponentDiagram.png)

### 2.2 Deployment Diagram

  Android application will run on Android devices and Android operating system will handles the database.
  we do not think here is any deployment diagram needed. but if we have to, the picture showing above is a simple deployment diagram.
  and everything we need is the Android OS.
  
  ![image](https://github.com/kanvile/test/blob/master/ui/deployment%20diagram.png)

## 3 Low-Level Design

  In this section we broken down the Component Diagram into a class diagram where we see the internal structure of each component in class.


### 3.1 Class Diagram
  
  The class diagram shows the detail of the method included in each class.
  The GroceryListManager contain the necessary methods to add, modify, or remove the list.
  The GroceryList class contain the variable necessary for a list to exsist. We also implement methods for user to add item, remove item, update the quantity, check off the item, clear check off, and group the item by the item type.
  The Item class takes control of the item database, the class contain variable necessary for an item to exist in the database. On top of that the class consist of getter and setter methods for the name, type, quantity of each item.
  user can also add and update price with the addPrice() and updatePrice() method.
  The last class is the Item Type class, in this class we add variable for the item type to exist. The class also contains methods to add the type of category, remove the category, add item, update item and remove the item.

  ![image](https://github.com/kurissu/uml/blob/master/Screenshot%202021-03-31%20003756.png)

### 3.2 Other Diagrams

  Here we demonstrate the Sequence diagram of how the class is behaving.

  ![image](https://github.com/kurissu/uml/blob/master/sequence%20diagram.png)

  Layout UML flow diagram
  ![image](https://user-images.githubusercontent.com/35271372/114290921-22b13780-9a51-11eb-9e26-3c8d85e56558.png)

## 4 User Interface Design
The UIs showing above may different from actual APK, here just an idea about how we worked on UI Design.

Simple view by UI design tool

![image](https://github.com/kanvile/test/blob/master/ui/1.png)

![image](https://github.com/kanvile/test/blob/master/ui/2.png)

![image](https://github.com/kanvile/test/blob/master/ui/3.png)

Detail view by Android Studio

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic1.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic2.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic3.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic4.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic5.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic6.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic7.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic8.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic9.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic10.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic11.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic12.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic13.png)

![image](https://github.com/qc-se-spring2021/370Spring21Sec34Team5/blob/main/GroupProject/Docs/z_pic14.png)